UpdateItemModalView = Backbone.View.extend({
	initialize : function() {
		this.$el = $("#updateItemModalContainer");
		this.template = _.template($("#updateItemModalTemplate").html());
		this.render();
	},
	events : {
		"keyup input[id=shortDescription]" : "validateShortDescription",
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
			var value=this.$el.find("#shortDescription").val();
			if (value.length > 40) {
				$controlGroup.addClass("error");
				$helpText.show();
			} else {
				$controlGroup.removeClass("error");
				$helpText.hide();
			}
		}
	},
	submitUpdate : function() {
		this.deactivate();
	},
	deactivate : function() {
		var state = this.model.get("selected");
		if (this.model.get("selected")) {
			this.model.set("selected", false);
		}
		return state;
	}
});