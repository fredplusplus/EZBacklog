package webplus.ezbacklog.model;

/**
 * Model for displaying dash board.
 * 
 */
public class Dashboard {
	public static final String MODEL = "Dashboard";

	private Backlogger backlogger;

	public Backlogger getBacklogger() {
		return backlogger;
	}

	public void setBacklogger(Backlogger backlogger) {
		this.backlogger = backlogger;
	}
}
