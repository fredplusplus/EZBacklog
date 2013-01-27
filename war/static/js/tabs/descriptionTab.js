DescriptionView = Backbone.View.extend({
	initialize : function() {
		this.$el = $("#descriptionContainer");
		this.template = _.template($("#descriptionTemplate").html());
		this.render();
	},
	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
	}
});