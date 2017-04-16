package main.email.credentials;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sangeet on 4/14/2017.
 */
@Configuration("main.email-config") public class SenderCredentials {

  private String host;

  private String username;

  private String password;

  public String getHost() {
    return host;
  }

  @Value("${mail.smtp.host}") public void setHost(final String host) {
    this.host = host;
  }

  public String getUsername() {
    return username;
  }

  @Value("${mail.smtp.user}") public void setUsername(final String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  @Value("${mail.smtp.password}") public void setPassword(final String password) {
    this.password = password;
  }

}
