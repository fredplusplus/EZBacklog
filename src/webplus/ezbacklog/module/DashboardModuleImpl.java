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
		dashboard.setItemAggregation(getItemAggregationByParentId(ItemLevel.PROJECT, -1));
		return dashboard;
	}

	@Override
	public ItemAggregation getItemAggregationByParentId(long itemLevel, long parentId) {
		List<Item> items = itemDisplayModule.getItemByParentId(parentId);
		ItemAggregation aggregation = new ItemAggregation();
		aggregation.setItemLevel(itemLevel);
		if (items != null) {
			for (Item item : items) {
				aggregation.addStatus(item.getStatus());
				aggregation.addPoint(item.getStatus(), item.getPoint());
			}
		}
		return aggregation;
	}

}
