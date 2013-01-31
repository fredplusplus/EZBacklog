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
	model : CommentModel,
	comparator : function(comment) {
		var d = new Date(comment.get("time"));
		return -d.getTime();
	}
});

/**
 * the view. contains an Item and a CommentCollection. Maintains relationship
 * between the 2.
 */
CommentView = Backbone.View.extend({
	events : {
		"click button#addAComment" : "addAComment",
	},
	initialize : function() {
		this.$el = $("#commentContainer");
		_.bindAll(this, "renderContent");
		this.collection = new CommentCollection();
		this.collection.url = "/f/comments/" + this.model.get("id");
		this.collection.bind('add', this.renderContent, this);
		this.initialized = false;
		this.render();
	},
	render : function() {
		if (!this.initialized) {
			this.renderLoading();
			this.refreshComment();
			this.initialized = true;
		} else {
			this.renderContent();
		}
	},
	renderLoading : function() {
		this.template = _.template($("#loadingTemplate").html());
		this.$el.html(this.template(this.model.toJSON()));
	},
	renderContent : function() {
		if (this.model.get("selected")) {
			this.template = _.template($("#commentTabTemplate").html());
			if (typeof (this.collection) != 'undefined') {
				this.$el.html(this.template({
					comments : this.collection.toJSON()
				}));
			}
		}
	},
	refreshComment : function() {
		this.collection.fetch({
			success : this.renderContent
		});
	},
	addAComment : function() {
		if (this.model.get("selected")) {
			var description = $('#commentInput').val();
			var localComment = new CommentModel({
				"time" : new Date(),
				"userEmail" : siteNavModel.get("userName"),
				"description" : description.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;"),
				"itemId" : this.model.get("id")
			});
			
			var remoteComment = new CommentModel({
				"userEmail" : siteNavModel.get("userName"),
				"description" : description,
				"itemId" : this.model.get("id")
			});
			remoteComment.save(remoteComment.toJSON());
			
			this.collection.add(localComment);
			
		}
	}
});