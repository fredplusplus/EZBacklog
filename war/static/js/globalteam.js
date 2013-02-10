var CreateTeamModel = Backbone.Model.extend({
	urlRoot : "/f/manageTeam",
	defaults : {
		name : '',
	},
	initialize : function() {

	}
});

var AddMemberModel = Backbone.Model.extend({
	urlRoot : "/f/manageMember",
	defaults : {
		Role : '',
		userEmail : '',
		teamId : '',
		teamName : '',
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

var AddMemberView = Backbone.View.extend({
	initialize : function() {
		this.template = _.template($('#addMemberModalTemplate').html());
		this.$el = $('#addMemberModalContainer'); 
		this.render();
	},
	render : function() {
		console.log(this.model.toJSON());
		this.$el.html(this.template(this.model.toJSON()));
	},
	events: {
		"click #addMemberModal #submitUpdate" : "submitAddMember"
	},
	submitAddMember : function(event) {
		var teamid = $(event.currentTarget).data("teamid");
		if (teamid == this.model.get("teamId")) {
			var addMemberModal = $("#addMemberModal");
			this.model.set("userEmail", addMemberModal.find("input#userEmail").val());
			this.syncAddMember();
		}
	},
	syncAddMember : function() {
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