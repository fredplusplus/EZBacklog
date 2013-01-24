package webplus.ezbacklog.module;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Calendar;

import javax.jdo.PersistenceManager;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.exceptions.DBException;
import webplus.ezbacklog.exceptions.ValidationException;
import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.service.PMF;

public class ItemUpdateModuleImpl implements ItemUpdateModule {

	@Autowired
	private BackloggerModule backloggerModule;

	@Override
	public void saveItem(Item item) {
		validate(item);
		item.setModifyDate(Calendar.getInstance().getTime());
		switch (item.getStatus()) {
		case Resolved:
			item.setResolveDate(Calendar.getInstance().getTime());
			break;
		default:
			item.setResolveDate(null);
			break;
		}
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(item);
		} catch (Exception e) {
			throw new DBException(e);
		} finally {
			pm.close();
		}
	}

	private void validate(Item item) {
		try {
			String ownerEmail = backloggerModule.getCurrencyBacklogger().getEmail();
			checkNotNull(item);
			checkNotNull(item.getOwnerEmail());
			checkArgument(item.getOwnerEmail().equals(ownerEmail),
					"This item owner is %s, it is not owned by current user %s.", item.getOwnerEmail(),
					item.getOwnerEmail());
		} catch (Exception e) {
			throw new ValidationException(e);
		}
	}
}
