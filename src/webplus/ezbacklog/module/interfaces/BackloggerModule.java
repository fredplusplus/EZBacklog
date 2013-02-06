package webplus.ezbacklog.module.interfaces;

import webplus.ezbacklog.model.Backlogger;

public interface BackloggerModule {
	Backlogger getCurrencyBacklogger();

	void registerBacklogger();

	void registerBotBacklogger();
	
	void updatePreference(Backlogger prefs);
	
	
}
