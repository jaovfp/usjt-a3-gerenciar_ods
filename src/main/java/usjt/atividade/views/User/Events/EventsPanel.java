package usjt.atividade.views.User.Events;

import usjt.atividade.domain.entities.User;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.User.Events.MyEvents.MyEventsPanel;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

import static usjt.atividade.views.utils.ComponentFactory.*;

public class EventsPanel extends AbstractPanel {

    private JLabel title;
    private JPanel menuPanel;
    private final User user;
    private Map<String, JButton> menuPanelButtons;
    private JPanel contentPanel;
    private CardLayout cardLayout = new CardLayout();

    public EventsPanel(User user) {
        super(UIStyle.BG_USER_ADMIN_COLOR, UIStyle.CONTENT_USER_ADMIN_DIMENSION);
        this.user = user;
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    public void initComponents(){
        title = createLabel("Eventos", UIStyle.USER_ADMIN_TITLE_FONT, UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);
        List<String> buttonLabels = List.of("Eventos Disponíveis", "Meus Eventos", "Minhas Inscrições");
        menuPanelButtons = buttonLabels.stream()
                .collect(Collectors.toMap(
                        label -> label,
                        label -> createMenuButton(label, UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.BG_SIDE_MENU_USER_COLOR.brighter()),
                        (a, b) -> b,
                        LinkedHashMap::new
                ));
        menuPanel = createMenuPanel(750, 75, UIStyle.BG_SIDE_MENU_USER_COLOR, menuPanelButtons.values().toArray(new JButton[0]));
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setPreferredSize(UIStyle.CONTENT_TOPIC_USER_ADMIN_DIMENSION);
        contentPanel.setBackground(UIStyle.BG_USER_ADMIN_COLOR);

        contentPanel.add(new AvailableEventsPanel(user), "Eventos Disponíveis");
        contentPanel.add(new MyEventsPanel(user), "Meus Eventos");
        contentPanel.add(new RegisteredEventsPanel(user), "Minhas Inscrições");

        updateMenuSelection("Meus Eventos");
        cardLayout.show(contentPanel, "Meus Eventos");
    }

    @Override
    public void layoutComponents(){
        JPanel topSpacer = new JPanel();
        topSpacer.setPreferredSize(new Dimension(1100, 50));
        topSpacer.setBackground(UIStyle.BG_USER_ADMIN_COLOR);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(topSpacer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(55)
                                .addComponent(title, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(150)
                                .addComponent(menuPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(topSpacer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(title, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addComponent(menuPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 525, Short.MAX_VALUE)
        );
    }

    @Override
    public void addListeners(){
        menuPanelButtons.forEach((label, button) -> {
            button.addActionListener(e -> {
                cardLayout.show(contentPanel, label);
                updateMenuSelection(label);
            });
        });
    }

    private void updateMenuSelection(String selectedLabel) {
        for (Map.Entry<String, JButton> entry : menuPanelButtons.entrySet()) {
            JButton button = entry.getValue();
            boolean isSelected = entry.getKey().equals(selectedLabel);
            button.putClientProperty("selected", isSelected);
            if (isSelected) {
                button.setForeground(UIStyle.BG_SIDE_MENU_USER_COLOR.brighter().brighter());
            } else {
                button.setForeground(UIStyle.BG_SIDE_MENU_USER_COLOR);
            }
        }
    }
}
