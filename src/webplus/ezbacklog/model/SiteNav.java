package webplus.ezbacklog.model;


public class SiteNav {
	public static final String BEAN_NAME = "SiteNav";
	private boolean isDashboard;
	private boolean isProject;
	private boolean isAbout;

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
}
