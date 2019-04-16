/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonoro.util;

import com.sonoro.model.Settings;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

/**
 *
 * @author ryanl
 */
public class EmailHandler {
    
    private Settings settings = Settings.getInstance();
    
    public void sendEmail(String sendTo, String subject, String emailBody) throws AddressException, MessagingException
    {
        Session session = Session.getDefaultInstance(getProperties(), null);
        MimeMessage message = new MimeMessage(session);
        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(sendTo));
        message.setSubject(subject);
        message.setContent(emailBody, "text/plain"); // text/html
        String clipName = settings.getClipName(); // this program always sends a clip as an attachment
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(emailBody);
        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(clipName);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(clipName);
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        Transport transport = session.getTransport("smtp");
        transport.connect(settings.getEmailUsername(), new String(settings.getEmailPassword()));
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
    
    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", settings.getEmailServer());
        properties.put("mail.smtp.port", settings.getEmailPort()); // SSL 465, TLS 587
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        return properties;
    }
}
