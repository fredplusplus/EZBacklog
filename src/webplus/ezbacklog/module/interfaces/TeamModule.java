package webplus.ezbacklog.module.interfaces;

import java.util.List;

import webplus.ezbacklog.model.Team;

/**
 * Logic for interacting with team.
 * 
 */
public interface TeamModule {
	/**
	 * 
	 * @return all tams that the current user is in.
	 */
	List<Team> getAllTeamsForCurrentUser();
}
