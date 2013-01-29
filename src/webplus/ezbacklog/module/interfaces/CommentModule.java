package webplus.ezbacklog.module.interfaces;

import java.util.List;

import webplus.ezbacklog.model.Activity;

/**
 * Logic for commenting.
 * 
 */
public interface CommentModule {

	/**
	 * Load all coments for an item.
	 * 
	 * @param itemId
	 * @return
	 */
	List<Activity> loadComments(Long itemId);

	/**
	 * Add a comment to an item.
	 * 
	 * @param comment
	 */
	void addComment(Activity comment);
}
