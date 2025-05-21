package usjt.atividade.views.authentication.SignUp;

import usjt.atividade.views.authentication.AuthView;
import usjt.atividade.views.authentication.Welcolme.WelcomePanel;

import java.awt.*;

public class SignUpView extends AuthView {

    private SignUpPanel signUpPanel;
    private WelcomePanel welcomePanel;

    public SignUpView() {
        super("Sign Up");
        render();
    }

    @Override
    protected void initComponents() {
        signUpPanel = new SignUpPanel(this);
        welcomePanel = new WelcomePanel("Junte-se a nós!", "ui-ux-signup.png");
    }

    @Override
    protected void layoutComponents() {
        leftPanel.add(welcomePanel, BorderLayout.CENTER);
        rightPanel.add(signUpPanel, BorderLayout.CENTER);
    }

    @Override
    protected void addListeners() {
    }

}
