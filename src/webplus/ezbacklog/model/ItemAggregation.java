package webplus.ezbacklog.model;

import java.util.HashMap;
import java.util.Map;

import webplus.ezbacklog.values.ItemStatus;

/**
 * Model for aggregated view of a certain item type.
 * 
 */
public class ItemAggregation {
	public static final String MODEL = "ItemAggregation";
	private Long itemLevel;
	private Long parentId;
	private String parentShortDescription;
	private Long grandParentId;
	private ItemStatus parentStatus;
	private Map<ItemStatus, Long> statusMap;
	private Map<ItemStatus, Long> pointMap;

	public ItemAggregation() {
		statusMap = new HashMap<ItemStatus, Long>();
		pointMap = new HashMap<ItemStatus, Long>();

		for (ItemStatus s : ItemStatus.values()) {
			statusMap.put(s, 0l);
			pointMap.put(s, 0l);
		}
		parentShortDescription = "";
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getParentId() {
		return parentId;
	}

	public Long getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(Long level) {
		this.itemLevel = level;
	}

	public Map<ItemStatus, Long> getStatusMap() {
		return statusMap;
	}

	public Map<ItemStatus, Long> getPointMap() {
		return pointMap;
	}

	public void addStatus(ItemStatus status) {
		statusMap.put(status, statusMap.get(status) + 1);
	}

	public void addPoint(ItemStatus status, Long point) {
		if (point != null) {
			pointMap.put(status, pointMap.get(status) + point);
		}
	}

	public Long getGrandParentId() {
		return grandParentId;
	}

	public void setGrandParentId(Long grandParentId) {
		this.grandParentId = grandParentId;
	}

	public String getParentShortDescription() {
		return parentShortDescription;
	}

	public void setParentShortDescription(String parentShortDescription) {
		this.parentShortDescription = parentShortDescription;
	}

	public ItemStatus getParentStatus() {
		return parentStatus;
	}

	public void setParentStatus(ItemStatus parentStatus) {
		this.parentStatus = parentStatus;
	}
}
