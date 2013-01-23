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
	private ItemUpdateModule itemUpdateModule;

	@Override
	public Dashboard getDashboard() {
		Dashboard dashboard = new Dashboard();
		dashboard.setBacklogger(backloggerModule.getCurrencyBacklogger());
		dashboard.setItemAggregation(getItemAggregation(ItemLevel.PROJECT));
		return dashboard;
	}

	@Override
	public ItemAggregation getItemAggregation(long itemLevel) {
		List<Item> items = itemUpdateModule.getItemByLevel(itemLevel, -1l);
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
