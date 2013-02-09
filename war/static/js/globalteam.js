var CreateTeamModel = Backbone.Model.extend({

	defaults : {
		name : '',
	},
	initialize : function() {

	}
});

var CreateTeamView = Backbone.View.extend({
	initialize : function() {
		this.template = _.template($('#createTeamModalTemplate').html());
		this.render();
	},
	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
	}
});