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
	 * @return all tams that the current user is in.
	 */
	List<Team> getAllTeamsForCurrentUser();

	void createTeam(TeamName teamName);

	void addMember(TeamMember member);
}
