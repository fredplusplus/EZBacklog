package webplus.ezbacklog.model;

import java.util.Date;
import java.util.Locale;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * A user model of lean backlog.
 * 
 */
@PersistenceCapable
public class Backlogger {
	public static final String MODEL = "Backlogger";
	@PrimaryKey
	@Persistent
	private String email;
	@Persistent
	private Date joinDate;
	@Persistent
	private boolean showActive;
	@Persistent
	private boolean showResolved;
	@Persistent
	private boolean showDeleted;
	@Persistent
	private String displayLocale;
	@Persistent
	private Long overviewlevel;
	@Persistent
	private boolean usePoint;

	public String getEmail() {
		return email;
	}

	public Long getOverviewlevel() {
		return overviewlevel;
	}

	public void setOverviewlevel(Long overviewlevel) {
		this.overviewlevel = overviewlevel;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public boolean getShowActive() {
		return showActive;
	}

	public void setShowActive(boolean showActive) {
		this.showActive = showActive;
	}

	public boolean getShowResolved() {
		return showResolved;
	}

	public void setShowResolved(boolean showResolved) {
		this.showResolved = showResolved;
	}

	public boolean getShowDeleted() {
		return showDeleted;
	}

	public void setShowDeleted(boolean showDeleted) {
		this.showDeleted = showDeleted;
	}

	public boolean isUsePoint() {
		return usePoint;
	}

	public void setUsePoint(boolean usePoint) {
		this.usePoint = usePoint;
	}

	public void setDisplayLocale(Locale displayLocale) {
		this.displayLocale = displayLocale.getLanguage();
	}

	public Locale getDisplayLocale() {
		return new Locale(displayLocale);
	}

}
