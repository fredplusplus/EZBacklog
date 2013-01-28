RelatedItemModel = Backbone.Model.extend({
	urlRoot : '/f/relatedItems',
	initialize : function() {
	}
});

RelatedItemCollection = Backbone.Collection.extend({
	url : function() {
		return '/f/relatedItems/' + this.itemId;
	},
	model : RelatedItemModel
});

/**
 * the view. contains an Item and a RelatedItemCollection. Maintains
 * relationship between the 2.
 */
RelatedItemView = Backbone.View.extend({
	events : {
		"click button#addALink" : "addALink",
	},
	initialize : function() {
		this.$el = $("#relatedItemContainer");
		_.bindAll(this, "renderContent");
		this.collection = new RelatedItemCollection();
		this.collection.url = "/f/relatedItems/" + this.model.get("id");
		this.collection.bind('add', this.renderContent, this);
		this.initialized = false;
		this.render();
	},
	render : function() {
		if (!this.initialized) {
			this.renderLoading();
			this.refreshRelated();
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
			this.template = _.template($("#relatedItemTemplate").html());
			if (typeof (this.collection) != 'undefined') {
				this.$el.html(this.template({
					relatedItems : this.collection.toJSON()
				}));
			}
		}
	},
	refreshRelated : function() {
		this.collection.fetch({
			success : this.renderContent
		});
	},
	addALink : function() {
		if (this.model.get("selected")) {
			var link = $('input#addALinkInput').val();
			// TODO: validate
			var relatedItem = new RelatedItemModel({
				"relatedItem" : link,
				"itemId" : this.model.get("id")
			});
			this.collection.add(relatedItem);
			relatedItem.save(relatedItem.toJSON());
		}
	}
});