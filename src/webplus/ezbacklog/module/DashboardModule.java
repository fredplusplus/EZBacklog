package webplus.ezbacklog.module;

import webplus.ezbacklog.model.Dashboard;
import webplus.ezbacklog.model.ItemAggregation;

public interface DashboardModule {

	Dashboard getDashboard();

	ItemAggregation getItemAggregationByParentId(long parentId);
}
