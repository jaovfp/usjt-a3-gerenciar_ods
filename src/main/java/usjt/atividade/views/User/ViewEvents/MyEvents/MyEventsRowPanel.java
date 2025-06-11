package usjt.atividade.views.User.ViewEvents.MyEvents;

import usjt.atividade.domain.entities.EventsRequest;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static usjt.atividade.common.utils.DateTimeUtils.dateConverter;
import static usjt.atividade.views.utils.ComponentFactory.*;
import static usjt.atividade.views.utils.UIStyle.defineEventRequestStatusColorByStatus;

public class MyEventsRowPanel extends AbstractPanel {

    private final EventsRequest event;

    private JLabel lblStatus, lblData, lblName, lblOds;
    private JLabel btnVerDetalhes;

    public MyEventsRowPanel(EventsRequest event, Color bgColor) {
        super(bgColor, new Dimension(970, 50));
        this.event = event;
        this.setOpaque(true);
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    protected void initComponents() {
        lblName = createLabel("<html><div style='width:200px'>" + event.getEventName() + "</div></html>", new Font("Segoe UI", Font.BOLD, 12), UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);
        lblOds = createLabel("<html><div style='width:270px'>" + event.getOds().getOdsName() + "</div></html>", new Font("Segoe UI", Font.PLAIN, 11), UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);
        String status = event.getStatus().getStatus();
        Color colorStatus = defineEventRequestStatusColorByStatus(event.getStatus());
        lblStatus = createLabel(status, new Font("Segoe UI", Font.PLAIN, 11), colorStatus, SwingConstants.LEFT);
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
                                .addComponent(lblName, PREFERRED_SIZE, 200, PREFERRED_SIZE)
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
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblName, GroupLayout.Alignment.CENTER)
                                .addComponent(lblStatus, GroupLayout.Alignment.CENTER)
                                .addComponent(lblOds, GroupLayout.Alignment.CENTER)
                                .addComponent(lblData, GroupLayout.Alignment.CENTER)
                                .addComponent(btnVerDetalhes, GroupLayout.Alignment.CENTER)
                        )
        );
    }

    @Override
    public Dimension getPreferredSize() {
        int width = 970;

        int lblNameHeight = getHtmlTextHeight(lblName, 200);
        int lblOdsHeight = getHtmlTextHeight(lblOds, 20);

        int maxHeight = Math.max(lblNameHeight, lblOdsHeight);
        int height = Math.max(50, maxHeight + 20);

        return new Dimension(width, height);
    }

    private int getHtmlTextHeight(JLabel label, int width) {
        if (label == null || label.getText() == null) {
            return 0;
        }
        View view = (View) label.getClientProperty(javax.swing.plaf.basic.BasicHTML.propertyKey);
        if (view != null) {
            view.setSize(width, 0);
            return (int) view.getPreferredSpan(View.Y_AXIS);
        }
        return label.getPreferredSize().height;
    }

    @Override
    protected void addListeners() {
        btnVerDetalhes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showDetailsDialog();
            }
        });
    }

    private void showDetailsDialog() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("📝 Nome do Evento: " + event.getEventName()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📄 Descrição: " + event.getEventDescription()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📌 Status: " + event.getStatus().getStatus()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("🎯 ODS Relacionado: " + event.getOds().getOdsName()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📍 Endereço: " + event.getAddress().toString()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📅 Data do Evento: " + event.getEventDate()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📅 Data da Solicitação: " + dateConverter(event.getCreateDate(), "dd/MM/yyyy HH:mm")));

        JOptionPane.showMessageDialog(this, panel, "Detalhes do Evento", JOptionPane.INFORMATION_MESSAGE);
    }
}
