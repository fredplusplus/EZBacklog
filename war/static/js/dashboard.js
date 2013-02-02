Dashboard = Backbone.View
		.extend({
			initialize : function() {
				this.render();
				google.load("visualization", "1", {
					packages : [ "corechart" ]
				});
				google.setOnLoadCallback(this.drawChart);
			},
			render : function() {
				dashboardData.backlogger.joinDate = formatDate(dashboardData.backlogger.joinDate);
				var dashboardTemplate = _.template($("#dashboardTemplate")
						.html(), dashboardData);
				this.$el.html(dashboardTemplate);
			},
			drawChart : function() {
				var data = google.visualization
						.arrayToDataTable(dashboardData.milestones);
				var chart = new google.visualization.LineChart(document
						.getElementById('milestoneContainer'));
				chart.draw(data, {
					curveType : "function",
					height : 400,
					backgroundColor : "#fbfbfb",
					hAxis : {
						format : '#'
					},
					vAxis : {
						format : '#',
						minValue : 0
					},
					pointSize : 3,
					chartArea : {
						top : 20,
						left : 20,
						width : '80%'
					}
				});
			}
		});