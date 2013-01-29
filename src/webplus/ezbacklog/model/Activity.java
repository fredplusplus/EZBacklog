package webplus.ezbacklog.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import webplus.ezbacklog.values.ActivityType;

@PersistenceCapable
public class Activity {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String userEmail;
	@Persistent
	private Date time;
	@Persistent
	private long itemId;
	@Persistent
	private long resolvedPoint;
	@Persistent
	private String description;
	@Persistent
	private String activityType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public long getResolvedPoint() {
		return resolvedPoint;
	}

	public void setResolvedPoint(long point) {
		this.resolvedPoint = point;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ActivityType getActivityType() {
		return ActivityType.fromString(activityType);
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType.name();
	}

}
