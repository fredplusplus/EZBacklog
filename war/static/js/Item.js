/**
 * The basic item model.
 */
ItemModel = Backbone.Model.extend({
	urlRoot : '/f/itemUpdate',
	defaults : {
		"selected" : false,
		"point" : 0,
		"resolvedPoint" : 0,
		"rank" : 0,
		"status" : 'Open',
		"shortDescription" : "",
		"longDescription" : "",
		"ownerEmail" : ""
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
		return -item.get("rank");
	}
});

ItemDetailView = Backbone.View.extend({
	initialize : function() {
		this.$el = $("#itemDetailContainer");
		this.template = _.template($("#itemDetailTemplate").html());
		this.render();
	},
	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	},
	events : {
		"click #deleteModal .btn-primary" : "deleteItem",
		"click #resolveBtn" : "resolveItem",
		"click #updateProgressBtn" : "displayUpdateProgress",
		"click #reopenBtn" : "reopenItem",
		"click #edit" : "displayEdit",
		"click #createChild" : "displayCreateChild",
	},
	deleteItem : function(event) {
		event.preventDefault();
		if (this.model.get("selected")) {
			$("#waitModal").modal();
			this.model.destroy({
				success : function(model, response) {
					location.reload();
				},
				error : function(model, response) {
				}
			});
		}
	},
	displayCreateChild : function(event) {
		event.preventDefault();
		if (this.model.get("selected")) {
			if (typeof (this.model.createView) == "undefined") {
				var child = new ItemModel({
					ownerEmail : this.model.get("ownerEmail"),
					parentId : this.model.get("id"),
					itemLevel : this.model.get("itemLevel") + 1,
					teamId : this.model.get("teamId"),
					teamName : this.model.get("teamName")

				});
				this.model.createView = new UpdateItemModalView({
					model : child
				});
			} else {
				this.model.createView.render();
			}
			$("#updateItemModal").modal();
		}
	},
	displayEdit : function(event) {
		event.preventDefault();
		if (this.model.get("selected")) {
			if (typeof (this.model.editView) == "undefined") {
				this.model.editView = new UpdateItemModalView({
					model : this.model
				});
			} else {
				this.model.editView.render();
			}
			$("#updateItemModal").modal();
			return this;
		}
	},
	displayUpdateProgress : function(event) {
		event.preventDefault();
		if (this.model.get("selected")) {
			if (typeof (this.model.updateProgressView) == "undefined") {
				this.model.updateProgressView = new UpdateProgressModalView({
					model : this.model
				});
			} else {
				this.model.updateProgressView.render();
			}
			$("#updateProgressModal").modal();
			return this;
		}
	},
	resolveItem : function() {
		if (this.model.get("selected")) {
			this.model.set({
				"status" : 'Resolved'
			});
			return this.saveItem();
		}
	},
	reopenItem : function() {
		if (this.model.get("selected")) {
			this.model.set({
				"status" : 'Open'
			});
			return this.saveItem();
		}
	},
	saveItem : function() {
		$("#waitModal").modal();
		return this.model.save(this.model.toJSON(), {
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
		// detail view
		if (typeof (model.detailView) == 'undefined') {
			model.detailView = new ItemDetailView({
				model : model
			});
		} else {
			model.detailView.render();
		}
		// dscription view
		if (typeof (model.descriptionView) == 'undefined') {
			model.descriptionView = new DescriptionView({
				model : model
			});
		} else {
			model.descriptionView.render();
		}
		// progress view
		if (backloggerModel.get("usePoint")) {
			if (typeof (model.progressView) == 'undefined') {
				model.progressView = new ProgressView({
					model : model
				});
			} else {
				model.progressView.render();
			}
		} else {
			$("#progressTab").hide();
		}
		// comment view
		if (typeof (model.commentView) == 'undefined') {
			model.commentView = new CommentView({
				model : model
			});
		} else {
			model.commentView.render();
		}
		// related item view
		if (typeof (model.relatedItemView) == 'undefined') {
			model.relatedItemView = new RelatedItemView({
				model : model
			});
		} else {
			model.relatedItemView.render();
		}
		// audit trails view
		if (typeof (model.auditView) == 'undefined') {
			model.auditView = new AuditView({
				model : model
			});
		} else {
			model.auditView.render();
		}
		// breadcrumb view
		if (typeof (model.breadcrumbView) == 'undefined') {
			model.breadcrumbView = new BreadCrumbView({
				model : model
			});
		} else {
			model.breadcrumbView.render();
		}
		$("#itemTabsContainer").show();
	}
});