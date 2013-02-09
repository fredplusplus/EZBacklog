var CreateTeamModel = Backbone.Model.extend({
	urlRoot : "/f/manageTeam",
	defaults : {
		name : '',
	},
	initialize : function() {

	}
});

var CreateTeamView = Backbone.View.extend({
	initialize : function() {
		this.template = _.template($('#createTeamModalTemplate').html());
		this.render();
	},
	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
	},
	events : {
		"click #createTeamModal #submitUpdate" : "submitCreateTeam"
	},
	submitCreateTeam : function() {
		var $modal = $("#createTeamModal");
		var teamName = $modal.find("#name").val();
		this.model.set("name", teamName);
		this.syncCreate();

	},
	syncCreate : function() {
		$("#waitModal").modal();
		return this.model.save(this.model.toJSON(), {
			success : function(model, response) {
				location.reload();
			},
			error : function(model, response) {
			}
		});
	}
});