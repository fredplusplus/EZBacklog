package webplus.ezbacklog.values;

import java.io.Serializable;

public enum ActivityType implements Serializable {
	Update, // edit item or change status
	Create, // create item
	Comment, // comment item
	Unknown;

	public static ActivityType fromString(String str) {
		for (ActivityType type : ActivityType.values()) {
			if (type.name().equalsIgnoreCase(str)) {
				return type;
			}
		}
		return ActivityType.Unknown;
	}
}
