package usjt.atividade.views.Admin;

import usjt.atividade.domain.entities.User;
import usjt.atividade.views.AbstractView;
import usjt.atividade.views.SideMenu.SideMenuPanel;
import usjt.atividade.views.utils.RoundedButton;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static usjt.atividade.views.utils.ComponentFactory.createRoundedButtonWithIcon;

public class AdminView extends AbstractView {

    protected JPanel menuPanel;
    protected JPanel contentPanel;
    private static final Color BG_SIDE_MENU_COLOR = UIStyle.BG_SIDE_MENU_ADMIN_COLOR;
    private java.util.List<JButton> menuButtons;
    private final User user;

    public AdminView(String screenTitle, User user) {
        super(screenTitle, UIStyle.USER_ADMIN_DIMENSION.width, UIStyle.USER_ADMIN_DIMENSION.height);
        setBackground(UIStyle.BG_USER_ADMIN_COLOR);
        this.user = user;
        createMenuButtons();
        initPanels();
        layoutPanels();
        render();
    }

    private void createMenuButtons(){
        RoundedButton btnHome = createRoundedButtonWithIcon("Inicio", UIStyle.SIDE_MENU_BTN_TEXT_FONT, BG_SIDE_MENU_COLOR.brighter(), BG_SIDE_MENU_COLOR.brighter().darker(), BG_SIDE_MENU_COLOR.brighter().brighter(), Color.WHITE, "home.png");
        RoundedButton btnCreateEvents = createRoundedButtonWithIcon("Aprovar Eventos", UIStyle.SIDE_MENU_BTN_TEXT_FONT, UIStyle.TRANSPARENT_COLOR, BG_SIDE_MENU_COLOR.brighter().darker(), BG_SIDE_MENU_COLOR.brighter().brighter(), Color.WHITE, "approve.png");
        RoundedButton btnSearchEvents = createRoundedButtonWithIcon("Gerenciar Usuários", UIStyle.SIDE_MENU_BTN_TEXT_FONT, BG_SIDE_MENU_COLOR.brighter(), BG_SIDE_MENU_COLOR.brighter().darker(), BG_SIDE_MENU_COLOR.brighter().brighter(), Color.WHITE, "management.png");

        btnHome.addActionListener(e -> setContent(new AdminHomePanel()));
        //btnCreateEvents.addActionListener(e -> setContent(new CadastrarEventosPanel()));
        //btnSearchEvents.addActionListener(e -> setContent(new VerEventosPanel()));

        menuButtons = List.of(btnHome, btnCreateEvents, btnSearchEvents);
    }

    public void setContent(JPanel newContent) {
        contentPanel.removeAll();
        contentPanel.add(newContent, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    @Override
    protected void initPanels(){
        menuPanel = new SideMenuPanel(user.getFullname(), user.getProfilePhotoUrl(), BG_SIDE_MENU_COLOR, menuButtons, this);
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
    }

    @Override
    protected void layoutPanels(){
        setLayout(new BorderLayout());
        menuPanel.setPreferredSize(new Dimension(220, getHeight()));
        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

}
