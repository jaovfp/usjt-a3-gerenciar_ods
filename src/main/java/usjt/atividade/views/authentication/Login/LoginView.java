package usjt.atividade.views.authentication.Login;

import usjt.atividade.views.authentication.AuthView;

import java.awt.*;

public class LoginView extends AuthView {

    LoginPanel loginPanel;

    public LoginView() {
        super("Entrar", "Olá, seja bem-vindo!", "ui-ux-login.png");
        loginPanel = new LoginPanel(this);
        rightPanel.add(loginPanel, BorderLayout.CENTER);
        render();
    }
}
