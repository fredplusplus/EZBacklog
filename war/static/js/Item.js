ItemModel = Backbone.Model.extend({
	initialize : function() {
	}
});

ItemCollection = Backbone.Collection.extend({
	model : ItemModel
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
		var id = $(event.currentTarget).data("id");
		var level = $(event.currentTarget).data("level");
		var model = this.collection.get(id);
	}
});