package webplus.ezbacklog.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import webplus.ezbacklog.values.Role;

/**
 * A team member class.
 * 
 */
@PersistenceCapable
public class TeamMember {
	@Persistent
	@PrimaryKey
	private String userEmail;
	@Persistent
	private Long teamId;
	@Persistent
	private Role role;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

}
