package webplus.ezbacklog.service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.module.interfaces.BackloggerModule;

public class NotificationService {
	private static final String ADD_TITLE = "Team member added";
	private static final String REMOVE_TITLE = "Team member removed";
	private static final String ADD_TEMPLATE = "%s is added to team %s by %s";
	private static final String REMOVED_TEMPLATE = "%s is removed from team %s by %s";

	@Autowired
	private BackloggerModule backlogger;

	public void sendRemoveMemberNotification(String email, String teamName) {
		sendEmail(email, REMOVE_TITLE,
				String.format(REMOVED_TEMPLATE, email, teamName, backlogger.getCurrencyBacklogger().getEmail()));
	}

	public void sendAddMemberNotification(String email, String teamName) {
		sendEmail(email, ADD_TITLE,
				String.format(ADD_TEMPLATE, email, teamName, backlogger.getCurrencyBacklogger().getEmail()));
	}

	private void sendEmail(String recipient, String subject, String message) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("lean.backlog.noreply@gmail.com", "{Lean Backlog} Admin"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient, recipient));
			msg.setSubject(subject);
			msg.setText(message);
			Transport.send(msg);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
	}
}
