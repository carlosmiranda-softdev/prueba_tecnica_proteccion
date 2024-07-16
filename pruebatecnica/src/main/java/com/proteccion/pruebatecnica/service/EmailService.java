package com.proteccion.pruebatecnica.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Service for sending an email
 */
@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * Sends an email to the specified recipient with the given subject and text content.
     *
     * @param to      An array of email addresses to send.
     * @param subject The subject of the email.
     * @param text    The text content of the email.
     * @throws MessagingException If an error occurs during the message creation or sending.
     */
    public void sendEmail(String[] to, String subject, String text) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("sender@example.com");
        for (String recipient : to) {
            helper.addTo(recipient);
        }
        helper.setSubject(subject);
        helper.setText(text);

        javaMailSender.send(message);
    }
}
