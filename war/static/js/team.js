var TeamModel = Backbone.Model.extend({
	defaults : {},
	initialize : function() {
	}
});

var TeamCollection = Backbone.Collection.extend({
	model : TeamModel
});

var CreateTeamModel = Backbone.Model.extend({
	urlRoot : "/f/manageTeam",
	defaults : {
		name : '',
	},
	initialize : function() {

	}
});

var TeamMemberModel = Backbone.Model.extend({
	urlRoot : "/f/manageMember",
	defaults : {
		Role : '',
		userEmail : '',
		teamId : '',
		teamName : '',
	}
});

var TeamCollectionView = Backbone.View.extend({
	initialize : function() {
		this.template = _.template($("#teamListTemplate").html());
		this.render();
	},
	render : function() {
		this.$el.html(this.template({
			teams : this.collection.toJSON(),
		}));
	},
	events : {
		"click #teamContainer #addMemberBtn" : "displayAddMember",
		"click #teamContainer #removeMemberBtn" : "removeMember",
	},
	removeMember : function(event) {
		event.preventDefault();
		var memberid = $(event.currentTarget).data("memberid");
		console.log(memberid);
		var removeMemberModel = new TeamMemberModel();
		removeMemberModel.set("id", memberid);
		$("#waitModal").modal();
		removeMemberModel.destroy({
			success : function(model, response) {
				location.reload();
			},
			error : function(model, response) {
			}
		});
	},
	displayAddMember : function(event) {
		event.preventDefault();
		var teamid = $(event.currentTarget).data("teamid");
		console.log(teamid);
		var teamModel = this.collection.get(teamid);
		if (typeof (teamModel.addMemberView) == 'undefined') {
			console.log("creating new add member modal");
			var addMemberModel = new TeamMemberModel();
			addMemberModel.set("teamId", teamid);
			addMemberModel.set("teamName", teamModel.get("teamName").name)
			teamModel.addMemberView = new AddMemberView({
				model : addMemberModel
			});
		} else {
			console.log("reusing add member modal");
			teamModel.addMemberView.render();
		}
		$("#addMemberModal").modal({
			keyboard : false
		});
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
	events : {
		"click #addMemberModal #submitUpdate" : "submitAddMember"
	},
	submitAddMember : function(event) {
		var teamid = $(event.currentTarget).data("teamid");
		if (teamid == this.model.get("teamId")) {
			var addMemberModal = $("#addMemberModal");
			this.model.set("userEmail", addMemberModal.find("input#userEmail")
					.val());
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