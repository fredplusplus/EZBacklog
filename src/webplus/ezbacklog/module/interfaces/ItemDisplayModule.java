package webplus.ezbacklog.module.interfaces;

import java.util.List;

import webplus.ezbacklog.model.Item;

/**
 * This module contains logic to query and display items.
 * 
 */
public interface ItemDisplayModule {
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
}