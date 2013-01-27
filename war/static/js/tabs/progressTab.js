ProgressView = Backbone.View.extend({
	initialize : function() {
		this.$el = $("#progressContainer");
		this.template = _.template($("#progressTemplate").html());
		this.render();
	},
	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
	}
});