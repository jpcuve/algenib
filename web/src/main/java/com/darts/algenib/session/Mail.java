package com.darts.algenib.session;

import com.darts.algenib.bean.MailEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.ejb.Asynchronous;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Created by jpc on 3/14/16.
 */
@Singleton
@Lock(LockType.READ)
public class Mail {
    private static final Logger LOGGER = LoggerFactory.getLogger(Mail.class);

    @Asynchronous
    public void gmail(@Observes MailEvent mailEvent) {
        LOGGER.debug("sending gmail: {}", mailEvent);
        final String username = "messiosprl@gmail.com";
        final String password = "gme33ereo";
        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
//        session.setDebug(true);
        try {
            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sagitta@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailEvent.getTo()));
            message.setSubject(mailEvent.getSubject());
            final MimeMultipart mimeMultipart = new MimeMultipart("alternative");
            for (final DataSource dataSource: mailEvent.getDataSources()){
                final MimeBodyPart bodyPart = new MimeBodyPart();
                bodyPart.setDataHandler(new DataHandler(dataSource));
                if (dataSource.getName() != null && dataSource.getName().length() > 0){
                    bodyPart.setHeader("Content-Disposition", "attachment; filename=" + dataSource.getName());
                }
                mimeMultipart.addBodyPart(bodyPart);
            }
            message.setContent(mimeMultipart);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
