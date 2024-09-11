package com.academy.utils;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.academy.utils.mail.Mail;
import com.academy.utils.mail.MailBoxCredentials;
import com.academy.utils.mail.MailHttpClient;

public class MailUtils {
	private static final Long TIMEOUT_MILLIS = 30000L;
	private final MailHttpClient mailHttpClient;

	public MailUtils() {
		this.mailHttpClient = new MailHttpClient();
	}

	private MailBoxCredentials createNewMailCredentials() {
		try {
			return mailHttpClient.createInbox();
		} catch (Exception e) {
			throw new RuntimeException("Error while creating new inbox: " + e.getMessage());
		}
	}

	public Mail getLastEmail(UUID inboxId) {
		try {
			return mailHttpClient.getLastMail(inboxId.toString(), TIMEOUT_MILLIS);
		} catch (Exception e) {
			throw new RuntimeException("Error while retrieving last email: " + e.getMessage());
		}
	}
}
