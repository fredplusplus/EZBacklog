Dashboard = Backbone.View.extend({
	initialize : function() {
		this.render();
	},
	render : function() {
		var time = new Date(dashboardData.backlogger.joinDate + " UTC");
		dashboardData.backlogger.joinDate = (time.getMonth() + 1) + '/'
				+ time.getDate() + '/' + time.getFullYear();
		var dashboardTemplate = _.template($("#dashboardTemplate").html(),
				dashboardData);
		this.$el.html(dashboardTemplate);
	}
});