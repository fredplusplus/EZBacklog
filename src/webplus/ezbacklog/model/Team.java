package webplus.ezbacklog.model;

import java.util.List;

public class Team implements Comparable<Team> {
	private Long id;
	private TeamName teamName;
	private List<TeamMember> members;

	public TeamName getTeamName() {
		return teamName;
	}

	public void setTeamName(TeamName teamName) {
		this.teamName = teamName;
		this.id = teamName.getId();
	}

	public Long getId() {
		return this.id;
	}

	public List<TeamMember> getMembers() {
		return members;
	}

	public void setMembers(List<TeamMember> members) {
		this.members = members;
	}

	@Override
	public int compareTo(Team o) {
		if (o == null || o.teamName == null || o.teamName.getName() == null) {
			return 1;
		} else if (teamName == null || teamName.getName() == null) {
			return -1;
		} else {
			return teamName.getName().compareTo(o.teamName.getName());
		}
	}

}
