package webplus.ezbacklog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * A team class.
 * 
 */
@PersistenceCapable
public class Team {

	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@PrimaryKey
	private Long id;
	@Persistent
	private String name;
	@Persistent
	private Date createTime;

	private List<TeamMember> members;

	public Team() {
		members = new ArrayList<TeamMember>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TeamMember> getMembers() {
		return members;
	}

	public void addMember(TeamMember member) {
		if (member != null) {
			members.add(member);
		}
	}

}
