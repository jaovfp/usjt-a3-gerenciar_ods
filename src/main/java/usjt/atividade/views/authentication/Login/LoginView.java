package usjt.atividade.views.authentication.Login;

import usjt.atividade.views.authentication.Welcolme.WelcomePanel;
import usjt.atividade.views.authentication.AuthView;

import java.awt.*;

public class LoginView extends AuthView {

    LoginPanel loginPanel;
    WelcomePanel welcomePanel;

    public LoginView() {
        super("Login");
        render();
    }

    @Override
    protected void initPanels(){
        super.initPanels();
        loginPanel = new LoginPanel(this);
        welcomePanel = new WelcomePanel("Olá, seja bem-vindo!", "ui-ux-login.png");
    }

    @Override
    protected void layoutPanels(){
        super.layoutPanels();
        leftPanel.add(welcomePanel, BorderLayout.CENTER);
        rightPanel.add(loginPanel, BorderLayout.CENTER);
    }
}
