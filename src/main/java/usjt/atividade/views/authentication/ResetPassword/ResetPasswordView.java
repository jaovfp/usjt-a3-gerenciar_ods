package usjt.atividade.views.authentication.ResetPassword;

import usjt.atividade.views.authentication.AuthView;

import java.awt.*;

public class ResetPasswordView extends AuthView {
    ResetPasswordPanel resetPasswordPanel;

    public ResetPasswordView() {
        super("Alterar senha", "Troque a sua senha com facilidade!", "ui-ux-reset-password.png");
        resetPasswordPanel = new ResetPasswordPanel(this);
        rightPanel.add(resetPasswordPanel, BorderLayout.CENTER);
        render();
    }
}
