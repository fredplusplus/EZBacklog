package webplus.ezbacklog.module;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.model.Dashboard;

public class DashboardModuleImpl implements DashboardModule {
	@Autowired BackloggerModule backloggerModule;

	@Override
	public Dashboard getDashboard() {
		Dashboard dashboard = new Dashboard();
		dashboard.setBacklogger(backloggerModule.getCurrencyBacklogger());
		return dashboard;
	}


}
