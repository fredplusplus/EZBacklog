package webplus.ezbacklog.module;

import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.values.ActivityType;

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

	/**
	 * Creates an item. If the item parent is available, the current user must
	 * be the owner.
	 * 
	 * @param item
	 *            {@link Item}
	 */
	void createItem(Item item);
}
