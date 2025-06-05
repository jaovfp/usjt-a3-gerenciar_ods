package usjt.atividade.infra.config;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Properties;

public class EmailConfig {
    private static final Dotenv dotenv = Dotenv.load();

    private final String username = dotenv.get("APP_EMAIL");
    private final String password = dotenv.get("PASSWORD_APP_EMAIL");
    private final String host = dotenv.get("HOST_SMTP_GMAIL");
    private final int port = Integer.parseInt(dotenv.get("SMTP_PORT"));
    private final boolean startTls = Boolean.parseBoolean(dotenv.get("SMTP_STARTTLS"));
    private final boolean auth = Boolean.parseBoolean(dotenv.get("SMTP_AUTH"));

    private final Properties props;

    public EmailConfig() {
        props = new Properties();
        props.put("mail.smtp.auth", String.valueOf(auth));
        props.put("mail.smtp.starttls.enable", String.valueOf(startTls));
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", String.valueOf(port));
    }

    public Properties getProperties() {
        return props;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
