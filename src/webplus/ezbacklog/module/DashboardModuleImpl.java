package webplus.ezbacklog.module;

import static webplus.ezbacklog.values.ActivityType.Create;
import static webplus.ezbacklog.values.ActivityType.Delete;
import static webplus.ezbacklog.values.ActivityType.Reopen;
import static webplus.ezbacklog.values.ActivityType.Resolve;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import webplus.ezbacklog.model.Activity;
import webplus.ezbacklog.model.Backlogger;
import webplus.ezbacklog.model.Dashboard;
import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.model.ItemAggregation;
import webplus.ezbacklog.module.interfaces.ActivityUpdateModule;
import webplus.ezbacklog.module.interfaces.BackloggerModule;
import webplus.ezbacklog.module.interfaces.DashboardModule;
import webplus.ezbacklog.module.interfaces.ItemDisplayModule;
import webplus.ezbacklog.values.ItemLevel;

public class DashboardModuleImpl implements DashboardModule {
	@Autowired
	private BackloggerModule backloggerModule;
	@Autowired
	private ItemDisplayModule itemDisplayModule;
	@Autowired
	private ActivityUpdateModule activityModule;
	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;

	private final String[] milestoneHeader = { "dashboard.chart.haxis", "dashboard.milestone.create",
			"dashboard.milestone.resolve", "dashboard.milestone.reopen", "dashboard.milestone.delete" };

	@Override
	public Dashboard getDashboard() {
		Dashboard dashboard = new Dashboard();
		dashboard.setBacklogger(backloggerModule.getCurrencyBacklogger());
		dashboard.setItemAggregation(getItemAggregationByLevel(backloggerModule.getCurrencyBacklogger()
				.getOverviewlevel()));
		Calendar startTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		startTime.add(Calendar.YEAR, -1);
		List<Activity> creates = activityModule.getActivty(Create, startTime.getTime(), endTime.getTime());
		List<Activity> resolves = activityModule.getActivty(Resolve, startTime.getTime(), endTime.getTime());
		List<Activity> reopens = activityModule.getActivty(Reopen, startTime.getTime(), endTime.getTime());
		List<Activity> deletes = activityModule.getActivty(Delete, startTime.getTime(), endTime.getTime());
		List<List<Object>> milestones = makeMilestoneHistory(creates, reopens, resolves, deletes, endTime.getTime());

		dashboard.setMilestones(milestones);
		return dashboard;
	}

	@Override
	public ItemAggregation getItemAggregationByParentId(long parentId) {
		Item parent = null;
		if (parentId > 0) {
			parent = itemDisplayModule.getItemById(parentId);
		}
		List<Item> items = itemDisplayModule.getItemByParentId(parentId);

		return makeItemAggregation(ItemLevel.PROJECT, parent, items);
	}

	public ItemAggregation getItemAggregationByLevel(Long level) {
		List<Item> items = itemDisplayModule.getItemByLevel(level);
		return makeItemAggregation(level, null, items);
	}

	private ItemAggregation makeItemAggregation(Long level, Item parent, List<Item> items) {
		ItemAggregation aggregation = new ItemAggregation();
		if (parent == null) {
			aggregation.setItemLevel(level);
		} else {
			aggregation.setItemLevel(parent.getItemLevel() + 1L);
			aggregation.setParentId(parent.getId());
			aggregation.setTeamId(parent.getTeamId());
			aggregation.setTeamName(parent.getTeamName());
			aggregation.setGrandParentId(parent.getParentId());
			aggregation.setParentShortDescription(parent.getShortDescription());
			aggregation.setParentStatus(parent.getStatus());
		}
		if (items != null) {
			for (Item item : items) {
				aggregation.addStatus(item.getStatus());
				aggregation.addPoint(item.getStatus(), item.getPoint());
			}
		}
		return aggregation;

	}

	private List<List<Object>> makeMilestoneHistory(List<Activity> creates, List<Activity> reopen,
			List<Activity> resolve, List<Activity> delete, Date endTime) {
		int endWeek = weekOfYear(endTime);
		int startWeek = endWeek + 1;
		endWeek += 52;
		List<List<Object>> milestones = makeHeader(milestoneHeader);
		Map<Integer, Integer> createData = makeData(creates);
		Map<Integer, Integer> reopenData = makeData(reopen);
		Map<Integer, Integer> resolveData = makeData(resolve);
		Map<Integer, Integer> deleteData = makeData(delete);
		Locale displayLocale = backloggerModule.getCurrencyBacklogger().getDisplayLocale();
		boolean haveAnything = false;
		boolean foundTrueStart = false;
		for (int i = startWeek; i <= endWeek; i++) {
			List<Object> dataRow = new ArrayList<Object>();
			int week = i % 52;
			if (week == 0) {
				week = 52;
			}
			dataRow.add(messageSource.getMessage("dashboard.chart.haxis", null, displayLocale) + week);
			dataRow.add(createData.get(week));
			dataRow.add(resolveData.get(week));
			dataRow.add(reopenData.get(week));
			dataRow.add(deleteData.get(week));
			if (!foundTrueStart && createData.get(week) == 0 && resolveData.get(week) == 0 && reopenData.get(week) == 0
					&& deleteData.get(week) == 0) {
				continue;
			}
			milestones.add(dataRow);
			foundTrueStart = true;
			haveAnything = true;
		}
		if (!haveAnything) {
			List<Object> dataRow = new ArrayList<Object>();
			dataRow.add(messageSource.getMessage("dashboard.chart.haxis", null, displayLocale) + weekOfYear(endTime));
			dataRow.add(0);
			dataRow.add(0);
			dataRow.add(0);
			dataRow.add(0);
			milestones.add(dataRow);
		}
		return milestones;
	}

	private List<List<Object>> makeHeader(String[] headerKey) {
		Backlogger user = backloggerModule.getCurrencyBacklogger();
		List<List<Object>> list = new ArrayList<List<Object>>();
		List<Object> header = new ArrayList<Object>();
		list.add(header);
		for (String key : headerKey) {
			header.add(messageSource.getMessage(key, null, user.getDisplayLocale()));
		}
		return list;
	}

	/**
	 * Make a map of week, number. number by default is point, unless
	 * byCount==true
	 * 
	 * @param acts
	 * @param byCount
	 * @return
	 */
	private Map<Integer, Integer> makeData(List<Activity> acts) {
		Map<Integer, Integer> data = new HashMap<Integer, Integer>();
		for (int i = 0; i < 53; i++) {
			data.put(i, 0);
		}
		for (Activity act : acts) {
			int week = weekOfYear(act.getTime());
			data.put(week, data.get(week) + 1);
		}
		return data;
	}

	private int weekOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
}
