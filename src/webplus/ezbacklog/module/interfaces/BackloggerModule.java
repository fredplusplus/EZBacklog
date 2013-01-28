package webplus.ezbacklog.module.interfaces;

import webplus.ezbacklog.model.Backlogger;

public interface BackloggerModule {
	Backlogger getCurrencyBacklogger();

	void registerBacklogger();

	void updatePreference(Backlogger prefs);
}
