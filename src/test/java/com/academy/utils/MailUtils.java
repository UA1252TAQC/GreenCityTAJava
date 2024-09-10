package com.academy.utils;

import com.academy.utils.mail.Mail;
import com.academy.utils.mail.MailBoxCredentials;
import com.academy.utils.mail.MailHttpClient;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public class MailUtils {
	private static final Long TIMEOUT_MILLIS = 30000L;
	private final MailHttpClient mailHttpClient;

	public MailUtils() {
		this.mailHttpClient = new MailHttpClient();
	}

	public MailBoxCredentials getUsedAccount() {
		try {
			List<MailBoxCredentials> inboxes = mailHttpClient.getAllInboxes();
			return findValidInbox(inboxes).orElseGet(this::createNewMailCredentials);
		} catch (Exception e) {
			throw new RuntimeException("Error while getting mail credentials: " + e.getMessage());
		}
	}

	private Optional<MailBoxCredentials> findValidInbox(List<MailBoxCredentials> inboxes) {
		return inboxes.stream().filter(inbox -> {
			if (inbox.getExpiresAt() == null) return false;
			ZonedDateTime expirationDate = ZonedDateTime.parse(inbox.getExpiresAt());
			ZonedDateTime nowPlusHour = ZonedDateTime.now(ZoneOffset.UTC).plusHours(1);
			return expirationDate.isAfter(nowPlusHour);
		}).findFirst();
	}

	public MailBoxCredentials createNewMailCredentials() {
		try {
			return mailHttpClient.createInbox();
		} catch (Exception e) {
			throw new RuntimeException("Error while creating new inbox: " + e.getMessage());
		}
	}

	public Mail getLastEmail(String inboxId) {
		try {
			return mailHttpClient.getLastMail(inboxId, TIMEOUT_MILLIS);
		} catch (Exception e) {
			throw new RuntimeException("Error while retrieving last email: " + e.getMessage());
		}
	}
}
