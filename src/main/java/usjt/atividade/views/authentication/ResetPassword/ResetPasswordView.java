package usjt.atividade.views.authentication.ResetPassword;

import usjt.atividade.domain.model.PasswordRecovery;
import usjt.atividade.views.authentication.AuthView;

import java.awt.*;

public class ResetPasswordView extends AuthView {
    ResetPasswordPanel resetPasswordPanel;

    public ResetPasswordView(PasswordRecovery passwordRecovery) {
        super("Alterar senha", "Troque a sua senha com facilidade!", "ui-ux-reset-password.png");
        resetPasswordPanel = new ResetPasswordPanel(this, passwordRecovery);
        rightPanel.add(resetPasswordPanel, BorderLayout.CENTER);
        render();
    }
}
