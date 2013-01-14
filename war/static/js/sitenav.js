SiteNavView = Backbone.View.extend({
	initialize : function() {
		this.template = _.template($("#sitenavTemplate").html());
		_.bindAll(this, "render");
		this.model.bind('change', this.render, this);
		this.render();
	},
	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	}
});

SiteNavModel = Backbone.Model.extend({
	initialize: function() {
	}
});