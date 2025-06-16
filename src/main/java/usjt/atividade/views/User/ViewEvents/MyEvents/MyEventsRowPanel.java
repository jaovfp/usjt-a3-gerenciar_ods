package usjt.atividade.views.User.ViewEvents.MyEvents;

import usjt.atividade.app.Events.DTO.UpdateEventRequestStatusDto;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.common.Response;
import usjt.atividade.domain.entities.EventRequest;
import usjt.atividade.domain.valueObjects.EventRequestStatus;
import usjt.atividade.domain.valueObjects.UserType;
import usjt.atividade.infra.controller.EventRequestController;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static usjt.atividade.common.utils.DateTimeUtils.dateConverter;
import static usjt.atividade.views.utils.ComponentFactory.*;
import static usjt.atividade.views.utils.RowLayoutUtils.applyRowLayout;
import static usjt.atividade.views.utils.UIStyle.defineEventRequestStatusColorByStatus;

public class MyEventsRowPanel extends AbstractPanel {

    private final EventRequest event;

    private JLabel lblStatus, lblData, lblName, lblOds;
    private JLabel btnVerDetalhes;
    private JPanel btnRequestCancel;
    private final EventRequestController eventRequetController;
    private final Runnable refreshCallback;

    public MyEventsRowPanel(EventRequest event, Color bgColor, Runnable refreshCallback) {
        super(bgColor, new Dimension(970, 50));
        this.event = event;
        this.eventRequetController = new EventRequestController();
        this.refreshCallback = refreshCallback;
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
        String dateConverted = dateConverter(event.getEventDate(), "dd/MM/yyyy");
        lblData = createLabel(dateConverted, new Font("Segoe UI", Font.PLAIN, 11), Color.GRAY, SwingConstants.LEFT);
        btnVerDetalhes = createLinkLabel("Ver detalhes", UIStyle.BG_SIDE_MENU_USER_COLOR.brighter(), new Font("Segoe UI", Font.PLAIN, 11));
        btnRequestCancel = createLabelWithIcon("<html><u> Cancelar Solicitação </u></html>", Color.RED, UIStyle.ROW_BTN_FONT, SwingConstants.LEFT, "clear.png", 12, BorderLayout.WEST);
        btnRequestCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void layoutComponents() {
        List<Component> components = new ArrayList<>(List.of(
                lblName,
                lblStatus,
                lblOds,
                lblData,
                btnVerDetalhes
        ));

        List<Integer> widths = new ArrayList<>(List.of(
                200, // lblName
                100, // lblStatus
                270, // lblOds
                140, // lblData
                120  // btnVerDetalhes
        ));

        List<Integer> gaps = new ArrayList<>(List.of(
                20, // lblName -> lblStatus
                20, // lblStatus -> lblOds
                20, // lblOds -> lblData
                50, // lblData -> btnVerDetalhes
                20  // btnVerDetalhes -> btnRequestCancel (condicional)
        ));

        if (EventRequestStatus.PENDING.equals(event.getStatus())) {
            components.add(btnRequestCancel);
            widths.add(150);
            gaps.add(20);
        }

        applyRowLayout(this, components, widths, gaps);

        int totalWidth = 0;
        for (int w : widths) {
            totalWidth += w;
        }
        for (int g : gaps) {
            totalWidth += g;
        }

        this.setPreferredSize(new Dimension(totalWidth + 60, 50));
    }

    @Override
    protected void addListeners() {
        btnVerDetalhes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showDetailsDialog();
            }
        });
        btnRequestCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btnRequestCancelClick();
            }
        });
    }

    private boolean confirmAction(String message, String title) {
        int confirm = JOptionPane.showConfirmDialog(
                null,
                message,
                title,
                JOptionPane.YES_NO_OPTION
        );
        return confirm == JOptionPane.YES_OPTION;
    }

    private void btnRequestCancelClick(){
        if (confirmAction("Tem certeza que deseja cancelar este evento?", "Confirmação")) {
            UpdateEventRequestStatusDto request = new UpdateEventRequestStatusDto(
                    event.getRequestId(),
                    event.getRequestedBy().getUserId(),
                    UserType.NORMAL,
                    "CANCELED"
            );

            Response<EventRequest> response = eventRequetController.updateStatus(request);

            if (response.isSuccess()) {
                JOptionPane.showMessageDialog(null, MessageConstants.EVENT_REQUEST_CANCELED_SUCCESS, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                refreshCallback.run();
            } else {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
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
        panel.add(new JLabel("📅 Data do Evento: " + dateConverter(event.getEventDate(), "dd/MM/yyyy")));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📅 Data da Solicitação: " + dateConverter(event.getCreateDate(), "dd/MM/yyyy HH:mm")));

        JOptionPane.showMessageDialog(this, panel, "Detalhes do Evento", JOptionPane.INFORMATION_MESSAGE);
    }
}
