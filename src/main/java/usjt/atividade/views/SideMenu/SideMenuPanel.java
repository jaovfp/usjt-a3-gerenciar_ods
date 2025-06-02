package usjt.atividade.views.SideMenu;

import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.authentication.Login.LoginView;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.List;

import static java.util.Objects.isNull;
import static usjt.atividade.common.utils.DateTimeUtils.getFormattedDate;
import static usjt.atividade.views.utils.ComponentFactory.*;

public class SideMenuPanel extends AbstractPanel {

    private final String fullName;
    private final String profileImagePath;
    private final Color backgroundColor;
    private final List<JButton> menuButtons;
    private JPanel userCard;
    private JPanel footer;
    private JLabel profileImage;
    private JLabel logoutButton;

    private JPanel mainPanel;
    private JFrame parentView;

    public SideMenuPanel(String fullName, String profileImagePath, Color backgroundColor, List<JButton> menuButtons, JFrame parentView) {
        super(backgroundColor, UIStyle.SIDE_MENU_DIMENSION);
        this.parentView = parentView;
        this.fullName = fullName;
        this.profileImagePath = profileImagePath;
        this.backgroundColor = backgroundColor;
        this.menuButtons = menuButtons;

        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    protected void initComponents() {
        userCard = createUserCard(fullName, profileImagePath);
        footer = createFooter(backgroundColor);

        mainPanel = new RoundedPanel(100, backgroundColor);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);
    }

    @Override
    protected void layoutComponents() {
        JPanel topSpacer = new JPanel();
        topSpacer.setPreferredSize(new Dimension(250, 50));
        topSpacer.setBackground(UIStyle.BG_USER_ADMIN_COLOR);
        JLabel menuLabel = createImageLabel("menu.png", "icons", SwingConstants.LEFT, 15, 15);
        topSpacer.setLayout(new BorderLayout());
        menuLabel.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 0));
        topSpacer.add(menuLabel, BorderLayout.WEST);


        mainPanel.add(userCard);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(createSeparator());
        mainPanel.add(Box.createVerticalStrut(100));

        if (!isNull(menuButtons)) {
            for (JButton btn : menuButtons) {
                mainPanel.add(wrapButton(btn));
                mainPanel.add(Box.createVerticalStrut(10));
            }
        }

        mainPanel.add(Box.createVerticalStrut(100));
        mainPanel.add(createSeparator());
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(footer);

        add(topSpacer, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createUserCard(String name, String profileImagePath) {
        JPanel card = new JPanel();
        card.setOpaque(false);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);

        if(isNull(profileImagePath)){
            profileImagePath = "default_profile.png";
        }
        profileImage = createRoundedImageLabel(profileImagePath, "userImages", SwingConstants.CENTER, 80, 80);
        profileImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileImage.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setForeground(UIStyle.SIDE_MENU_TEXT_COLOR);
        nameLabel.setFont(UIStyle.SIDE_MENU_TITLE_FONT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel welcome = new JLabel("Seja bem-vindo!");
        welcome.setForeground(Color.LIGHT_GRAY);
        welcome.setFont(UIStyle.SIDE_MENU_TEXT_FONT);
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(profileImage);
        card.add(Box.createVerticalStrut(10));
        card.add(nameLabel);
        card.add(welcome);

        return card;
    }

    private JSeparator createSeparator() {
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        separator.setForeground(Color.CYAN);
        return separator;
    }

    private JPanel wrapButton(JButton button) {
        int width = 200;
        int height = 60;
        button.setPreferredSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        button.setMinimumSize(new Dimension(width, height));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
        wrapper.setOpaque(false);
        wrapper.setMaximumSize(new Dimension(width + 30, height));
        wrapper.setPreferredSize(new Dimension(width + 30, height));
        wrapper.setMinimumSize(new Dimension(width + 30, height));
        wrapper.add(Box.createHorizontalGlue());
        wrapper.add(button);
        wrapper.add(Box.createHorizontalGlue());

        return wrapper;
    }

    private JPanel createFooter(Color bgColor) {
        JPanel footer = new JPanel();
        footer.setOpaque(false);
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel dateLabel = new JLabel(getFormattedDate());
        dateLabel.setForeground(Color.LIGHT_GRAY);
        dateLabel.setFont(UIStyle.SIDE_MENU_TEXT_FONT);
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        logoutButton = createBtnLabelTextAndIcon("Sair", UIStyle.SIDE_MENU_TEXT_FONT, "logout.png");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setForeground(UIStyle.SIDE_MENU_TEXT_COLOR);
        logoutButton.setMaximumSize(new Dimension(100, 30));

        footer.add(dateLabel);
        footer.add(Box.createVerticalStrut(8));
        footer.add(logoutButton);

        return footer;
    }


    static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color bg;

        public RoundedPanel(int radius, Color bg) {
            this.radius = radius;
            this.bg = bg;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(UIStyle.BG_USER_ADMIN_COLOR);
            g2.fillRect(0, 0, getWidth(), getHeight());

            int w = getWidth();
            int h = getHeight();
            int r = radius;

            Path2D path = new Path2D.Float();
            path.moveTo(0, 0);
            path.lineTo(w - r, 0);
            path.quadTo(w, 0, w, r);
            path.lineTo(w, h);
            path.lineTo(0, h);
            path.closePath();

            g2.setColor(bg);
            g2.fill(path);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    @Override
    protected void addListeners() {
        addLogoutListener();
    }

    private void addLogoutListener() {
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Tem certeza que deseja sair?",
                        "Confirmar Logout",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    parentView.dispose();
                    new LoginView().setVisible(true);
                }
            }
        });
    }
}
