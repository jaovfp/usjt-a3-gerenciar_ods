package usjt.atividade.views.User.Events;

import usjt.atividade.domain.model.User.User;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.CustomTextField;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static usjt.atividade.views.utils.ComponentFactory.createCustomTextField;

public class MyEventsPanel extends AbstractPanel {

    private final User user;
    private JPanel searchField;
    private JScrollPane scrollPane;

    public MyEventsPanel(User user) {
        super(UIStyle.BG_USER_ADMIN_COLOR, UIStyle.CONTENT_TOPIC_USER_ADMIN_DIMENSION);
        this.user = user;
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    public void initComponents(){
        CustomTextField textField = createCustomTextField("Busque por um evento...", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.BG_SIDE_MENU_USER_COLOR);
        searchField = textField.withIcon("searchBlue.png", 15);
        JTable table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
    }

    @Override
    public void layoutComponents(){
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(55)
                                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(searchField, PREFERRED_SIZE, 211, PREFERRED_SIZE)
                                        .addComponent(scrollPane, PREFERRED_SIZE, 970, PREFERRED_SIZE))
                        ));
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(searchField, PREFERRED_SIZE, 34, PREFERRED_SIZE)
                                .addGap(22)
                                .addComponent(scrollPane, PREFERRED_SIZE, 420, PREFERRED_SIZE))
        );
    }

    @Override
    public void addListeners(){

    }
}
