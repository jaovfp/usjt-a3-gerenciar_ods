package usjt.atividade.views.authentication.SignUp;

import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.authentication.Login.LoginView;
import usjt.atividade.views.authentication.utils.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static usjt.atividade.views.authentication.utils.ComponentFactory.*;


public class SignUpPanel extends AbstractPanel {

    private JLabel backArrowImageLabel;
    private JLabel signUpLabel;
    private JLabel emailLabel;
    private CustomTextField emailTextField;
    private JLabel userLabel;
    private CustomTextField userTextField;
    private CustomPasswordField passwordField;
    private JLabel passwordLabel;
    private CustomPasswordField repeatPasswordField;
    private JLabel repeatPasswordLabel;
    private RoundedButton btnSignUp;
    private SignUpView signUpView;

    public SignUpPanel(SignUpView signUpView) {
        super(UIStyle.BG_AUTH_COLOR, UIStyle.AUTH_DIMENSION);
        this.signUpView = signUpView;
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    protected void initComponents() {
        backArrowImageLabel = createImageLabel("voltar.png", "icons",SwingConstants.CENTER, 30, 30);
        backArrowImageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLabel = createLabel("Cadastre-se", UIStyle.AUTH_TITLE_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        emailLabel = createLabel("E-mail:", UIStyle.AUTH_TEXT_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        emailTextField = createCustomTextField("Digite seu e-mail...", UIStyle.AUTH_TEXT_COLOR, Color.CYAN);
        userLabel = createLabel("Usuário:", UIStyle.AUTH_TEXT_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        userTextField = createCustomTextField("Digite seu usuário...", UIStyle.AUTH_TEXT_COLOR, Color.CYAN);
        passwordLabel = createLabel("Senha:", UIStyle.AUTH_TEXT_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        passwordField = createCustomPasswordField("Digite sua senha...", UIStyle.AUTH_TEXT_COLOR, Color.CYAN);
        repeatPasswordLabel = createLabel("Repetir senha:", UIStyle.AUTH_TEXT_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        repeatPasswordField = createCustomPasswordField("Repita sua senha...", UIStyle.AUTH_TEXT_COLOR, Color.CYAN);
        btnSignUp = createRoundedButton("Cadastrar", UIStyle.AUTH_BTN_FONT, UIStyle.AUTH_ACCENT_COLOR, UIStyle.AUTH_TEXT_COLOR);
    }

    @Override
    protected void layoutComponents(){
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(backArrowImageLabel)
            )
            .addComponent(signUpLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(repeatPasswordField, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
                                .addComponent(repeatPasswordLabel)
                                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
                                .addComponent(passwordLabel)
                                .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
                                .addComponent(emailLabel)
                                .addComponent(userTextField, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
                                .addComponent(userLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(btnSignUp, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(15,15,15)
                    .addComponent(backArrowImageLabel)
                    .addGap(17, 17, 17)
                    .addComponent(signUpLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                    .addGap(43, 43, 43)
                    .addComponent(userLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(userTextField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28)
                    .addComponent(emailLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28)
                    .addComponent(passwordLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28)
                    .addComponent(repeatPasswordLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(repeatPasswordField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addGap(59, 59, 59)
                    .addComponent(btnSignUp, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(73, Short.MAX_VALUE)
                )
        );
    }

    @Override
    protected void addListeners(){
        addBotaoVoltarListener();
    }

    private void addBotaoVoltarListener(){
        backArrowImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LoginView().setVisible(true);
                signUpView.dispose();
            }
        });
    }
}
