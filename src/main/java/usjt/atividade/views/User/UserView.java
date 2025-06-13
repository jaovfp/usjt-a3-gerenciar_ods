package usjt.atividade.views.User;

import usjt.atividade.domain.entities.User;
import usjt.atividade.views.AbstractView;
import usjt.atividade.views.User.UpdateRegistration.UpdateUserPanel;
import usjt.atividade.views.utils.SideMenu.SideMenuPanel;
import usjt.atividade.views.User.ViewEvents.EventsPanel;
import usjt.atividade.views.utils.RoundedButton;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static usjt.atividade.views.utils.ComponentFactory.createRoundedButtonWithIcon;

public class UserView extends AbstractView {

    protected JPanel menuPanel;
    private JPanel currentContent;

    private static final Color BG_SIDE_MENU_COLOR = UIStyle.BG_SIDE_MENU_USER_COLOR;
    private List<JButton> menuButtons;
    private final User user;

    public UserView(String screenTitle, User user) {
        super(screenTitle, UIStyle.USER_ADMIN_DIMENSION.width, UIStyle.USER_ADMIN_DIMENSION.height);
        setBackground(UIStyle.BG_USER_ADMIN_COLOR);
        this.user = user;
        createMenuButtons();
        initPanels();
        layoutPanels();
        render();
    }

    private void createMenuButtons() {
        RoundedButton updateUser = createRoundedButtonWithIcon(
                "Atualizar Cadastro",
                UIStyle.SIDE_MENU_BTN_TEXT_FONT,
                BG_SIDE_MENU_COLOR.brighter(),
                BG_SIDE_MENU_COLOR.brighter().darker(),
                BG_SIDE_MENU_COLOR.brighter().brighter(),
                Color.WHITE,
                "home.png"
        );

        RoundedButton btnCreateEvents = createRoundedButtonWithIcon(
                "Cadastrar Eventos",
                UIStyle.SIDE_MENU_BTN_TEXT_FONT,
                UIStyle.TRANSPARENT_COLOR,
                BG_SIDE_MENU_COLOR.brighter().darker(),
                BG_SIDE_MENU_COLOR.brighter().brighter(),
                Color.WHITE,
                "appointment.png"
        );

        RoundedButton btnEvents = createRoundedButtonWithIcon(
                "Eventos",
                UIStyle.SIDE_MENU_BTN_TEXT_FONT,
                BG_SIDE_MENU_COLOR.brighter(),
                BG_SIDE_MENU_COLOR.brighter().darker(),
                BG_SIDE_MENU_COLOR.brighter().brighter(),
                Color.WHITE,
                "search.png"
        );

        updateUser.addActionListener(e -> setContent(new UpdateUserPanel(user)));
        // btnCreateEvents.addActionListener(e -> setContent(new CadastrarEventosPanel()));
        btnEvents.addActionListener(e -> setContent(new EventsPanel(user)));

        menuButtons = List.of(btnEvents, btnCreateEvents, updateUser);
    }

    public void setContent(JPanel newContent) {
        if (currentContent != null) {
            remove(currentContent);
        }
        currentContent = newContent;
        add(currentContent, BorderLayout.CENTER);
        currentContent.setBackground(UIStyle.BG_USER_ADMIN_COLOR);
        revalidate();
        repaint();
    }

    @Override
    protected void initPanels() {
        menuPanel = new SideMenuPanel(user.getFullname(), user.getProfilePhotoUrl(), BG_SIDE_MENU_COLOR, menuButtons, this);
        currentContent = new EventsPanel(user);
    }

    @Override
    protected void layoutPanels() {
        setLayout(new BorderLayout());
        menuPanel.setPreferredSize(UIStyle.SIDE_MENU_DIMENSION);
        add(menuPanel, BorderLayout.WEST);
        add(currentContent, BorderLayout.CENTER);
    }

}
