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
		List<Item> items = itemDisplayModule.getItemByParentId(parentId);
		ItemAggregation aggregation = new ItemAggregation();
		if (items == null || items.isEmpty()) {
			aggregation.setItemLevel(ItemLevel.PROJECT);
		} else {
			aggregation.setItemLevel(items.get(0).getItemLevel());
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
