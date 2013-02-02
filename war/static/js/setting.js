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
		this.initLanguages([ "en", "zh" ]);
		this.initOverviewLevel([ 1, 2, 3 ]);
	},
	initToggle : function(id) {
		if (this.model.get(id)) {
			$('button#' + id).button("toggle");
		}
	},
	initOverviewLevel : function(levels) {
		this.overviewlevel = 1;
		for ( var i in levels) {
			if (this.model.get("overviewlevel") == levels[i]) {
				$('button#overviewlevel' + levels[i]).button("toggle");
				this.overviewlevel = levels[i];
				return;
			}
		}
	},
	initLanguages : function(langs) {
		this.displayLocale = 'en';
		for ( var i in langs) {
			if (this.model.get("displayLocale") == langs[i]) {
				$('button#' + langs[i]).button("toggle");
				this.displayLocale = langs[i];
				return;
			}
		}
	},
	events : {
		"click button#showActive" : "toggleActive",
		"click button#showResolved" : "toggleResolved",
		"click button#showDeleted" : "toggleDeleted",
		"click button#en" : "toggleEnglish",
		"click button#zh" : "toggleChinese",
		"click button#overviewlevel1" : "toggleOverviewlevel1",
		"click button#overviewlevel2" : "toggleOverviewlevel2",
		"click button#overviewlevel3" : "toggleOverviewlevel3",
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
	toggleEnglish : function() {
		this.displayLocale = 'en';
	},
	toggleChinese : function() {
		this.displayLocale = 'zh';
	},
	toggleOverviewlevel1 : function() {
		this.overviewlevel = 1;
	},
	toggleOverviewlevel2 : function() {
		this.overviewlevel = 2;
	},
	toggleOverviewlevel3 : function() {
		this.overviewlevel = 3;
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
		this.model.set("displayLocale", this.displayLocale);
		this.model.set("overviewlevel", this.overviewlevel);
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
