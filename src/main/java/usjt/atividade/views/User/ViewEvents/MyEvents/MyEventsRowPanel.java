package usjt.atividade.views.User.ViewEvents.MyEvents;

import usjt.atividade.domain.entities.EventRequest;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static usjt.atividade.common.utils.DateTimeUtils.dateConverter;
import static usjt.atividade.views.utils.ComponentFactory.*;
import static usjt.atividade.views.utils.RowLayoutUtils.applyRowLayout;
import static usjt.atividade.views.utils.UIStyle.defineEventRequestStatusColorByStatus;

public class MyEventsRowPanel extends AbstractPanel {

    private final EventRequest event;

    private JLabel lblStatus, lblData, lblName, lblOds;
    private JLabel btnVerDetalhes;

    public MyEventsRowPanel(EventRequest event, Color bgColor) {
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
        List<Component> components = List.of(lblName, lblStatus, lblOds, lblData, btnVerDetalhes);
        List<Integer> widths = List.of(200, 100, 270, 100, 120);
        List<Integer> gaps = List.of(20, 20, 20, 50);

        applyRowLayout(this, components, widths, gaps);
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
