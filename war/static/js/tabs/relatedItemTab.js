RelatedItemModel = Backbone.Model.extend({
	defaults : {
		"selected" : false,
		"cached" : false,
	},
	urlRoot : '/f/relatedItems',
	initialize : function() {
	}
});

RelatedItemView = Backbone.View.extend({
	initialize : function() {
		this.$el = $("#relatedItemContainer");
		this.render();
	},
	render : function() {
		if (!this.model.get("cached")) {
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
		this.$el.html(this.template(this.model.toJSON()));
	},
	events : {
		"click #refresh-related" : "refreshRelated"
	},
	refreshRelated : function() {
		console.log("refresh");
		
	}
});