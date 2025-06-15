package usjt.atividade.views.Admin.ApproveUsers;

import usjt.atividade.app.Events.DTO.UpdateEventRequestStatusDto;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.common.Response;
import usjt.atividade.domain.entities.EventRequest;
import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.valueObjects.EventRequestStatus;
import usjt.atividade.infra.controller.EventRequestController;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static usjt.atividade.common.utils.DateTimeUtils.dateConverter;
import static usjt.atividade.views.utils.ComponentFactory.createLabel;
import static usjt.atividade.views.utils.ComponentFactory.createLabelWithIcon;
import static usjt.atividade.views.utils.RowLayoutUtils.applyRowLayout;
import static usjt.atividade.views.utils.UIStyle.defineEventRequestStatusColorByStatus;

public class ApproveRowPanel extends AbstractPanel {

    private EventRequest eventRequest;
    private JLabel lblEventName;
    private JLabel lblOds;
    private JLabel lblEventDate;
    private JLabel lblEventRequestCreatorName;
    private JLabel lblEventRequestCreatorEmail;
    private JPanel btnSeeMore;
    private JPanel btnApprove;
    private JPanel btnReject;
    private JLabel lblStatus;
    private EventRequestController eventRequestController;
    private final User user;
    private final Runnable refreshCallback;

    public ApproveRowPanel(EventRequest eventRequest, Color bgColor, EventRequestController eventRequestController, User user, Runnable refreshCallback){
        super(bgColor, new Dimension(1100, 50));
        this.eventRequest = eventRequest;
        this.eventRequestController = eventRequestController;
        this.user = user;
        this.refreshCallback = refreshCallback;
        this.setOpaque(true);
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    protected void initComponents() {
        lblEventName = createLabel(eventRequest.getEventName(), new Font("Segoe UI", Font.BOLD, 12), UIStyle.BG_SIDE_MENU_ADMIN_COLOR, SwingConstants.LEFT);
        lblOds = createLabel(eventRequest.getOds().getOdsName(), new Font("Segoe UI", Font.PLAIN, 11), UIStyle.BG_SIDE_MENU_ADMIN_COLOR, SwingConstants.LEFT);
        String dateConverted = dateConverter(eventRequest.getCreateDate(), "dd/MM/yyyy");

        String status = eventRequest.getStatus().getStatus();
        Color colorStatus = defineEventRequestStatusColorByStatus(eventRequest.getStatus());
        lblStatus = createLabel(status, new Font("Segoe UI", Font.PLAIN, 11), colorStatus, SwingConstants.LEFT);

        lblEventDate = createLabel(dateConverted, new Font("Segoe UI", Font.PLAIN, 11), Color.GRAY, SwingConstants.LEFT);
        lblEventRequestCreatorName = createLabel(eventRequest.getRequestedBy().getFullname(), new Font("Segoe UI", Font.PLAIN, 11), Color.BLACK, SwingConstants.LEFT);
        lblEventRequestCreatorEmail = createLabel(eventRequest.getRequestedBy().getEmail().getValue(), new Font("Segoe UI", Font.PLAIN, 11), Color.BLACK, SwingConstants.LEFT);
        btnApprove = createLabelWithIcon("<html><u> Aprovar Evento </u></html>", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.ROW_BTN_FONT, SwingConstants.LEFT, "approveGreen.png", 12, BorderLayout.WEST);
        btnApprove.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnReject = createLabelWithIcon("<html><u> Rejeitar Evento </u></html>", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.ROW_BTN_FONT, SwingConstants.LEFT, "clear.png", 12, BorderLayout.WEST);
        btnReject.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSeeMore = createLabelWithIcon("<html><u> Ver Detalhes </u></html>", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.ROW_BTN_FONT, SwingConstants.LEFT, "menu.png", 12, BorderLayout.WEST);
        btnSeeMore.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void layoutComponents() {
        List<Component> components = List.of(
                lblEventName,
                lblStatus,
                lblEventRequestCreatorName,
                lblEventRequestCreatorEmail,
                lblOds,
                lblEventDate,
                btnSeeMore
        );

        List<Integer> widths = List.of(200, 100, 150, 200, 250, 150, 160);
        List<Integer> gaps = List.of(20, 20, 20, 20,20,50);

        if (EventRequestStatus.PENDING.equals(eventRequest.getStatus())) {
            components = new java.util.ArrayList<>(components);
            widths = new java.util.ArrayList<>(widths);
            gaps = new java.util.ArrayList<>(gaps);

            components.add(btnApprove);
            components.add(btnReject);

            widths.add(160);
            widths.add(160);
            gaps.add(50);
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
        btnSeeMore.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showAdminDetailsDialog();
            }
        });
        btnApprove.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btnApproveClick();
            }
        });
        btnReject.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btnRejectClick();
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

    private void btnApproveClick(){
        if (confirmAction("Tem certeza que deseja aprovar este evento?", "Confirmação")) {
            UpdateEventRequestStatusDto request = new UpdateEventRequestStatusDto(
                    eventRequest.getRequestId(),
                    eventRequest.getRequestedBy().getUserId(),
                    user.getType(),
                    "APPROVED"
            );

            Response<EventRequest> response = eventRequestController.updateStatus(request);

            if (response.isSuccess()) {
                JOptionPane.showMessageDialog(null, MessageConstants.EVENT_REQUEST_APPROVED_SUCCESS, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                refreshCallback.run();
            } else {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnRejectClick(){
        if (confirmAction("Tem certeza que deseja cancelar este evento?", "Confirmação")) {
            UpdateEventRequestStatusDto request = new UpdateEventRequestStatusDto(
                    eventRequest.getRequestId(),
                    eventRequest.getRequestedBy().getUserId(),
                    user.getType(),
                    "REJECTED"
            );

            Response<EventRequest> response = eventRequestController.updateStatus(request);

            if (response.isSuccess()) {
                JOptionPane.showMessageDialog(null, MessageConstants.EVENT_REQUEST_REJECT_SUCCESS, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                refreshCallback.run();
            } else {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showAdminDetailsDialog() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        User creator = eventRequest.getRequestedBy();

        panel.add(new JLabel("📝 Nome do Evento: " + eventRequest.getEventName()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📄 Descrição: " + eventRequest.getEventDescription()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📌 Status: " + eventRequest.getStatus().getStatus()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("🎯 ODS Relacionado: " + eventRequest.getOds().getOdsName()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📍 Endereço do Evento: " + eventRequest.getAddress()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📅 Data do Evento: " + eventRequest.getEventDate()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📅 Data da Solicitação: " + dateConverter(eventRequest.getCreateDate(), "dd/MM/yyyy HH:mm")));
        panel.add(Box.createVerticalStrut(15));

        panel.add(new JLabel("👤 Criador: " + creator.getFullname()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📧 Email: " + creator.getEmail().getValue()));
        panel.add(Box.createVerticalStrut(10));
        if (creator.getCpf() != null)
            panel.add(new JLabel("🆔 CPF: " + creator.getCpf().getValue()));
        if (creator.getPhoneNumber() != null)
            panel.add(new JLabel("📞 Telefone: " + creator.getPhoneNumber()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("🧑 Tipo de Usuário: " + creator.getType()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("✅ Ativo: " + (creator.isActive() ? "Sim" : "Não")));

        JOptionPane.showMessageDialog(this, panel, "Detalhes do Evento (Admin)", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
