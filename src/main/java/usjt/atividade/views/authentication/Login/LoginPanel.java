/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package usjt.atividade.views.authentication.Login;

import usjt.atividade.views.authentication.SignUp.SignUpView;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPanel extends javax.swing.JPanel {

    private usjt.atividade.views.authentication.utils.RoundedButton btnEntrar;
    private javax.swing.JLabel cadastrarLabel;
    private javax.swing.JLabel esqueceuSenhaLabel;
    private javax.swing.JLabel naoTemUmaContaLabel;
    private javax.swing.JLabel loginLabel;
    private usjt.atividade.views.authentication.utils.CustomPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel userLabel;
    private usjt.atividade.views.authentication.utils.CustomTextField userTextField;
    private LoginView loginView;

    public LoginPanel(LoginView loginView) {
        this.loginView = loginView;
        initComponents();
        addListeners();
    }

    private void initComponents() {

        loginLabel = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        userTextField = new usjt.atividade.views.authentication.utils.CustomTextField();
        userTextField.setPlaceholderColor(Color.GRAY);
        userTextField.setTextColor(Color.WHITE);
        userTextField.setBorderColor(Color.CYAN);
        userTextField.setPlaceholder("Digite seu e-mail");
        passwordLabel = new javax.swing.JLabel();
        passwordField = new usjt.atividade.views.authentication.utils.CustomPasswordField();
        passwordField.setPlaceholder("Digite sua senha...");
        passwordField.setPlaceholderColor(Color.GRAY);
        passwordField.setTextColor(Color.WHITE);
        passwordField.setBorderColor(Color.CYAN);
        esqueceuSenhaLabel = new javax.swing.JLabel();
        btnEntrar = new usjt.atividade.views.authentication.utils.RoundedButton();
        cadastrarLabel = new javax.swing.JLabel();
        naoTemUmaContaLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 33, 66));
        setPreferredSize(new java.awt.Dimension(500, 700));

        loginLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginLabel.setText("Login");

        userLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userLabel.setForeground(Color.WHITE);
        userLabel.setText("Usuário:");

        userTextField.setToolTipText("");

        passwordLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setText("Senha:");

        esqueceuSenhaLabel.setForeground(new java.awt.Color(0, 140, 160));
        esqueceuSenhaLabel.setText("<html><u>Esqueceu sua senha ?</u></html>");
        esqueceuSenhaLabel.setToolTipText("");
        esqueceuSenhaLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnEntrar.setBackground(new java.awt.Color(0, 140, 160));
        btnEntrar.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrar.setBorderColor(new java.awt.Color(0, 140, 160));
        btnEntrar.setColorPressed(new java.awt.Color(0, 150, 160));
        btnEntrar.setColorRollover(new java.awt.Color(0, 160, 170));
        btnEntrar.setText("Entrar");
        btnEntrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        cadastrarLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cadastrarLabel.setForeground(new java.awt.Color(0, 140, 160));
        cadastrarLabel.setText("<html><u>Cadastre-se</u></html>");
        cadastrarLabel.setText("<html><u>Cadastre-se</u></html>");
        cadastrarLabel.setToolTipText("");
        cadastrarLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        naoTemUmaContaLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        naoTemUmaContaLabel.setForeground(Color.GRAY);
        naoTemUmaContaLabel.setText("Não tem uma conta ?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(esqueceuSenhaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordLabel)
                        .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(userLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(naoTemUmaContaLabel)
                        .addGap(169, 169, 169))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cadastrarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(209, 209, 209))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(loginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(userLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(passwordLabel)
                .addGap(18, 18, 18)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(esqueceuSenhaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(naoTemUmaContaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cadastrarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
    }

    private void addListeners(){
        cadastrarLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SignUpView().setVisible(true);
                loginView.dispose();
            }
        });
    }
}
