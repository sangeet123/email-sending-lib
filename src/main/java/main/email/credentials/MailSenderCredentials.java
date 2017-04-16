package main.email.credentials;

/**
 * Created by sangeet on 4/14/2017.
 */
public class MailSenderCredentials {
  private String to;

  private String from;

  private String subject;

  private String body;

  public String getTo() {
    return to;
  }

  public void setTo(final String to) {
    this.to = to;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(final String from) {
    this.from = from;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(final String subject) {
    this.subject = subject;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
