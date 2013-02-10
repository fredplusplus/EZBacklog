TeamModel = Backbone.Model.extend({
	defaults : {
	},
	initialize : function() {
	}
});

TeamCollection = Backbone.Collection.extend({
	model : TeamModel
});

TeamCollectionView = Backbone.View.extend({
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
		"click #teamContainer #addMemberBtn" : "displayAddMember"
	},
	displayAddMember : function(event) {
		event.preventDefault();
		var teamid = $(event.currentTarget).data("teamid");
		console.log(teamid);
		var teamModel = this.collection.get(teamid);
		if (typeof (teamModel.addMemberView) == 'undefined') {
			console.log("creating new add member modal");
			var addMemberModel = new AddMemberModel();
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