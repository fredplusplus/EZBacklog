package webplus.ezbacklog.model;

import java.util.List;

public class Team {
	private TeamName teamName;
	private List<TeamMember> members;

	public TeamName getTeamName() {
		return teamName;
	}

	public void setTeamName(TeamName teamName) {
		this.teamName = teamName;
	}

	public List<TeamMember> getMembers() {
		return members;
	}

	public void setMembers(List<TeamMember> members) {
		this.members = members;
	}

}
