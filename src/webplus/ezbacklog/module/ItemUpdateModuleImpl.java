package webplus.ezbacklog.module;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Calendar;
import java.util.List;

import javax.jdo.PersistenceManager;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.exceptions.DBException;
import webplus.ezbacklog.exceptions.ValidationException;
import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.module.interfaces.BackloggerModule;
import webplus.ezbacklog.module.interfaces.ItemDisplayModule;
import webplus.ezbacklog.module.interfaces.ItemUpdateModule;
import webplus.ezbacklog.service.PMF;
import webplus.ezbacklog.service.StringNormalizer;

public class ItemUpdateModuleImpl implements ItemUpdateModule {

	@Autowired
	private BackloggerModule backloggerModule;
	@Autowired
	private ItemDisplayModule itemDisplayModule;
	@Autowired
	private StringNormalizer stringNormalizer;

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
		item.setLongDescription(stringNormalizer.normalize(item.getLongDescription()));
		item.setShortDescription(stringNormalizer.normalize(item.getShortDescription(), 40));
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

	@Override
	public void clearTeamAndReassignOwner(String email, List<Item> items) {
		if (items != null && email != null) {
			for (Item item : items) {
				item.setTeamId(null);
				item.setTeamName(null);
				item.setOwnerEmail(email);
				saveItem(item);
			}
		}
	}

}
