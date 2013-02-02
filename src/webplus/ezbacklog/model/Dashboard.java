package webplus.ezbacklog.model;

import java.util.List;

/**
 * Model for displaying dash board.
 * 
 */
public class Dashboard {
	public static final String MODEL = "Dashboard";

	private Backlogger backlogger;
	private ItemAggregation itemAggregation;
	private List<List<Object>> milestones;

	public List<List<Object>> getMilestones() {
		return milestones;
	}

	public Backlogger getBacklogger() {
		return backlogger;
	}

	public void setBacklogger(Backlogger backlogger) {
		this.backlogger = backlogger;
	}

	public ItemAggregation getItemAggregation() {
		return itemAggregation;
	}

	public void setItemAggregation(ItemAggregation itemAggregation) {
		this.itemAggregation = itemAggregation;
	}

	public void setMilestones(List<List<Object>> milestones) {
		this.milestones = milestones;
	}
}
