package com.academy.utils;

import com.academy.utils.mail.Mail;
import com.academy.utils.mail.MailBoxCredentials;
import com.academy.utils.mail.MailHttpClient;

public class MailUtils {
    private static final Long TIMEOUT_MILLIS = 30000L;
    private final MailHttpClient mailHttpClient;

    public MailUtils() {
        this.mailHttpClient = new MailHttpClient();
    }

    public MailBoxCredentials createNewMailCredentials() {
        return mailHttpClient.createInbox();
    }

    public Mail getLastEmail(String inboxId) {
        return mailHttpClient.getLastMail(inboxId, TIMEOUT_MILLIS);
    }
}
