package usjt.atividade.views.authentication.Login;

import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.authentication.SignUp.SignUpView;
import usjt.atividade.views.authentication.utils.CustomPasswordField;
import usjt.atividade.views.authentication.utils.CustomTextField;
import usjt.atividade.views.authentication.utils.RoundedButton;
import usjt.atividade.views.authentication.utils.UIStyle;
import static usjt.atividade.views.authentication.utils.ComponentFactory.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPanel extends AbstractPanel {

    private RoundedButton btnEntrar;
    private JLabel cadastrarLabel;
    private JLabel esqueceuSenhaLabel;
    private JLabel naoTemUmaContaLabel;
    private JLabel loginLabel;
    private CustomPasswordField passwordField;
    private JLabel passwordLabel;
    private JLabel userLabel;
    private CustomTextField userTextField;
    private LoginView loginView;

    public LoginPanel(LoginView loginView) {
        super(UIStyle.BG_AUTH_COLOR, UIStyle.AUTH_DIMENSION);
        this.loginView = loginView;
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    protected void initComponents() {
        loginLabel = createLabel("Login", UIStyle.AUTH_TITLE_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        userLabel = createLabel("Usuário:", UIStyle.AUTH_TEXT_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        passwordLabel = createLabel("Senha:", UIStyle.AUTH_TEXT_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        naoTemUmaContaLabel = createLabel("Não tem uma conta ?", UIStyle.AUTH_TEXT_FONT, UIStyle.PLACEHOLDER_COLOR, SwingConstants.LEFT);
        userTextField = createCustomTextField("Digite seu usuário...", UIStyle.AUTH_TEXT_COLOR, Color.CYAN);
        passwordField = createCustomPasswordField("Digite sua senha...", UIStyle.AUTH_TEXT_COLOR, Color.CYAN);
        esqueceuSenhaLabel = createLinkLabel("Esqueceu sua senha ?", UIStyle.AUTH_ACCENT_COLOR, UIStyle.AUTH_TEXT_FONT);
        cadastrarLabel = createLinkLabel("Cadastre-se",  UIStyle.AUTH_ACCENT_COLOR, UIStyle.AUTH_TEXT_FONT);
        btnEntrar = createRoundedButton("Entrar", new Font("Arial", Font.BOLD, 18), UIStyle.AUTH_ACCENT_COLOR, UIStyle.AUTH_TEXT_COLOR);
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
                    .addComponent(esqueceuSenhaLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordLabel)
                        .addComponent(userTextField, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
                        .addComponent(userLabel)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnEntrar, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(naoTemUmaContaLabel)
                        .addGap(169, 169, 169))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cadastrarLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
                .addComponent(userTextField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(passwordLabel)
                .addGap(18, 18, 18)
                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(esqueceuSenhaLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(btnEntrar, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(naoTemUmaContaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cadastrarLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
    }

    @Override
    protected void addListeners(){
        addCadastrarLabelListener();
    }

    private void addCadastrarLabelListener() {
        cadastrarLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SignUpView().setVisible(true);
                loginView.dispose();
            }
        });
    }
}
