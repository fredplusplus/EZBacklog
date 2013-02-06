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
			this.model.set("point", this.$el.find("input#point").val());
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