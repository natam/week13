package org.practice.afternoon_classes.day_1;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("morozko.nnn@gmail.com", System.getenv("gmail_pw"));
            }
        };
        Session session = Session.getInstance(properties, authenticator);
        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("morozko.nnn@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("morozko.nnn@gmail.com"));
            message.setSubject("test from IDE");
            message.setText("my email text from maven project");
            Transport.send(message);
            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }
}
