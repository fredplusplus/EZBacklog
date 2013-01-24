package webplus.ezbacklog.module;

import java.util.List;

import webplus.ezbacklog.model.Item;

/**
 * Item update logic.
 * 
 */
public interface ItemUpdateModule {
	/**
	 * Returns a list of item on a certain leve. All items are owned by the
	 * current user.
	 * 
	 * @param parentId
	 *            optional parent id.
	 * @return
	 */
	List<Item> getItemByParentId(long parentId);

	/**
	 * Gets an item with specific Id. It must be owned by the current user.
	 * 
	 * @param id
	 *            item id.
	 * @return
	 */
	Item getItemById(Long id);

	/**
	 * Stores an item. If the item exists, it must be owned by the current user.
	 * 
	 * @param item
	 *            {@link Item}
	 */
	void saveItem(Item item);
}
