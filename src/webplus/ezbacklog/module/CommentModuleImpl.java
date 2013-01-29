package webplus.ezbacklog.module;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.exceptions.ValidationException;
import webplus.ezbacklog.model.Activity;
import webplus.ezbacklog.module.interfaces.ActivityUpdateModule;
import webplus.ezbacklog.module.interfaces.BackloggerModule;
import webplus.ezbacklog.module.interfaces.CommentModule;
import webplus.ezbacklog.service.PMF;
import webplus.ezbacklog.values.ActivityType;

public class CommentModuleImpl implements CommentModule {

	@Autowired
	private BackloggerModule backloggerModule;
	@Autowired
	private ActivityUpdateModule activityUpdateModule;

	@Override
	public List<Activity> loadComments(Long itemId) {
		if (itemId == null || itemId <= 0) {
			return null;
		}
		String filter = String.format("itemId == %d && activityType == 'Comment'", itemId);
		return query(filter);
	}

	@Override
	public void addComment(Activity comment) {
		validate(comment);
		comment.setActivityType(ActivityType.Comment);
		activityUpdateModule.saveActivity(comment);
	}

	private void validate(Activity comment) {
		try {
			checkNotNull(comment.getItemId());
			checkArgument(comment.getItemId() > 0, "The item id is invalid");
		} catch (Exception e) {
			throw new ValidationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private List<Activity> query(String filter) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Activity.class);
		query.setFilter(filter);
		return (List<Activity>) query.execute();
	}
}
