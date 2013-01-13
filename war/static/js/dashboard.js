Dashboard = Backbone.View.extend({
	initialize : function() {
		this.render();
	},
	render : function() {
		var dashboardTemplate = _.template($("#dashboardTemplate").html(), dashboardData);
		this.$el.html(dashboardTemplate);
	}
});