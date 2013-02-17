package webplus.ezbacklog.module.interfaces;

import java.util.List;

import webplus.ezbacklog.model.Item;

/**
 * This module contains logic to query and display items.
 * 
 */
public interface ItemDisplayModule {

	/**
	 * Returns a list of item on a certain level. All items owned by the current
	 * user.
	 * 
	 * @param level
	 * @return
	 */
	List<Item> getItemByLevel(long level);

	/**
	 * Returns a list of item on under the same parent. All items are owned by
	 * the current user.
	 * 
	 * @param parentId
	 *            optional parent id.
	 * @return
	 */
	List<Item> getItemByParentId(long parentId);

	/**
	 * Returns all item from the same team.
	 */
	List<Item> getItemByTeam(long teamId);

	/**
	 * Gets an item with specific Id. It must be owned by the current user.
	 * 
	 * @param id
	 *            item id.
	 * @return
	 */
	Item getItemById(Long id);
}
