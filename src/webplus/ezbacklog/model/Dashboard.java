package webplus.ezbacklog.model;

/**
 * Model for displaying dash board.
 * 
 */
public class Dashboard {
	public static final String MODEL = "Dashboard";

	private Backlogger backlogger;
	private ItemAggregation itemAggregation;

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
}
