CommentModel = Backbone.Model.extend({
	defaults : {
		userEmail : "",
		description : "",
		itemId : "",
	},
	urlRoot : '/f/comments',
	initialize : function() {
	}
});

CommentCollection = Backbone.Collection.extend({
	url : function() {
		return '/f/comments/' + this.itemId;
	},
	model : CommentModel
});