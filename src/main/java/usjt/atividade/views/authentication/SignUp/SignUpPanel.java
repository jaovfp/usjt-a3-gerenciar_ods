/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usjt.atividade.views.authentication.SignUp;

import usjt.atividade.views.authentication.Login.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SignUpPanel extends javax.swing.JPanel {

    private usjt.atividade.views.authentication.utils.RoundedButton btnSignUp;
    private javax.swing.JLabel emailLabel;
    private usjt.atividade.views.authentication.utils.CustomTextField emailTextField;
    private javax.swing.JLabel loginLabel;
    private usjt.atividade.views.authentication.utils.CustomPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private usjt.atividade.views.authentication.utils.CustomPasswordField repeatPasswordField;
    private javax.swing.JLabel repeatPasswordLabel;
    private javax.swing.JLabel userLabel;
    private usjt.atividade.views.authentication.utils.CustomTextField userTextField;
    private SignUpView signUpView;
    private JLabel backArrowImageLabel;

    public SignUpPanel(SignUpView signUpView) {
        this.signUpView = signUpView;
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
        userTextField.setPlaceholder("Digite seu usuário...");
        emailLabel = new javax.swing.JLabel();
        emailTextField = new usjt.atividade.views.authentication.utils.CustomTextField();
        emailTextField.setPlaceholder("Digite seu e-mail...");
        emailTextField.setPlaceholderColor(Color.GRAY);
        emailTextField.setTextColor(Color.WHITE);
        emailTextField.setBorderColor(Color.CYAN);
        passwordLabel = new javax.swing.JLabel();
        passwordField = new usjt.atividade.views.authentication.utils.CustomPasswordField();
        passwordField.setPlaceholder("Digite sua senha...");
        passwordField.setPlaceholderColor(Color.GRAY);
        passwordField.setTextColor(Color.WHITE);
        passwordField.setBorderColor(Color.CYAN);
        repeatPasswordLabel = new javax.swing.JLabel();
        repeatPasswordField = new usjt.atividade.views.authentication.utils.CustomPasswordField();
        repeatPasswordField.setPlaceholder("Repita sua senha...");
        repeatPasswordField.setPlaceholderColor(Color.GRAY);
        repeatPasswordField.setTextColor(Color.WHITE);
        repeatPasswordField.setBorderColor(Color.CYAN);
        btnSignUp = new usjt.atividade.views.authentication.utils.RoundedButton();

        setBackground(new java.awt.Color(0, 33, 66));
        setPreferredSize(new java.awt.Dimension(500, 700));
        
        loginLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginLabel.setText("Cadastre-se");

        userLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userLabel.setText("Usuário:");
        userLabel.setForeground(Color.WHITE);

        userTextField.setToolTipText("");

        emailLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        emailLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        emailLabel.setText("Email:");
        emailLabel.setForeground(Color.WHITE);

        emailTextField.setToolTipText("");

        passwordLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordLabel.setText("Senha:");
        passwordLabel.setForeground(Color.WHITE);

        repeatPasswordLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        repeatPasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        repeatPasswordLabel.setText("Repetir senha:");
        repeatPasswordLabel.setForeground(Color.WHITE);

        btnSignUp.setBackground(new java.awt.Color(0, 140, 160));
        btnSignUp.setForeground(new java.awt.Color(255, 255, 255));
        btnSignUp.setBorderColor(new java.awt.Color(0, 140, 160));
        btnSignUp.setColorPressed(new java.awt.Color(0, 150, 160));
        btnSignUp.setColorRollover(new java.awt.Color(0, 160, 170));
        btnSignUp.setText("Cadastrar");
        btnSignUp.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        backArrowImageLabel = new JLabel();
        backArrowImageLabel.setIcon(new ImageIcon(getClass().getResource("/icons/voltar.png"))); // NOI18N
        backArrowImageLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(backArrowImageLabel)
                )
            .addComponent(loginLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(repeatPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(repeatPasswordLabel)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordLabel)
                            .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailLabel)
                            .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15,15,15)
                .addComponent(backArrowImageLabel)
                .addGap(17, 17, 17)
                .addComponent(loginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(userLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(emailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(repeatPasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(repeatPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE)
            )
        );
    }

    private void addListeners(){
        backArrowImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LoginView().setVisible(true);
                signUpView.dispose();
            }
        });
    }
}
