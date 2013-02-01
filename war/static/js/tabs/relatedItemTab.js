RelatedItemModel = Backbone.Model.extend({
	urlRoot : '/f/relatedItems',
	initialize : function() {
	}
});

RelatedItemCollection = Backbone.Collection.extend({
	model : RelatedItemModel
});

/**
 * the view. contains an Item and a RelatedItemCollection. Maintains
 * relationship between the 2.
 */
RelatedItemView = Backbone.View.extend({
	events : {
		"click button#addALink" : "submitAddALink",
		"click span#removeRelatedItem" : "deleteALink",
	},
	initialize : function() {
		this.$el = $("#relatedItemContainer");
		_.bindAll(this, "renderContent");
		_.bindAll(this, "displayAddALink");
		_.bindAll(this, "displayDeleteALink");
		this.collection = new RelatedItemCollection();
		this.collection.url = "/f/relatedItems/" + this.model.get("id");
		this.collection.bind('add', this.renderContent, this);
		this.collection.bind('remove', this.renderContent, this);
		this.initialized = false;
		this.render();
	},
	render : function() {
		if (!this.initialized) {
			this.renderLoading();
			this.refreshRelated();
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
			this.template = _.template($("#relatedItemTemplate").html());
			if (typeof (this.collection) != 'undefined') {
				this.$el.html(this.template({
					relatedItems : this.collection.toJSON()
				}));
			}
		}
	},
	refreshRelated : function() {
		this.collection.fetch({
			success : this.renderContent
		});
	},
	submitAddALink : function() {
		if (this.model.get("selected")) {
			var link = $('input#addALinkInput').val();
			// TODO: validate
			var relatedItem = new RelatedItemModel({
				"relatedItem" : link,
				"itemId" : this.model.get("id")
			});

			relatedItem.save(relatedItem.toJSON(), {
				success : this.displayAddALink
			});
		}
	},
	displayAddALink : function(model) {
		this.collection.add(model);
	},
	deleteALink : function(event) {
		if (this.model.get("selected")) {
			var currentTarget = $(event.currentTarget);
			var id = $(event.currentTarget).data("id");
			var model = this.collection.get(id);
			model.destroy({
				success : this.displayDeleteALink
			});
		}
	},
	displayDeleteALink : function(model) {
		this.collection.remove(model);
	}
});