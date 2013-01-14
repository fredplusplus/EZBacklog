ItemAggregationView = Backbone.View.extend({
	initialize : function() {
		this.template = _.template($("#itemAggregationTemplate").html());
		_.bindAll(this, "render");
		this.model.bind('change', this.render, this);
		this.render();
	},
	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	}
});

ItemAggregationModel = Backbone.Model.extend({
	initialize : function() {
	}
});