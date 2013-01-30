AuditModel = Backbone.Model.extend({
	initialize : function() {
	}
});

AuditCollection = Backbone.Collection.extend({
	model : AuditModel
});

/**
 * the view. contains an Item and a AuditCollection. Maintains relationship
 * between the 2.
 */
AuditView = Backbone.View.extend({
	initialize : function() {
		this.$el = $("#auditContainer");
		_.bindAll(this, "renderContent");
		this.collection = new AuditCollection();
		this.collection.url = "/f/audit/" + this.model.get("id");
		this.collection.bind('add', this.renderContent, this);
		this.initialized = false;
		this.render();
	},
	render : function() {
		if (!this.initialized) {
			this.renderLoading();
			this.refreshAudit();
			this.initialized = true;
		} else {
			this.renderContent();
		}
	},
	renderLoading : function() {
		this.template = _.template($("#loadingTemplate").html());
		this.$el.html(this.template(this.model.toJSON()));
	},
	renderContent : function() {
		if (this.model.get("selected")) {
			this.template = _.template($("#auditTemplate").html());
			if (typeof (this.collection) != 'undefined') {
				this.$el.html(this.template({
					audits : this.collection.toJSON()
				}));
			}
		}
	},
	refreshAudit : function() {
		this.collection.fetch({
			success : this.renderContent
		});
	}
});