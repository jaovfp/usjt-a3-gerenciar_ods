package usjt.atividade.app.Email;

public class EmailTemplate {

    public static String recoveryEmailHtml(String pin) {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset=\"UTF-8\">");
        html.append("<title>Recuperação de Senha</title>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; background-color: #f9fafb; padding: 20px; }");
        html.append(".container { background-color: #ffffff; max-width: 600px; margin: auto; border: 1px solid #e0e0e0; border-radius: 8px; padding: 30px; }");
        html.append(".header { text-align: center; color: #2e7d32; font-size: 24px; margin-bottom: 20px; }");
        html.append(".pin { font-size: 32px; font-weight: bold; color: #1565c0; text-align: center; margin: 30px 0; }");
        html.append(".message { font-size: 16px; color: #444444; line-height: 1.6; }");
        html.append(".footer { margin-top: 40px; font-size: 12px; color: #888888; text-align: center; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");
        html.append("<div class=\"container\">");
        html.append("<div class=\"header\">Gerenciar ODS - Recuperação de Senha</div>");
        html.append("<div class=\"message\">");
        html.append("<p>Você solicitou a recuperação de senha para sua conta no <strong>Gerenciar ODS</strong>.</p>");
        html.append("<p>Use o código abaixo para redefinir sua senha:</p>");
        html.append("</div>");
        html.append("<div class=\"pin\">").append(pin).append("</div>");
        html.append("<div class=\"message\">");
        html.append("<p>O código expira em 3 minutos. Se você não solicitou essa recuperação, ignore este e-mail.</p>");
        html.append("</div>");
        html.append("<div class=\"footer\">&copy; 2025 Gerenciar ODS. Todos os direitos reservados.</div>");
        html.append("</div>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }

}
