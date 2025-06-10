package usjt.atividade.views.User.UpdateRegistration;

import usjt.atividade.domain.entities.User;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;

import java.awt.*;

import static usjt.atividade.views.utils.ComponentFactory.createLabel;

public class UpdateUserPanel extends AbstractPanel {

    private final User user;
    private JLabel title;

    public UpdateUserPanel(User user) {
        super(UIStyle.BG_USER_ADMIN_COLOR, UIStyle.USER_ADMIN_CONTENT_DIMENSION);
        this.user = user;
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    public void initComponents(){
        title = createLabel("Atualizar Cadastro", UIStyle.USER_ADMIN_TITLE_FONT, UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);
    }

    @Override
    public void layoutComponents(){
        JPanel topSpacer = new JPanel();
        topSpacer.setPreferredSize(new Dimension(1100, 50));
        topSpacer.setBackground(UIStyle.BG_USER_ADMIN_COLOR);
    }

    @Override
    public void addListeners(){
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
    }
}
