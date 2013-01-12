package webplus.ezbacklog.model;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * A user model of ezbacklog.
 *
 */
@PersistenceCapable(detachable = "true")
public class Backlogger {

	@PrimaryKey 
	@Persistent private String email;
	
	@Persistent private Date joinDate;

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

}
