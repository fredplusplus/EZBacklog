Dashboard = Backbone.View.extend({
	initialize : function() {
		this.render();
	},
	render : function() {
		dashboardData.backlogger.joinDate = formatDate(dashboardData.backlogger.joinDate);
		var dashboardTemplate = _.template($("#dashboardTemplate").html(),
				dashboardData);
		this.$el.html(dashboardTemplate);
	}
});