UpdateItemModalView = Backbone.View.extend({
	initialize : function() {
		this.$el = $("#updateItemModalContainer");
		this.template = _.template($("#updateItemModalTemplate").html());
		this.render();
	},
	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	}
});