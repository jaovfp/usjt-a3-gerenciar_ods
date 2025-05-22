package usjt.atividade.views.authentication.Login;

import usjt.atividade.views.authentication.utils.CustomPasswordField;
import usjt.atividade.views.authentication.utils.CustomTextField;
import usjt.atividade.views.authentication.utils.UIStyle;
import usjt.atividade.views.authentication.Login.LoginPanel;
import usjt.atividade.views.authentication.Welcolme.WelcomePanel;
import usjt.atividade.views.authentication.AuthView;

import javax.swing.*;
import java.awt.*;

public class LoginView extends AuthView {

    private LoginPanel loginPanel;
    private WelcomePanel welcomePanel;

    public LoginView() {
        super("Login");

        LoginPanel loginPanel = new LoginPanel(this);
        WelcomePanel welcomePanel = new WelcomePanel("Olá, seja bem-vindo!", "ui-ux-login.png");

        leftPanel.add(welcomePanel, BorderLayout.CENTER);
        rightPanel.add(loginPanel, BorderLayout.CENTER);

        render();
    }
}
