package usjt.atividade.views.authentication;

import usjt.atividade.views.authentication.utils.CustomPasswordField;
import usjt.atividade.views.authentication.utils.CustomTextField;
import usjt.atividade.views.authentication.utils.UIStyle;

import javax.swing.*;
import java.awt.*;

public class LoginView extends AuthView {

    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel forgotPasswordLabel;
    private JLabel signUpLabel;

    public LoginView() {
        super("Login");
        render();
    }

    @Override
    protected void initComponents() {
        userField = new CustomTextField(20);
        passwordField = new CustomPasswordField(20);
        loginButton = UIStyle.createRoundedButton("Entrar", Color.CYAN,Color.white, 220, 80);
        forgotPasswordLabel = UIStyle.createLinkLabel("Esqueceu a senha?");
        signUpLabel = UIStyle.createLinkLabel("Cadastrar-se");
    }

    @Override
    protected void layoutComponents() {
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setFont(UIStyle.TITLE_FONT);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


//        JPanel userLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
//        userLabelPanel.setOpaque(false);
        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setFont(UIStyle.TEXT_FONT);
        userLabel.setForeground(Color.BLACK);
//        userLabelPanel.add(userLabel);
//
//        JPanel passwordLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
//        passwordLabelPanel.setOpaque(false);
        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setFont(UIStyle.TEXT_FONT);
        passwordLabel.setForeground(Color.BLACK);
//        passwordLabelPanel.add(passwordLabel);
//
//        JPanel forgotPasswordLabelPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        forgotPasswordLabelPanel.setOpaque(false);
//        forgotPasswordLabelPanel.add(forgotPasswordLabel);

        Dimension textFieldSize = new Dimension(450, 30);
        userField.setPreferredSize(textFieldSize);
        userField.setMaximumSize(textFieldSize);
        passwordField.setPreferredSize(textFieldSize);
        passwordField.setMaximumSize(textFieldSize);

        userLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgotPasswordLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        signUpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(Box.createVerticalStrut(100));
        leftPanel.add(titleLabel);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(userLabel);
        leftPanel.add(userField);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(passwordLabel);
        leftPanel.add(passwordField);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(forgotPasswordLabel);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(loginButton);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(signUpLabel);

        // Lado direito — imagem e mensagem
        rightPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Bem-vindo!", SwingConstants.CENTER);
        welcomeLabel.setFont(UIStyle.TITLE_FONT);
        welcomeLabel.setForeground(new Color(50, 50, 50)); // escuro sobre fundo claro

        ImageIcon icon = new ImageIcon("caminho/para/imagem.png"); // ajuste aqui
        JLabel imageLabel = new JLabel(icon, SwingConstants.CENTER);

        rightPanel.add(welcomeLabel, BorderLayout.NORTH);
        rightPanel.add(imageLabel, BorderLayout.CENTER);
    }

    @Override
    protected void addListeners() {
    }
}
