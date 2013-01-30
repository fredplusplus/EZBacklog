package webplus.ezbacklog.module.interfaces;

import java.util.List;

import webplus.ezbacklog.model.Activity;
import webplus.ezbacklog.model.Item;

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
	 * Adds an activity to the audit table.
	 * 
	 * @param activity
	 *            {@link Activity}
	 */
	void addCreationAcitivity(Item item);

	/**
	 * Adds a burndown actvity.
	 * 
	 * @param item
	 */
	void addUpdateActivity(Item item);

	/**
	 * Save an activity.
	 * 
	 * @param act
	 */
	void saveActivity(Activity act);
}
