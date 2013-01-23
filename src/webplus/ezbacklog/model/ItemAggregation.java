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
	private long itemLevel;
	private long parentId;
	private Map<ItemStatus, Long> statusMap;
	private Map<ItemStatus, Long> pointMap;

	public ItemAggregation() {
		statusMap = new HashMap<ItemStatus, Long>();
		pointMap = new HashMap<ItemStatus, Long>();

		for (ItemStatus s : ItemStatus.values()) {
			statusMap.put(s, 0l);
			pointMap.put(s, 0l);
		}
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	
	public long getParentId() {
		return parentId;
	}
	
	public long getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(long level) {
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
		pointMap.put(status, pointMap.get(status) + point);
	}
}
