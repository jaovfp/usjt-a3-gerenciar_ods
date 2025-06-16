package usjt.atividade.views.User.ViewEvents.AvailableEvents;

import usjt.atividade.app.Events.DTO.EventSubscribeDto;
import usjt.atividade.app.Events.DTO.UpdateEventRequestStatusDto;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.common.Response;
import usjt.atividade.domain.entities.Event;
import usjt.atividade.domain.entities.EventRequest;
import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.valueObjects.UserType;
import usjt.atividade.infra.controller.EventController;
import usjt.atividade.infra.controller.EventSubscribeController;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static usjt.atividade.common.utils.DateTimeUtils.dateConverter;
import static usjt.atividade.views.utils.ComponentFactory.createLabel;
import static usjt.atividade.views.utils.ComponentFactory.createLabelWithIcon;
import static usjt.atividade.views.utils.RowLayoutUtils.applyRowLayout;

public class EventRowPanel extends AbstractPanel {

    private final Event event;
    private JLabel eventName;
    private JLabel eventDate;
    private JLabel odsName;
    private JLabel eventCreator;
    private JPanel btnSeeMore;
    private JPanel btnSubscribe;
    private final User user;
    private final EventSubscribeController eventSubscribeController;

    public EventRowPanel(Event event, Color bgColor, User user){
        super(bgColor, new Dimension(970, 50));
        this.event = event;
        this.eventSubscribeController = new EventSubscribeController();
        this.user = user;
        this.setOpaque(true);
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    public void initComponents(){
        eventName = createLabel(event.getEventName(), new Font("Segoe UI", Font.BOLD, 12), UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);
        String dateConverted = dateConverter(event.getEventDate(), "dd/MM/yyyy");
        eventDate = createLabel(dateConverted, new Font("Segoe UI", Font.PLAIN, 11), UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);
        odsName = createLabel(event.getOds().getOdsName(), new Font("Segoe UI", Font.PLAIN, 11), UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);
        eventCreator = createLabel(event.getCreatedBy().getFullname(), new Font("Segoe UI", Font.PLAIN, 11), UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);
        btnSeeMore = createLabelWithIcon("<html><u> Ver Detalhes </u></html>", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.ROW_BTN_FONT, SwingConstants.LEFT, "menu.png", 12, BorderLayout.WEST);
        btnSeeMore.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSubscribe = createLabelWithIcon("<html><u> Participar </u></html>", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.ROW_BTN_FONT, SwingConstants.LEFT, "approveGreen.png", 12, BorderLayout.WEST);
        btnSubscribe.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void layoutComponents(){
        List<Component> components = List.of(eventName, odsName, eventCreator, eventDate, btnSeeMore, btnSubscribe);
        List<Integer> widths = List.of(200, 200, 100, 150, 160, 160);
        List<Integer> gaps = List.of(20, 20, 20, 20, 50, 20);

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
    public void addListeners(){
        btnSeeMore.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showAvailableEventDetailsDialog(event);
            }
        });
        btnSubscribe.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btnSubscribeClick();
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

    private void btnSubscribeClick(){
        if (confirmAction("Tem certeza que deseja participar deste evento?", "Confirmação")) {
            EventSubscribeDto request = new EventSubscribeDto(event.getEventId(), user.getUserId());

            Response<Void> response = eventSubscribeController.subscribe(request);

            if (response.isSuccess()) {
                JOptionPane.showMessageDialog(null, MessageConstants.SUBSCRIBE_SUCCESS, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showAvailableEventDetailsDialog(Event event) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("📝 Nome do Evento: " + event.getEventName()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📄 Descrição: " + event.getEventDescription()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("🎯 ODS Relacionado: " + event.getOds().getOdsName()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("👥 Total de Inscritos: " + event.getTotalRegistrations()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📍 Endereço: " + event.getAddress().toString()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📅 Data do Evento: " + dateConverter(event.getEventDate(), "dd/MM/yyyy")));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("👤 Criado por: " + event.getCreatedBy().getFullname()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("✉️ Contato (e-mail): " + event.getCreatedBy().getEmail().getValue()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📞 Contato (telefone): " + event.getCreatedBy().getPhoneNumberValue()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("📅 Data de Criação: " + dateConverter(event.getCreateDate(), "dd/MM/yyyy HH:mm")));

        JOptionPane.showMessageDialog(this, panel, "Detalhes do Evento Disponível", JOptionPane.INFORMATION_MESSAGE);
    }

}
