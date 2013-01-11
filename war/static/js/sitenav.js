_.templateSettings = {
	interpolate : /\<\@\=(.+?)\@\>/gim,
	evaluate : /\<\@([\s\S]+?)\@\>/gim,
	escape : /\<\@\-(.+?)\@\>/gim
};

SiteNav = Backbone.View.extend({
	initialize : function() {
		this.render();
	},
	render : function() {
		var siteNavTemplate = _.template($("#sitenavTemplate").html(),
				siteNavData);
		this.$el.html(siteNavTemplate);
	}
});