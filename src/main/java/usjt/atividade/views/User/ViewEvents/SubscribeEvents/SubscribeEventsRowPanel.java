package usjt.atividade.views.User.ViewEvents.SubscribeEvents;

import usjt.atividade.domain.entities.EventSubscribe;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static usjt.atividade.common.utils.DateTimeUtils.dateConverter;
import static usjt.atividade.views.utils.ComponentFactory.createLabel;
import static usjt.atividade.views.utils.ComponentFactory.createLabelWithIcon;
import static usjt.atividade.views.utils.RowLayoutUtils.applyRowLayout;

public class SubscribeEventsRowPanel extends AbstractPanel {

    private final EventSubscribe eventSubscribe;
    private JLabel eventName;
    private JLabel eventDate;
    private JLabel odsName;
    private JLabel eventCreator;
    private JPanel btnSeeMore;
    private JPanel btnUnsubscribe;

    public SubscribeEventsRowPanel(EventSubscribe eventSubscribe, Color bgColor){
        super(bgColor, new Dimension(970, 50));
        this.eventSubscribe = eventSubscribe;
        this.setOpaque(true);
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    protected void initComponents() {
        eventName = createLabel(eventSubscribe.getEvent().getEventName(), new Font("Segoe UI", Font.BOLD, 12), UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);
        String dateConverted = dateConverter(eventSubscribe.getEvent().getEventDate(), "dd/MM/yyyy");
        eventDate = createLabel(dateConverted, new Font("Segoe UI", Font.PLAIN, 11), UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);
        odsName = createLabel(eventSubscribe.getEvent().getOds().getOdsName(), new Font("Segoe UI", Font.PLAIN, 11), UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);
        eventCreator = createLabel(eventSubscribe.getUser().getFullname(), new Font("Segoe UI", Font.PLAIN, 11), UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);
        btnSeeMore = createLabelWithIcon("<u></html> Ver Detalhes </u></html>", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.ROW_BTN_FONT, SwingConstants.LEFT, "menu.png", 12, BorderLayout.WEST);
        btnSeeMore.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnUnsubscribe = createLabelWithIcon("<u></html> Cancelar Inscrição </u></html>", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.ROW_BTN_FONT, SwingConstants.LEFT, "clear.png", 12, BorderLayout.WEST);
        btnUnsubscribe.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void layoutComponents() {
        List<Component> components = List.of(eventName, odsName, eventCreator, eventDate, btnUnsubscribe, btnSeeMore);
        List<Integer> widths = List.of(200, 200, 100, 100, 160, 160);
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
    protected void addListeners() {

    }

}
