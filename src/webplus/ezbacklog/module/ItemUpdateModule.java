package webplus.ezbacklog.module;

import java.util.List;

import webplus.ezbacklog.model.Item;

public interface ItemUpdateModule {
	List<Item> getItemByLevel(Long level, Long parentId);
	
	void updateItem(Item item);
}
