package usjt.atividade.app.Email;

import javax.mail.*;
import javax.mail.internet.*;

public class EmailClient {
    private final EmailConfig config;

    public EmailClient() {
        this.config = new EmailConfig();
    }

    public void sendHtml(String to, String subject, String body) {
        Session session = Session.getInstance(config.getProperties(),
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(config.getUsername(), config.getPassword());
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(config.getUsername()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(body, "text/html; charset=utf-8");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail", e);
        }
    }

}
