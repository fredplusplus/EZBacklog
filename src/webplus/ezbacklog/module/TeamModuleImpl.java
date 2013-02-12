package webplus.ezbacklog.module;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.exceptions.DBException;
import webplus.ezbacklog.model.Team;
import webplus.ezbacklog.model.TeamMember;
import webplus.ezbacklog.model.TeamName;
import webplus.ezbacklog.module.interfaces.BackloggerModule;
import webplus.ezbacklog.module.interfaces.TeamModule;
import webplus.ezbacklog.service.PMF;
import webplus.ezbacklog.values.Role;

public class TeamModuleImpl implements TeamModule {

	@Autowired
	private BackloggerModule backloggerModule;

	@Override
	public List<Team> getAllTeamsForCurrentUser(boolean fullInfo) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(TeamMember.class);
		query.setFilter(String.format("userEmail == '%s'", backloggerModule.getCurrencyBacklogger().getEmail()));
		List<TeamMember> members = (List<TeamMember>) query.execute();
		if (members != null) {
			// separate teams
			Map<Long, List<TeamMember>> memberMap = new HashMap<Long, List<TeamMember>>();
			for (TeamMember member : members) {
				List<TeamMember> membersFromTeam = memberMap.get(member.getTeamId());
				if (membersFromTeam == null) {
					membersFromTeam = new ArrayList<TeamMember>();
				}
				membersFromTeam.add(member);
				memberMap.put(member.getTeamId(), membersFromTeam);
			}
			// get team for each group of member
			List<Team> teams = new ArrayList<Team>();
			for (Entry<Long, List<TeamMember>> membersFromTeam : memberMap.entrySet()) {
				try {
					TeamName name = pm.detachCopy(pm.getObjectById(TeamName.class, membersFromTeam.getKey()));
					Team team = new Team();
					team.setTeamName(name);
					team.setMembers(membersFromTeam.getValue());
					teams.add(team);
					if (fullInfo) {
						getAllMemberExceptMe(team);
					}
				} catch (Exception e) {
					// team not found, skip.
				}
			}
			Collections.sort(teams);
			return teams;
		}
		return null;

	}

	@Override
	public void createTeam(TeamName name) {
		validateTeam(name);
		name.setCreateTime(Calendar.getInstance().getTime());
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(name);
			TeamMember member = new TeamMember();
			member.setRole(Role.Admin);
			member.setUserEmail(backloggerModule.getCurrencyBacklogger().getEmail());
			member.setTeamId(name.getId());
			addMember(member);
		} catch (Exception e) {
			throw new DBException(e);
		} finally {
			pm.close();
		}
	}

	@Override
	public void addMember(TeamMember member) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(member);
		} catch (Exception e) {
			throw new DBException(e);
		} finally {
			pm.close();
		}
	}

	@Override
	public void removeMember(Long id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.deletePersistent(pm.getObjectById(TeamMember.class, id));
		} catch (JDOObjectNotFoundException e) {
		} finally {
			pm.close();
		}
		return;
	}

	private void getAllMemberExceptMe(Team team) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(TeamMember.class);
		String filter = String.format("teamId == %s && userEmail != '%s'", team.getId(), backloggerModule
				.getCurrencyBacklogger().getEmail());
		query.setFilter(filter);
		List<TeamMember> members = (List<TeamMember>) query.execute();
		team.getMembers().addAll(members);
	}

	private void validateTeam(TeamName team) {
		checkNotNull(team);
		checkNotNull(team.getName());
		team.setId(null);
	}

}
