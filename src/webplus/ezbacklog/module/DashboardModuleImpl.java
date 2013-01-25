package webplus.ezbacklog.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.model.Dashboard;
import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.model.ItemAggregation;
import webplus.ezbacklog.values.ItemLevel;

public class DashboardModuleImpl implements DashboardModule {
	@Autowired
	private BackloggerModule backloggerModule;
	@Autowired
	private ItemDisplayModule itemDisplayModule;

	@Override
	public Dashboard getDashboard() {
		Dashboard dashboard = new Dashboard();
		dashboard.setBacklogger(backloggerModule.getCurrencyBacklogger());
		dashboard.setItemAggregation(getItemAggregationByParentId(-1));
		return dashboard;
	}

	@Override
	public ItemAggregation getItemAggregationByParentId(long parentId) {
		Item parent = null;
		ItemAggregation aggregation = new ItemAggregation();
		if (parentId > 0) {
			parent = itemDisplayModule.getItemById(parentId);
		}
		List<Item> items = itemDisplayModule.getItemByParentId(parentId);
		if (parent == null) {
			aggregation.setItemLevel(ItemLevel.PROJECT);
		} else {
			aggregation.setItemLevel(parent.getItemLevel() + 1L);
			aggregation.setParentId(parentId);
			aggregation.setGrandParentId(parent.getParentId());
		}
		if (items != null) {
			for (Item item : items) {
				aggregation.addStatus(item.getStatus());
				aggregation.addPoint(item.getStatus(), item.getPoint());
			}
		}
		return aggregation;
	}

}
