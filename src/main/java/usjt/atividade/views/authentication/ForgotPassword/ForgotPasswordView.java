package usjt.atividade.views.authentication.ForgotPassword;

import usjt.atividade.views.authentication.AuthView;

import java.awt.*;

public class ForgotPasswordView extends AuthView {
    ForgotPasswordPanel forgotPasswordPanel;

    public ForgotPasswordView() {
        super("Recuperar senha", "Esqueceu sua senha ?","ui-ux-forgot-password.png");
        forgotPasswordPanel = new ForgotPasswordPanel(this);
        rightPanel.add(forgotPasswordPanel, BorderLayout.CENTER);
        render();
    }
}
