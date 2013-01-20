BreadCrumbView = Backbone.View.extend({
	initialize : function() {
		this.$el = $("#breadCrumbContainer");
		this.template = _.template($("#breadCrumbTemplate").html());
		_.bindAll(this, "render");
		this.render();
	},
	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	}
});