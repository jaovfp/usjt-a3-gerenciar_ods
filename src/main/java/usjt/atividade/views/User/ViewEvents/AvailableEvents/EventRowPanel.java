package usjt.atividade.views.User.ViewEvents.AvailableEvents;

import usjt.atividade.domain.entities.Event;
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

    public EventRowPanel(Event event, Color bgColor){
        super(bgColor, new Dimension(970, 50));
        this.event = event;
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
        btnSeeMore = createLabelWithIcon("<u></html> Ver Detalhes </u></html>", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.ROW_BTN_FONT, SwingConstants.LEFT, "menu.png", 12, BorderLayout.WEST);
        btnSeeMore.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSubscribe = createLabelWithIcon("<u></html> Inscrever-se </u></html>", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.ROW_BTN_FONT, SwingConstants.LEFT, "approveGreen.png", 12, BorderLayout.WEST);
        btnSubscribe.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void layoutComponents(){
        List<Component> components = List.of(eventName, odsName, eventCreator, eventDate, btnSubscribe, btnSeeMore);
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
    public void addListeners(){

    }

}
