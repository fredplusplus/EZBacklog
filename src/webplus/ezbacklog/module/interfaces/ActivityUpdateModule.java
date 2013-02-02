package webplus.ezbacklog.module.interfaces;

import java.util.Date;
import java.util.List;

import webplus.ezbacklog.model.Activity;
import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.values.ActivityType;

/**
 * Logic for updating activities for each item.
 * 
 */
public interface ActivityUpdateModule {

	/**
	 * loads audit trail for an item.
	 * 
	 * @param itemId
	 * @return
	 */
	List<Activity> getAuditTrails(Long itemId);

	/**
	 * Gets all activity of a specific type in the past year.
	 * 
	 * @param types
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Activity> getActivty(ActivityType type, Date startDate, Date endDate);

	/**
	 * Adds an activity to the audit table.
	 */
	void addCreationAcitivity(Item item);

	/**
	 * Adds a burndown actvity.
	 * 
	 */
	void addUpdateActivity(Item oldItem, Item newItem);

	/**
	 * Adds a delete activity.
	 * 
	 * @param item
	 */
	void addDeleteActivity(Item item);

	/**
	 * Save an activity.
	 * 
	 * @param act
	 */
	void saveActivity(Activity act);
}
