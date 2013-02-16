package webplus.ezbacklog.service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NotificationService {

	public void sendRemoveMemberNotification(String email, String teamName) {
		sendEmail(email, "Team Member Removed", email + " is removed from team " + teamName);
	}

	public void sendAddMemberNotification(String email, String teamName) {
		sendEmail(email, "Team Member Added", email + " is added to team " + teamName);
	}

	private void sendEmail(String recipient, String subject, String message) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("lean.backlog.noreply@example.com", "{Lean Backlog} Admin"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient, recipient));
			msg.setSubject(subject);
			msg.setText(message);
			Transport.send(msg);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
	}
}
