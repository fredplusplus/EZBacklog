SettingModel = Backbone.Model.extend({
	urlRoot : '/f/setting',
	defaults : {
		showActive : true,
		showResolved : true,
		showDeleted : false,
	},
	initialize : function() {
	}
});

SettingView = Backbone.View.extend({
	initialize : function() {
		this.$el = $("#settingContainer");
		this.template = _.template($("#settingTemplate").html());
		this.render();
	},
	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	},
	init : function() {
		this.initToggle("showActive");
		this.initToggle("showDeleted");
		this.initToggle("showResolved");
	},
	initToggle : function(id) {
		if (this.model.get(id)) {
			$('button#' + id).button("toggle");
		}
	},
	events : {
		"click button#showActive" : "toggleActive",
		"click button#showResolved" : "toggleResolved",
		"click button#showDeleted" : "toggleDeleted",
		"click button#saveSetting" : "saveSettings",
	},
	toggleActive : function() {
		this.toggleState('showActive');
	},
	toggleResolved : function() {
		this.toggleState('showResolved');
	},
	toggleDeleted : function() {
		this.toggleState('showDeleted');
	},
	toggleState : function(id) {
		var $btn = $('button#' + id);
		var state = $btn.data("value");
		$btn.data("value", !state);
		console.log(id + ' ' + $btn.data("value"))
	},
	saveSettings : function() {
		this.model.set("showActive", $('button#showActive').data("value"));
		this.model.set("showResolved", $('button#showResolved').data("value"));
		this.model.set("showDeleted", $('button#showDeleted').data("value"));
		$("#waitModal").modal();
		this.model.save(this.model.toJSON(), {
			success : function(model, response) {
				location.reload();
			},
			error : function(model, response) {
			}
		})
	}
});
