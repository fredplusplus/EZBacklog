package webplus.ezbacklog.module;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Calendar;

import javax.jdo.PersistenceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.HtmlUtils;

import webplus.ezbacklog.exceptions.DBException;
import webplus.ezbacklog.exceptions.ValidationException;
import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.service.PMF;

public class ItemUpdateModuleImpl implements ItemUpdateModule {

	@Autowired
	private BackloggerModule backloggerModule;
	@Autowired
	private ItemDisplayModule itemDisplayModule;

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
		item.setLongDescription(HtmlUtils.htmlEscape(item.getLongDescription()));
		item.setShortDescription(HtmlUtils.htmlEscape(item.getShortDescription()));
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(item);
		} catch (Exception e) {
			throw new DBException(e);
		} finally {
			pm.close();
		}
	}

	@Override
	public void createItem(Item item) {
		item.setCreationDate(Calendar.getInstance().getTime());
		item.setId(null);
		item.setOwnerEmail(backloggerModule.getCurrencyBacklogger().getEmail());
		validateCreation(item);
		saveItem(item);
	}

	private void validateCreation(Item item) {
		Long parentId = item.getParentId();
		try {
			if (parentId != null) {
				if (parentId <= 0) {
					item.setParentId(null);
				} else {
					Item parentItem = itemDisplayModule.getItemById(parentId);
					checkNotNull(parentItem, "There is no valid parent item to attach to.");
				}
			}
		} catch (Exception e) {
			throw new ValidationException(e);
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
