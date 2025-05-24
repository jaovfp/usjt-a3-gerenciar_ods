package usjt.atividade.views.authentication.SignUp;

import usjt.atividade.views.authentication.AuthView;

import java.awt.*;

public class SignUpView extends AuthView {

    private SignUpPanel signUpPanel;

    public SignUpView() {
        super("Cadastrar", "Junte-se a nós!", "ui-ux-signup.png");
        signUpPanel = new SignUpPanel(this);
        rightPanel.add(signUpPanel, BorderLayout.CENTER);
        render();
    }
}
