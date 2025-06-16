package usjt.atividade.views.Admin.ApproveUsers;

import usjt.atividade.domain.entities.EventRequest;
import usjt.atividade.domain.valueObjects.EventRequestStatus;
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

    private final EventRequest eventRequest;
    private JLabel lblEventName;
    private JLabel lblOds;
    private JLabel lblEventDate;
    private JLabel lblEventRequestCreatorName;
    private JLabel lblEventRequestCreatorEmail;
    private JPanel btnSeeMore;
    private JPanel btnApprove;
    private JPanel btnReject;
    private JLabel lblStatus;

    public ApproveRowPanel(EventRequest eventRequest, Color bgColor){
        super(bgColor, new Dimension(1100, 50));
        this.eventRequest = eventRequest;
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

    }

}
