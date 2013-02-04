package webplus.ezbacklog.model;

public class SiteNav {
	public static final String BEAN_NAME = "SiteNav";
	private boolean isDashboard;
	private boolean isProject;
	private boolean isAbout;
	private boolean isSetting;
	private boolean authenticated;

	private String userName;

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getUserName() {
		return userName;
	}

	public boolean isDashboard() {
		return isDashboard;
	}

	public void setDashboard(boolean isDashboard) {
		this.isDashboard = isDashboard;
	}

	public boolean isProject() {
		return isProject;
	}

	public void setProject(boolean isProject) {
		this.isProject = isProject;
	}

	public boolean isAbout() {
		return isAbout;
	}

	public void setAbout(boolean isAbout) {
		this.isAbout = isAbout;
	}

	public boolean isSetting() {
		return isSetting;
	}

	public void setSetting(boolean isSetting) {
		this.isSetting = isSetting;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}
