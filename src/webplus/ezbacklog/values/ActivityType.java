package webplus.ezbacklog.values;

import java.io.Serializable;

public enum ActivityType implements Serializable {
	Update, // edit item or change status
	Create, // create item
	Burndown, // burn down item
	Comment, // comment item
	Resolve, // resolve item
	Reopen, // reopen item
	Delete, //delete item
	Unknown;
}
