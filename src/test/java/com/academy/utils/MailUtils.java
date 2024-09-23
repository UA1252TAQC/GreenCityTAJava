package com.academy.utils;

import com.academy.utils.mail.Mail;
import com.academy.utils.mail.MailBoxCredentials;
import com.academy.utils.mail.MailHttpClient;

import io.qameta.allure.Step;

public class MailUtils {
    private static final Long TIMEOUT_MILLIS = 30000L;
    private final MailHttpClient mailHttpClient;

    public MailUtils() {
        this.mailHttpClient = new MailHttpClient();
    }

    @Step("Create temporary mail box")
    public MailBoxCredentials createNewMailCredentials() {
        return mailHttpClient.createInbox();
    }

    @Step("Get last mail from inbox with id: {inboxId}")
    public Mail getLastEmail(String inboxId) {
        return mailHttpClient.getLastMail(inboxId, TIMEOUT_MILLIS);
    }
}
