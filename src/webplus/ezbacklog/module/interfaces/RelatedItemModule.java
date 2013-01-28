package webplus.ezbacklog.module.interfaces;

import java.util.List;

import webplus.ezbacklog.model.RelatedItem;

/**
 * Logic for fetching/updating related items.
 */
public interface RelatedItemModule {

	/**
	 * Fetch all related items for an item id.
	 * 
	 * @param itemId
	 * @return
	 */
	List<RelatedItem> fecthRelatedItems(Long itemId);

	/**
	 * Add a related item to an item.
	 * 
	 * @param relatedItem
	 */
	void addRelatedItem(RelatedItem relatedItem);
}
