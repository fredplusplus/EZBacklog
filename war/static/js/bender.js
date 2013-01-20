BenderView = Backbone.View.extend({
	initialize : function() {
		this.$el = $("#itemDetailContainer");
		this.template = _.template($("#benderTemplate").html());
		this.render();
	},
	render : function() {
		this.$el.html(this.template({
			items : this.collection.toJSON()
		}));
	}
});