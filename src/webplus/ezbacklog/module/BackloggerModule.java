package webplus.ezbacklog.module;

import webplus.ezbacklog.model.Backlogger;

public interface BackloggerModule {
	Backlogger getCurrencyBacklogger();

	void registerBacklogger();

	void updatePreference(Backlogger prefs);
}
