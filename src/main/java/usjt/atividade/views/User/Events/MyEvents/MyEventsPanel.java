package usjt.atividade.views.User.Events.MyEvents;

import usjt.atividade.app.Events.DTO.MyEventsRequest;
import usjt.atividade.infra.controller.EventController;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.common.Response;
import usjt.atividade.common.StatusCode;
import usjt.atividade.domain.entities.User;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.CustomTextField;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;
import java.util.List;

import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static usjt.atividade.views.utils.ComponentFactory.createCustomTextField;

public class MyEventsPanel extends AbstractPanel {

    private final User user;
    private JPanel searchField;
    private JScrollPane listEventsPanel;
    private EventController eventController;

    public MyEventsPanel(User user) {
        super(UIStyle.BG_USER_ADMIN_COLOR, UIStyle.CONTENT_TOPIC_USER_ADMIN_DIMENSION);
        this.eventController = new EventController();
        this.user = user;
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    public void initComponents(){
        CustomTextField textField = createCustomTextField("Busque por um evento...", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.BG_SIDE_MENU_USER_COLOR);
        searchField = textField.withIcon("searchBlue.png", 15);
        Response<PaginatedResponse<MyEventsRequest>> response = eventController.getEventRequests(user.getUserId(), 1, 10);
        listEventsPanel = getListEventsPanel(response,1);
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
                                        .addComponent(listEventsPanel, PREFERRED_SIZE, 970, PREFERRED_SIZE))
                        ));
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(searchField, PREFERRED_SIZE, 34, PREFERRED_SIZE)
                                .addGap(22)
                                .addComponent(listEventsPanel, PREFERRED_SIZE, 420, PREFERRED_SIZE))
        );
    }

    @Override
    public void addListeners(){

    }

    private static JScrollPane createMyEventsListPanel(List<MyEventsRequest> eventos, Color bgListColor, Color rowColor) {
        JPanel listPanel = new JPanel();
        listPanel.setBackground(bgListColor);
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        for (MyEventsRequest evento : eventos) {
            MyEventsRowPanel row = new MyEventsRowPanel(evento);
            row.setBackground(rowColor);
            row.setAlignmentX(Component.LEFT_ALIGNMENT);
            listPanel.add(row);
            listPanel.add(Box.createVerticalStrut(6));
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        return scrollPane;
    }

    private static JScrollPane createErrorPanel(String errorMessage, Color backgroundColor, Color textColor) {
        JPanel errorPanel = new JPanel();
        errorPanel.setPreferredSize(new Dimension(970, 400));
        errorPanel.setBackground(backgroundColor);
        errorPanel.setLayout(new GridBagLayout());

        JLabel errorLabel = new JLabel(errorMessage);
        errorLabel.setForeground(textColor);
        errorLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        errorPanel.add(errorLabel, new GridBagConstraints());

        JScrollPane scrollPane = new JScrollPane(errorPanel);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(970, 400));

        return scrollPane;
    }


    private JScrollPane getListEventsPanel(Response<PaginatedResponse<MyEventsRequest>> response, int page){
        if (response.isSuccess()){
           List<MyEventsRequest> listEvents = response.getData().getData();
           return createMyEventsListPanel(listEvents, UIStyle.BG_USER_ADMIN_COLOR, Color.WHITE);
        }else if(StatusCode.NOT_FOUND.equals(response.getStatusCode())){
            return createErrorPanel("Você não possuí solicitação de eventos.", UIStyle.BG_USER_ADMIN_COLOR, Color.GRAY);
        }else{
            return createErrorPanel("Erro ao consultar as suas solicitações de eventos", UIStyle.BG_USER_ADMIN_COLOR, Color.GRAY);
        }
    }
}
