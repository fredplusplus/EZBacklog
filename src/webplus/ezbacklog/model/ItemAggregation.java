package webplus.ezbacklog.model;

import java.util.HashMap;
import java.util.Map;

import webplus.ezbacklog.values.ItemStatus;

/**
 * Model for aggregated view of a certain item type.
 * 
 */
public class ItemAggregation {
	private long level;
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

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
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
