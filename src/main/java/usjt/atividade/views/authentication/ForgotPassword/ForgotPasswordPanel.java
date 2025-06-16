package usjt.atividade.views.authentication.ForgotPassword;

import usjt.atividade.infra.controller.AuthController;
import usjt.atividade.common.Response;
import usjt.atividade.domain.entities.PasswordRecovery;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.authentication.Login.LoginView;
import usjt.atividade.views.authentication.ResetPassword.ResetPasswordView;
import usjt.atividade.views.utils.CustomTextField;
import usjt.atividade.views.utils.RoundedButton;
import usjt.atividade.views.utils.UIStyle;
import static usjt.atividade.views.utils.ComponentFactory.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ForgotPasswordPanel extends AbstractPanel {

    private final ForgotPasswordView forgotPasswordView;
    private JLabel backArrowImageLabel;
    private JLabel forgotPasswordTitleLabel;
    private JLabel forgotPasswordTextLabel;
    private JLabel emailLabel;
    private CustomTextField emailTextField;
    private RoundedButton btnSendPinCodeEmail;
    private final AuthController authController;

    public ForgotPasswordPanel(ForgotPasswordView forgotPasswordView) {
        super(UIStyle.BG_AUTH_COLOR, UIStyle.AUTH_DIMENSION);
        this.forgotPasswordView = forgotPasswordView;
        this.authController = new AuthController();
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    protected void initComponents(){
        backArrowImageLabel = createImageLabel("voltar.png", "icons", SwingConstants.CENTER, 35, 35);
        backArrowImageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordTitleLabel = createLabel("Solicitar alteração de senha", UIStyle.AUTH_TITLE_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        forgotPasswordTextLabel = createLabel(UIStyle.FORGOT_PASSWORD_TEXT, UIStyle.AUTH_TEXT_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        emailLabel = createLabel("E-mail:", UIStyle.AUTH_TEXT_FONT, UIStyle.AUTH_TEXT_COLOR, SwingConstants.CENTER);
        emailTextField = createCustomTextField("Digite seu e-mail...", UIStyle.AUTH_TEXT_COLOR, Color.CYAN);
        btnSendPinCodeEmail = createRoundedButton("Continuar", UIStyle.AUTH_BTN_FONT, UIStyle.AUTH_ACCENT_COLOR, UIStyle.AUTH_TEXT_COLOR, 60, 60);
    }

    @Override
    protected void layoutComponents(){
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(forgotPasswordTitleLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(forgotPasswordTextLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(17, 17, 17)
                                                .addComponent(backArrowImageLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(emailLabel)
                                                        .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(27, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnSendPinCodeEmail, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
                                .addGap(147, 147, 147))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(backArrowImageLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93)
                                .addComponent(forgotPasswordTitleLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(forgotPasswordTextLabel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87)
                                .addComponent(emailLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(btnSendPinCodeEmail, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))
        );
    }

    @Override
    protected void addListeners(){
        addBotaoVoltarListener();
        btnSendPinCodeEmail.addActionListener(e -> btnSendPinCodeEmailClick());
    }

    private void btnSendPinCodeEmailClick() {
        String email = emailTextField.getText();
        btnSendPinCodeEmail.setText("Enviando pin...");
        btnSendPinCodeEmail.setEnabled(false);

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            private Response<PasswordRecovery> response;

            @Override
            protected Void doInBackground() {
                response = authController.requestPasswordReset(email);
                return null;
            }

            @Override
            protected void done() {
                btnSendPinCodeEmail.setText("Continuar");
                btnSendPinCodeEmail.setEnabled(true);

                if (response.isSuccess()) {
                    JOptionPane.showMessageDialog(null, response.getMessage(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    new ResetPasswordView(response.getData()).setVisible(true);
                    forgotPasswordView.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, response.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    clearFields();
                }
            }
        };

        worker.execute();
    }


    private void clearFields(){
        emailTextField.setText("");
    }

    private void addBotaoVoltarListener(){
        backArrowImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LoginView().setVisible(true);
                forgotPasswordView.dispose();
            }
        });
    }
}
