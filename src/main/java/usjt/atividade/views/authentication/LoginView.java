package usjt.atividade.views.authentication;

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
    }

    @Override
    protected void initComponents() {
        userField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = UIStyle.createRoundedButton("Entrar");
        forgotPasswordLabel = UIStyle.createLinkLabel("Esqueceu a senha?");
        signUpLabel = UIStyle.createLinkLabel("Cadastrar-se");
    }

    @Override
    protected void layoutComponents() {
        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setFont(UIStyle.TITLE_FONT);
        titleLabel.setForeground(UIStyle.TITLE_COLOR);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(UIStyle.BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(userField, gbc);
        gbc.gridy++;
        formPanel.add(passwordField, gbc);
        gbc.gridy++;
        formPanel.add(forgotPasswordLabel, gbc);
        gbc.gridy++;
        formPanel.add(loginButton, gbc);
        gbc.gridy++;
        formPanel.add(signUpLabel, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    @Override
    protected void addListeners() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
