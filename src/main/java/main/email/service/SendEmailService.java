package main.email.service;

import main.email.credentials.MailSenderCredentials;
import main.email.credentials.SenderCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by sangeet on 4/14/2017.
 */
@Component()
public class SendEmailService {
  private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailService.class);
  private SenderCredentials senderCredentials;

  @Autowired()
  public SendEmailService(final SenderCredentials senderCredentials) {
    this.senderCredentials = senderCredentials;
  }

  public void sentEmail(final MailSenderCredentials mailSenderCredentials) {
    final Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", senderCredentials.getHost());
    properties.setProperty("mail.smtp.auth", "true");
    properties.setProperty("mail.smtp.port", "465");
    properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    properties.put("mail.smtp.socketFactory.port", "465");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.debug", "true");

    final Session session = Session.getDefaultInstance(properties, new Authenticator() {
      @Override protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(senderCredentials.getUsername(),
            senderCredentials.getPassword());
      }
    });
    try {
      final MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(mailSenderCredentials.getFrom()));
      message.addRecipients(Message.RecipientType.TO, mailSenderCredentials.getTo());
      message.setSubject(mailSenderCredentials.getSubject());
      message.setText(mailSenderCredentials.getBody());
      Transport.send(message);
    } catch (final MessagingException ex) {
      LOGGER.error("failed to send message from:{} to:{} ex:{}", mailSenderCredentials.getFrom(),
          mailSenderCredentials.getTo(), ex);
    }
  }

}
