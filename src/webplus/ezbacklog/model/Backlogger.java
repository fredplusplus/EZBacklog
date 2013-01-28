package webplus.ezbacklog.model;

import java.util.Date;
import java.util.Locale;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * A user model of ezbacklog.
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

	public String getEmail() {
		return email;
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

	public void setDisplayLocale(Locale displayLocale) {
		this.displayLocale = displayLocale.getLanguage();
	}

	public Locale getDisplayLocale() {
		return new Locale(displayLocale);
	}

}
