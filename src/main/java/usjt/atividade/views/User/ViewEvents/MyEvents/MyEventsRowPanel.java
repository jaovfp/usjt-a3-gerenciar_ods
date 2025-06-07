package usjt.atividade.views.User.ViewEvents.MyEvents;

import usjt.atividade.app.Events.DTO.MyEventsRequest;
import usjt.atividade.domain.valueObjects.EventRequestStatus;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.RoundedButton;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static usjt.atividade.common.utils.DateTimeUtils.dateConverter;
import static usjt.atividade.views.utils.ComponentFactory.*;
import static usjt.atividade.views.utils.UIStyle.defineEventRequestStatusColorByStatus;

public class MyEventsRowPanel extends AbstractPanel {

    private final MyEventsRequest event;

    private JLabel lblNome, lblOds, lblStatus, lblData;
    private JLabel btnVerDetalhes;

    public MyEventsRowPanel(MyEventsRequest event, Color bgColor) {
        super(bgColor, new Dimension(970, 50));
        this.event = event;
        this.setOpaque(true);
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    protected void initComponents() {
        lblNome = createLabel(event.getEventName(), new Font("Segoe UI", Font.BOLD, 12), UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);
        String status = EventRequestStatus.valueOf(event.getStatus()).getStatus();
        Color colorStatus = defineEventRequestStatusColorByStatus(EventRequestStatus.valueOf(event.getStatus()));
        lblStatus = createLabel(status, new Font("Segoe UI", Font.PLAIN, 11), colorStatus, SwingConstants.LEFT);
        lblOds = createLabel(event.getOdsName(), new Font("Segoe UI", Font.PLAIN, 11), UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);
        String dateConverted = dateConverter(event.getCreateDate(), "dd/MM/yyyy");
        lblData = createLabel(dateConverted, new Font("Segoe UI", Font.PLAIN, 11), Color.GRAY, SwingConstants.LEFT);
        btnVerDetalhes = createLinkLabel("Ver detalhes", UIStyle.BG_SIDE_MENU_USER_COLOR.brighter(), new Font("Segoe UI", Font.PLAIN, 11));
    }

    @Override
    protected void layoutComponents() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20)
                                .addComponent(lblNome, PREFERRED_SIZE, 200, PREFERRED_SIZE)
                                .addGap(20)
                                .addComponent(lblStatus, PREFERRED_SIZE, 100, PREFERRED_SIZE)
                                .addGap(20)
                                .addComponent(lblOds, PREFERRED_SIZE, 270, PREFERRED_SIZE)
                                .addGap(20)
                                .addComponent(lblData, PREFERRED_SIZE, 100, PREFERRED_SIZE)
                                .addGap(50)
                                .addComponent(btnVerDetalhes, PREFERRED_SIZE, 120, PREFERRED_SIZE)
                                .addGap(20)
        ));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblNome, GroupLayout.Alignment.CENTER, PREFERRED_SIZE, 50, PREFERRED_SIZE)
                        .addComponent(lblStatus, GroupLayout.Alignment.CENTER, PREFERRED_SIZE, 50, PREFERRED_SIZE)
                        .addComponent(lblOds, GroupLayout.Alignment.CENTER, PREFERRED_SIZE, 50, PREFERRED_SIZE)
                        .addComponent(lblData, GroupLayout.Alignment.CENTER, PREFERRED_SIZE, 50, PREFERRED_SIZE)
                        .addComponent(btnVerDetalhes, GroupLayout.Alignment.CENTER, PREFERRED_SIZE, 50, PREFERRED_SIZE)
        );
    }

    private int getTextAreaHeight(JTextArea area, int width) {
        FontMetrics fm = area.getFontMetrics(area.getFont());
        int lines = (int) Math.ceil((double) area.getText().length() * fm.charWidth('a') / width);
        int height = lines * fm.getHeight();
        return Math.max(height, fm.getHeight());
    }

    @Override
    protected void addListeners() {
        //btnVerDetalhes.addActionListener(e -> showDetailsDialog());
    }
}
