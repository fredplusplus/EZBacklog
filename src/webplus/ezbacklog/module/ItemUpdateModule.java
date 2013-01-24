package webplus.ezbacklog.module;

import webplus.ezbacklog.model.Item;

/**
 * Item update logic.
 * 
 */
public interface ItemUpdateModule {

	/**
	 * Stores an item. If the item exists, it must be owned by the current user.
	 * 
	 * @param item
	 *            {@link Item}
	 */
	void saveItem(Item item);
}
