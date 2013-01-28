RelatedItemModel = Backbone.Model.extend({
	initialize : function() {
	}
});

RelatedItemCollection = Backbone.Collection.extend({
	url : function() {
		return '/f/relatedItems/' + this.itemId;
	},
	model : RelatedItemModel
});

/**
 * the view. contains an Item and a RelatedItemCollection.
 * Maintains relationship between the 2.
 */
RelatedItemView = Backbone.View.extend({
	initialize : function() {
		this.$el = $("#relatedItemContainer");
		this.render();
	},
	render : function() {
		if (typeof (this.collection) == 'undefined') {
			this.renderLoading();
			this.refreshRelated();
		} else {
			this.renderContent();
		}
	},
	renderLoading : function() {
		this.template = _.template($("#loadingTemplate").html());
		this.$el.html(this.template(this.model.toJSON()));
	},
	renderContent : function() {
		this.template = _.template($("#relatedItemTemplate").html());
		this.$el.html(this.template({
			relatedItems : this.collection.toJSON()
		}));
	},
	refreshRelated : function() {
		console.log("refresh");
		this.collection = new RelatedItemCollection();
		this.collection.url = "/f/relatedItems/" + this.model.get("id");
		this.collection.fetch({
			success : function(collection, response) {

			},
			error : function(collection, response) {

			}
		});
	}
});