ItemModel = Backbone.Model.extend({
	urlRoot : '/f/itemUpdate',
	defaults : {
		"selected" : false,
	},
	initialize : function() {
	}
});

/**
 * Item Collection model, drives the item list display. Items are sorted by
 * status, then rank.
 */
ItemCollection = Backbone.Collection.extend({
	model : ItemModel,
	comparator : function(item) {
		return [
				String.fromCharCode.apply(String, _.map(item.get("status")
						.split(""), function(c) {
					return 0xffff - c.charCodeAt();
				})), item.get("rank") ];
	}
});

ItemDetailView = Backbone.View.extend({
	initialize : function() {
		this.$el = $("#itemDetailContainer");
		this.template = _.template($("#itemDetailTemplate").html());
		this.model.bind('change', this.render, this);
		_.bindAll(this, "render");
		this.render();
	},
	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	},
	events : {
		"click #deleteModal .btn-primary" : "deleteItem",
		"click #resolveBtn" : "resolveItem",
		"click #updateProgressBtn" : "updateProgress",
		"click #reopenBtn" : "reopenItem",
	},
	deleteItem : function() {
		$("#waitModal").modal();
		if (this.model.get("selected")) {
			this.model.destroy({
				success : function(model, response) {
					location.reload();
				},
				error : function(model, response) {
				}
			});
		}
	},
	updateProgress : function() {
		console.log("update item");
	},
	resolveItem : function() {
		if (this.model.get("selected")) {
			this.model.set({
				"status" : 'Resolved'
			});
			this.saveItem();
		}
	},
	reopenItem : function() {
		if (this.model.get("selected")) {
			this.model.set({
				"status" : 'Open'
			});
			this.saveItem();
		}
	},
	saveItem : function() {
		$("#waitModal").modal();
		this.model.save(this.model.toJSON(), {
			success : function(model, response) {
				location.reload();
			},
			error : function(model, response) {
			}
		});
	}
});

ItemCollectionView = Backbone.View.extend({
	initialize : function() {
		this.template = _.template($("#itemCollectionTemplate").html());
		_.bindAll(this, "render");
		this.collection.bind('change', this.render, this);
		this.render();
	},
	render : function() {
		this.$el.html(this.template({
			items : this.collection.toJSON()
		}));
		return this;
	},
	events : {
		"click .itemBox" : "displayItem"
	},
	displayItem : function(event) {
		var currentTarget = $(event.currentTarget);
		var id = $(event.currentTarget).data("id");
		var level = $(event.currentTarget).data("level");
		var model = this.collection.get(id);
		if (this.currentModel) {
			this.currentModel.set({
				"selected" : false
			});
		}
		this.currentModel = model;
		model.set({
			"selected" : true
		});
		// create views and attach to model.
		if (typeof (model.detailView) == 'undefined') {
			model.detailView = new ItemDetailView({
				model : model
			});
		} else {
			model.detailView.render();
		}
		if (typeof (model.breadcrumbView) == 'undefined') {
			model.breadcrumbView = new BreadCrumbView({
				model : model
			});
		} else {
			model.breadcrumbView.render();
		}
	}
});