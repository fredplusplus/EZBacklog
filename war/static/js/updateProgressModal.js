UpdateProgressModalView = Backbone.View.extend({
	initialize : function() {
		this.$el = $("#updateProgressModalContainer");
		this.template = _.template($("#updateProgressModalTemplate").html());
		this.render();
	},
	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	},
	events : {
		"keyup input[id=burndownPoint]" : "validateBurndownPoint",
		"click #updateProgressModal .btn-primary" : "submitUpdateProgress",
	},
	validateBurndownPoint : function() {
		if (this.model.get("selected")) {
			var burndownPoint = this.$el.find("#burndownPoint").val();
			burndownPoint = parseInt(burndownPoint);
			var $controlGroup = this.$el.find("#burndownPointGroup");
			var $helpText = this.$el.find("#burndownPointHelp");
			if (isNaN(burndownPoint)) {
				$controlGroup.addClass("error");
				$helpText.show();
			} else {
				$controlGroup.removeClass("error");
				$helpText.hide();
			}
		}
	},
	submitUpdateProgress : function() {
		if (this.model.get("selected")) {
			var burndownPoint = this.$el.find("#burndownPoint").val();
			burndownPoint = parseInt(burndownPoint);
			if (isNaN(burndownPoint)) {
				return this;
			}
			var resolvedPoint = this.model.get("resolvedPoint");
			this.model.set({
				"resolvedPoint" : resolvedPoint + burndownPoint
			});
			return this.saveItem();
		}
	},
	saveItem : function() {
		$("#waitModal").modal();
		return this.model.save(this.model.toJSON(), {
			success : function(model, response) {
				$("#waitModal").modal('hide');
				location.reload();
			},
			error : function(model, response) {
			}
		});
	}
});