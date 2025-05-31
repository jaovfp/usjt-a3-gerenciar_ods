package usjt.atividade.views.authentication.Login;

import usjt.atividade.app.Authentication.AuthController;
import usjt.atividade.app.Authentication.dto.AuthenticateRequest;
import usjt.atividade.common.Response;
import usjt.atividade.domain.model.User.User;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.Admin.AdminView;
import usjt.atividade.views.User.UserView;
import usjt.atividade.views.authentication.ForgotPassword.ForgotPasswordView;
import usjt.atividade.views.authentication.SignUp.SignUpView;
import usjt.atividade.views.utils.CustomPasswordField;
import usjt.atividade.views.utils.CustomTextField;
import usjt.atividade.views.utils.RoundedButton;
import usjt.atividade.views.utils.UIStyle;
import static usjt.atividade.views.utils.ComponentFactory.*;
import static usjt.atividade.views.utils.PasswordUtils.convertPassword;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPanel extends AbstractPanel {

    private RoundedButton btnSignIn;
    private JLabel signUpLabel;
    private JLabel forgotPasswordLabel;
    private JLabel naoTemUmaContaLabel;
    private JLabel loginLabel;
    private CustomPasswordField passwordField;
    private JLabel passwordLabel;
    private JLabel userLabel;
    private CustomTextField emailTextField;
    private LoginView loginView;
    private AuthController authController;

    public LoginPanel(LoginView loginView) {
        super(UIStyle.BG_AUTH_COLOR, UIStyle.AUTH_DIMENSION);
        this.loginView = loginView;
        this.authController = new AuthController();
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    protected void initComponents() {
        loginLabel = createLabel("Login", UIStyle.AUTH_TITLE_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        userLabel = createLabel("E-mail:", UIStyle.AUTH_TEXT_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        passwordLabel = createLabel("Senha:", UIStyle.AUTH_TEXT_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        naoTemUmaContaLabel = createLabel("Não tem uma conta ?", UIStyle.AUTH_TEXT_FONT, UIStyle.PLACEHOLDER_COLOR, SwingConstants.LEFT);
        emailTextField = createCustomTextField("Digite seu e-mail...", UIStyle.AUTH_TEXT_COLOR, Color.CYAN);
        passwordField = createCustomPasswordField("Digite sua senha...", UIStyle.AUTH_TEXT_COLOR, Color.CYAN);
        forgotPasswordLabel = createLinkLabel("Esqueceu sua senha ?", UIStyle.AUTH_ACCENT_COLOR, UIStyle.AUTH_TEXT_FONT);
        signUpLabel = createLinkLabel("Cadastre-se",  UIStyle.AUTH_ACCENT_COLOR, UIStyle.AUTH_TEXT_FONT);
        btnSignIn = createRoundedButton("Entrar", UIStyle.AUTH_BTN_FONT, UIStyle.AUTH_ACCENT_COLOR, UIStyle.AUTH_TEXT_COLOR);
    }
    
    @Override
    protected void layoutComponents() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(loginLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(forgotPasswordLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordLabel)
                        .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
                        .addComponent(userLabel)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSignIn, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(naoTemUmaContaLabel)
                        .addGap(169, 169, 169))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(signUpLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(209, 209, 209))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(loginLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(userLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(passwordLabel)
                .addGap(18, 18, 18)
                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(forgotPasswordLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(btnSignIn, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(naoTemUmaContaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(signUpLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
    }

    @Override
    protected void addListeners(){
        addSignUpLabelListener();
        addForgotPasswordListener();
        btnSignIn.addActionListener(e -> btnSignInClick());
    }

    private void btnSignInClick(){
        AuthenticateRequest authenticateRequest = new AuthenticateRequest(emailTextField.getText(), convertPassword(passwordField));
        Response<User> response = authController.authenticate(authenticateRequest);
        if (response.isSuccess()){
            User user = response.getData();
            redirectToMainScreen(user);
            loginView.dispose();
        }else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            clearFields();
        }
    }

    private void redirectToMainScreen(User user) {
        switch (user.getType()) {
            case ADMIN:
                new AdminView("Admin Screen", user).setVisible(true);
                break;
            case NORMAL:
                new UserView("User Screen", user).setVisible(true);
                break;
            default:
                System.out.println(user);
                JOptionPane.showMessageDialog(null, "Tipo de usuário desconhecido.");
                break;
        }
    }

    private void clearFields(){
        emailTextField.setText("");
        passwordField.setText("");
    }

    private void addSignUpLabelListener() {
        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SignUpView().setVisible(true);
                loginView.dispose();
            }
        });
    }

    private void addForgotPasswordListener() {
        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ForgotPasswordView().setVisible(true);
                loginView.dispose();
            }
        });
    }
}
