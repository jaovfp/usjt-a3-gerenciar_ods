package usjt.atividade;

import usjt.atividade.views.authentication.Login.LoginView;

import javax.swing.*;

public class GerenciarODS {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}