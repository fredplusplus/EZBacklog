package webplus.ezbacklog.module.interfaces;

import webplus.ezbacklog.model.Activity;
import webplus.ezbacklog.model.Item;

/**
 * Logic for updating activities for each item.
 * 
 */
public interface ActivityUpdateModule {
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
}
