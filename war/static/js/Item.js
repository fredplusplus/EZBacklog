ItemModel = Backbone.Model.extend({
	defaults : {
		"selected" : false,
	},
	initialize : function() {
	}
});

ItemCollection = Backbone.Collection.extend({
	model : ItemModel
});

ItemDetailView = Backbone.View.extend({
	initialize : function() {
		this.$el = $("#itemDetailContainer");
		this.template = _.template($("#itemDetailTemplate").html());
		_.bindAll(this, "render");
		this.render();
	},
	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
		return this;
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
			console.log("not first click");
			this.currentModel.set({
				"selected" : false
			});
		} else {
			console.log("first click");
		}
		this.currentModel = model;
		model.set({
			"selected" : true
		});
		var itemDetailView = new ItemDetailView({
			model : model
		});
	}
});