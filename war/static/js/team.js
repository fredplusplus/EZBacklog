TeamModel = Backbone.Model.extend({
	initialize : function() {
	}
});

TeamCollection = Backbone.Collection.extend({
	model : TeamModel
});

TeamCollectionView = Backbone.View.extend({
	initialize : function() {
		this.template = _.template($("#teamListTemplate").html());
		this.render();
	},
	render : function() {
		this.$el.html(this.template({
			teams : this.collection.toJSON(),
		}));
	}
});