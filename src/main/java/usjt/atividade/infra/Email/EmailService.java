package usjt.atividade.infra.Email;

import usjt.atividade.app.Exceptions.EmailSendException;

import static usjt.atividade.common.MessageConstants.ERROR_SEND_EMAIL;

public class EmailService {

    private final EmailClient emailClient;

    public EmailService() {
        this.emailClient = new EmailClient();
    }

    public void sendRecoveryEmail(String to, String pin) {
        String subject = "Recuperação de senha - Gerenciar ODS";
        String body = EmailTemplate.recoveryEmailHtml(pin);
        try {
            emailClient.sendHtml(to, subject, body);
        } catch (Exception e) {
            System.out.println(e);
            throw new EmailSendException(ERROR_SEND_EMAIL + to);
        }
    }

}
