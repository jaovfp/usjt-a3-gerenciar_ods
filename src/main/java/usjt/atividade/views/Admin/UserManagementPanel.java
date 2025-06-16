package usjt.atividade.views.Admin;

import usjt.atividade.app.Adm.AdmService;
import usjt.atividade.common.Response;
import usjt.atividade.domain.entities.User;
import usjt.atividade.infra.controller.AdmController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserManagementPanel extends JPanel {
    private AdmController admController;
    private AdmService admService;
    private int currentPage = 0;
    private final int pageSize = 10;

    private JPanel userListPanel;

    public UserManagementPanel() {
        setLayout(new BorderLayout());

        userListPanel = new JPanel();
        userListPanel.setLayout(new BoxLayout(userListPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(userListPanel), BorderLayout.CENTER);

        JPanel paginationPanel = new JPanel(new FlowLayout());
        JButton btnPrev = new JButton("Anterior");
        JButton btnNext = new JButton("Próximo");

        btnPrev.addActionListener(e -> {
            if (currentPage > 0) {
                currentPage--;
                loadUsers();
            }
        });

        btnNext.addActionListener(e -> {
            currentPage++;
            loadUsers();
        });

        paginationPanel.add(btnPrev);
        paginationPanel.add(btnNext);

        add(paginationPanel, BorderLayout.SOUTH);

        loadUsers();
    }

    private void loadUsers() {
        userListPanel.removeAll();
        Response<List<User>> users = admController.getAllUser();
        for (User user : users.getData()) {
            JLabel lblUser = new JLabel(user.getUserId()+ " - " +user.getFullname() + " - " + user.getEmail());
            userListPanel.add(lblUser);
        }
        revalidate();
        repaint();
    }
}

