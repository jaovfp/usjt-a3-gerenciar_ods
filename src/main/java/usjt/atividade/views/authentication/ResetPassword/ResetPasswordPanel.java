package usjt.atividade.views.authentication.ResetPassword;

import usjt.atividade.app.Authentication.AuthController;
import usjt.atividade.app.Authentication.dto.ResetPasswordRequest;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.common.Response;
import usjt.atividade.domain.model.PasswordRecovery;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.authentication.ForgotPassword.ForgotPasswordView;
import usjt.atividade.views.authentication.Login.LoginView;
import usjt.atividade.views.utils.CustomPasswordField;
import usjt.atividade.views.utils.CustomTextField;
import usjt.atividade.views.utils.RoundedButton;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import static usjt.atividade.views.utils.ComponentFactory.*;
import static usjt.atividade.views.utils.PasswordUtils.convertPassword;

public class ResetPasswordPanel extends AbstractPanel {

    private JLabel backArrowImageLabel;
    private JLabel resetPasswordLabel;
    private JLabel pinCodeLabel;
    private CustomTextField pinCodeTextField;
    private JLabel newPassowordLabel;
    private CustomPasswordField newPasswordField;
    private JLabel repeatNewPasswordLabel;
    private CustomPasswordField repeatNewPasswordField;
    private RoundedButton btnResetPassword;
    private final ResetPasswordView resetPasswordView;
    private final PasswordRecovery passwordRecovery;
    private final AuthController authController;

    public ResetPasswordPanel(ResetPasswordView resetPasswordView, PasswordRecovery passwordRecovery){
        super(UIStyle.BG_AUTH_COLOR, UIStyle.AUTH_DIMENSION);
        this.resetPasswordView = resetPasswordView;
        this.passwordRecovery = passwordRecovery;
        this.authController = new AuthController();
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    protected void initComponents(){
        backArrowImageLabel = createImageLabel("voltar.png", "icons",SwingConstants.CENTER, 30, 30);
        backArrowImageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetPasswordLabel = createLabel("Alterar Senha", UIStyle.AUTH_TITLE_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        pinCodeLabel = createLabel("PIN:", UIStyle.AUTH_TEXT_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        pinCodeTextField = createCustomTextField("Digite o código recebido em seu e-mail...", UIStyle.AUTH_TEXT_COLOR, Color.CYAN);
        newPassowordLabel = createLabel("Nova Senha:", UIStyle.AUTH_TEXT_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        newPasswordField = createCustomPasswordField("Digite sua nova senha...", UIStyle.AUTH_TEXT_COLOR, Color.CYAN);
        repeatNewPasswordLabel = createLabel("Repetir senha:", UIStyle.AUTH_TEXT_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        repeatNewPasswordField = createCustomPasswordField("Repita sua nova senha...", UIStyle.AUTH_TEXT_COLOR, Color.CYAN);
        btnResetPassword = createRoundedButton("Alterar Senha", UIStyle.AUTH_BTN_FONT, UIStyle.AUTH_ACCENT_COLOR, UIStyle.AUTH_TEXT_COLOR);
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
                        .addComponent(resetPasswordLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(repeatNewPasswordField, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(repeatNewPasswordLabel)
                                                        .addComponent(newPasswordField, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(newPassowordLabel)
                                                        .addComponent(pinCodeTextField, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(pinCodeLabel)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(139, 139, 139)
                                                .addComponent(btnResetPassword, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15,15,15)
                                .addComponent(backArrowImageLabel)
                                .addGap(50, 50, 50)
                                .addComponent(resetPasswordLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(pinCodeLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pinCodeTextField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(newPassowordLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(newPasswordField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(repeatNewPasswordLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(repeatNewPasswordField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(btnResetPassword, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(73, Short.MAX_VALUE)
                        )
        );
    }

    @Override
    protected void addListeners(){
        addBotaoVoltarListener();
        btnResetPassword.addActionListener(e -> btnResetPasswordClick());
    }

    private void btnResetPasswordClick(){
        if (!isPasswordMatching()){
            JOptionPane.showMessageDialog(null, MessageConstants.PASSWORDS_DO_NOT_MATCH, "Erro", JOptionPane.ERROR_MESSAGE);
            clearFields();
            return;
        }
        updatePassword();
    }

    private void updatePassword(){
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest(pinCodeTextField.getText(), convertPassword(newPasswordField), passwordRecovery.getRecoveryId());
        Response<Void> response = authController.resetPassword(resetPasswordRequest);
        if (response.isSuccess()){
            JOptionPane.showMessageDialog(null, response.getMessage(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            new LoginView().setVisible(true);
            resetPasswordView.dispose();
        }else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            clearFields();
        }
    }

    private Boolean isPasswordMatching(){
        return Objects.equals(convertPassword(newPasswordField), convertPassword(repeatNewPasswordField));
    }

    private void clearFields(){
        pinCodeTextField.setText("");
        newPasswordField.setText("");
        repeatNewPasswordField.setText("");
    }

    private void addBotaoVoltarListener(){
        backArrowImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ForgotPasswordView().setVisible(true);
                resetPasswordView.dispose();
            }
        });
    }
}
