TeamDropdownView = Backbone.View.extend({
	initialize : function() {
		this.template = _.template($("#teamDropdownTemplate").html());
		if (this.$el.length) {
			this.render();
		}
	},
	render : function() {
		this.$el.html(this.template({
			teams : this.collection.toJSON()
		}))
	},
	events : {
		"click #teamoption" : "updateSelector"
	},
	updateSelector : function(event) {
		event.preventDefault();
		var currentTarget = $(event.currentTarget);
		var teamid = currentTarget.data("teamid");
		var teamName = currentTarget.data("teamname");
		var $selector = $("#teamSelector");
		$selector.data("teamid", teamid);
		$selector.data("teamname", teamName);
		$selector.html(teamName);
		console.log(teamid);
	}
});

UpdateItemModalView = Backbone.View.extend({
	initialize : function() {
		this.$el = $("#updateItemModalContainer");
		this.template = _.template($("#updateItemModalTemplate").html());
		this.render();
	},
	events : {
		"keyup input[id=shortDescription]" : "validateShortDescription",
		"keyup input[id=rank]" : "validateRank",
		"keyup input[id=point]" : "validatePoint",
		"click #updateItemModal #cancelUpdate" : "deactivate",
		"click #updateItemModal #submitUpdate" : "submitUpdate",
	},
	render : function() {
		this.model.set("selected", true);
		this.$el.html(this.template(this.model.toJSON()));
		this.teamDropdownView = new TeamDropdownView({
			collection : teams,
			el : this.$el.find("#teamDropdownContainer")
		});
		return this;
	},
	validateShortDescription : function() {
		if (this.model.get("selected")) {
			var $controlGroup = this.$el.find("#shortDescriptionGroup");
			var $helpText = $controlGroup.find(".help-block");
			var value = this.$el.find("input#shortDescription").val();
			if (value.length > 40) {
				$controlGroup.addClass("error");
				$helpText.show();
			} else {
				$controlGroup.removeClass("error");
				$helpText.hide();
			}
		}
	},
	validateRank : function() {
		this.validateNumeric('rank');
	},
	validatePoint : function() {
		this.validateNonNegative('point');
	},
	validateNumeric : function(id) {
		if (this.model.get("selected")) {
			var $controlGroup = this.$el.find("#" + id + "Group");
			var $helpText = $controlGroup.find(".help-block");
			var value = $controlGroup.find("#" + id).val();
			if (isNaN(parseInt(value))) {
				$controlGroup.addClass("error");
				$helpText.show();
			} else {
				$controlGroup.removeClass("error");
				$helpText.hide();
			}
		}
	},
	validateNonNegative : function(id) {
		if (this.model.get("selected")) {
			var $controlGroup = this.$el.find("#" + id + "Group");
			var $helpText = $controlGroup.find(".help-block");
			var value = $controlGroup.find("#" + id).val();
			if (isNaN(parseInt(value)) || parseInt(value) < 0) {
				$controlGroup.addClass("error");
				$helpText.show();
			} else {
				$controlGroup.removeClass("error");
				$helpText.hide();
			}
		}
	},
	submitUpdate : function() {
		if (this.model.get("selected")) {
			this.model.set("shortDescription", encodeURIComponent(this.$el
					.find("input#shortDescription").val()));
			this.model.set("longDescription", encodeURIComponent(this.$el.find(
					"textarea#longDescription").val()));
			this.model.set("rank", this.$el.find("input#rank").val());
			var teamId = this.$el.find("#teamSelector").data("teamid");
			if (!isNaN(teamId)) {
				this.model.set("teamId", teamId);
				console.log(teamId)
			}
			this.model.set("teamName", this.$el.find("#teamSelector").data(
					"teamname"));
			var point = this.$el.find("input#point").val();
			if (typeof (point) != 'undefined' && point != '') {
				this.model.set("point", this.$el.find("input#point").val());
			}
			this.syncSaveItem();
		}
		this.deactivate();
	},
	deactivate : function() {
		var state = this.model.get("selected");
		if (this.model.get("selected")) {
			this.model.set("selected", false);
			if (typeof (benderView) != 'undefined') {
				benderView.render();
			}
		}
		return state;
	},
	syncSaveItem : function() {
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