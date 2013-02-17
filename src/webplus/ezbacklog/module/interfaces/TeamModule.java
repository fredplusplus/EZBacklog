package webplus.ezbacklog.module.interfaces;

import java.util.List;

import webplus.ezbacklog.model.Team;
import webplus.ezbacklog.model.TeamMember;
import webplus.ezbacklog.model.TeamName;

/**
 * Logic for interacting with team.
 * 
 */
public interface TeamModule {
	/**
	 * @return all teams that the current user is in.
	 */
	List<Team> getAllTeamsForCurrentUser(boolean fullInfo);

	void createTeam(TeamName teamName);

	void addMember(TeamMember member);

	void removeMember(Long id);

	/**
	 * Delete a team and all members.
	 * 
	 * @param id
	 * @return the admin member email address
	 */
	String deleteTeam(Long id);
}
