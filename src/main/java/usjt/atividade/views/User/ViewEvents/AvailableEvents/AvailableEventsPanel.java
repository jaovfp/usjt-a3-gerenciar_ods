package usjt.atividade.views.User.ViewEvents.AvailableEvents;

import usjt.atividade.app.Events.DTO.EventFilter;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.common.Response;
import usjt.atividade.common.StatusCode;
import usjt.atividade.domain.entities.Event;
import usjt.atividade.domain.entities.ODS;
import usjt.atividade.domain.entities.User;
import usjt.atividade.infra.controller.EventController;
import usjt.atividade.infra.controller.OdsController;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.User.ViewEvents.SubscribeEvents.SubscribeEventsRowPanel;
import usjt.atividade.views.utils.CustomTextField;
import usjt.atividade.views.utils.HeaderPanel;
import usjt.atividade.views.utils.ModernScrollBarUI;
import usjt.atividade.views.utils.PaginatedPanel.PaginationPanel;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static usjt.atividade.common.utils.ODSUtils.*;
import static usjt.atividade.views.utils.ComponentFactory.*;

public class AvailableEventsPanel extends AbstractPanel {

    private final User user;
    private JLabel odsLabel;
    private JComboBox<String> odsComboBox;
    private JPanel odsPanel;
    private CustomTextField searchField;
    private JPanel searchPanel;
    private List<ODS> odsTopicsList;
    private EventController eventController;
    private OdsController odsController;
    private JScrollPane listEventsPanel;
    private PaginationPanel paginationPanel;

    public AvailableEventsPanel(User user) {
        super(UIStyle.BG_USER_ADMIN_COLOR, UIStyle.CONTENT_TOPIC_USER_ADMIN_DIMENSION);
        this.user = user;
        this.eventController = new EventController();
        this.odsController = new OdsController();
        setOdsTopics();
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    public void initComponents(){
        List<String> odsTopics = getArrayOdsTopics(odsTopicsList);
        odsTopics.add(0, "Selecione uma ODS");
        odsLabel = createLabel("Selecione a ODS relacionada ao evento:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        odsLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        odsComboBox = createCustomComboBox(odsTopics.toArray(new String[0]), UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.BG_USER_ADMIN_COLOR, Color.black);
        odsComboBox.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, UIStyle.BG_SIDE_MENU_USER_COLOR));
        applyCustomScrollBarToComboBox(odsComboBox);
        odsPanel = createComboBoxWithIcon(odsComboBox, "sustainable.png", 14, BorderLayout.WEST);

        searchField = createCustomTextField("Busque por um evento...", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.BG_SIDE_MENU_USER_COLOR);
        searchPanel = searchField.withIcon("searchBlue.png", 15, BorderLayout.WEST);

        String selectedOds = getSelectedOdsOrNull(odsComboBox);
        UUID odsIdObj = getOdsIdByName(odsTopicsList, selectedOds);
        String odsId = odsIdObj != null ? odsIdObj.toString() : null;
        EventFilter eventFilter = createFilter(searchField.getText().trim(), odsId, null, null, true);
        Response<PaginatedResponse<Event>> response = eventController.getPaginatedEvents(1, 5, eventFilter);
        listEventsPanel = getListEventsPanel(response, 1);
        paginationPanel = getPaginationPanel(response);
    }

    private EventFilter createFilter(String eventName, String odsId, String userId, String eventId, boolean afterToday){
        return new EventFilter(eventName, odsId, userId, eventId, afterToday);
    }

    @Override
    public void layoutComponents(){
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                        .addGap(55)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                        .addGap(55)
                                                                        .addComponent(odsPanel, PREFERRED_SIZE, 170, PREFERRED_SIZE)
                                                                        .addGap(460)
                                                                        .addComponent(searchPanel, PREFERRED_SIZE, 211, PREFERRED_SIZE)
                                                        )
                                                        .addComponent(listEventsPanel, PREFERRED_SIZE, 970, PREFERRED_SIZE)
                                                        .addComponent(paginationPanel, PREFERRED_SIZE, 970, PREFERRED_SIZE)
                                        )
                        )
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                        .addGap(12)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(odsPanel, PREFERRED_SIZE, 24, PREFERRED_SIZE)
                                                        .addComponent(searchPanel, PREFERRED_SIZE, 24, PREFERRED_SIZE)
                                        )
                                        .addGap(42)
                                        .addComponent(listEventsPanel, PREFERRED_SIZE, 360, PREFERRED_SIZE)
                                        .addGap(5)
                                        .addComponent(paginationPanel, PREFERRED_SIZE, 50, PREFERRED_SIZE)
                        )
        );
    }

    @Override
    public void addListeners(){

        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                applyFilterWithPage(1);
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                applyFilterWithPage(1);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                applyFilterWithPage(1);
            }
        });
        odsComboBox.addActionListener(e -> { applyFilterWithPage(1);});

    }

    private PaginationPanel getPaginationPanel(Response<PaginatedResponse<Event>> response) {
        int totalItems = 0;
        int itemsPerPage = 5;
        if (response.isSuccess() && !isNull(response.getData().getData())) {
            totalItems = response.getData().getTotalItems();
        }
        return createPaginationPanel(
                totalItems,
                1,
                itemsPerPage,
                this::applyFilterWithPage,
                UIStyle.USER_ADMIN_PAGINATION_DIMENSION,
                UIStyle.BG_USER_ADMIN_COLOR,
                Color.BLACK,
                UIStyle.BG_SIDE_MENU_USER_COLOR
        );
    }

    private void applyFilterWithPage(int page) {
        String searchText = searchField.getText().trim();
        String selectedOds = getSelectedOdsOrNull(odsComboBox);
        UUID odsIdObj = getOdsIdByName(odsTopicsList, selectedOds);
        String odsId = odsIdObj != null ? odsIdObj.toString() : null;

        EventFilter filter = createFilter(searchText, odsId, null, null, true);
        int size = paginationPanel.getItemsPerPage();

        Response<PaginatedResponse<Event>> response = eventController.getPaginatedEvents(page, size, filter);
        listEventsPanel.setViewportView(getListEventsPanel(response, page).getViewport().getView());

        if (response.isSuccess()) {
            paginationPanel.refresh(response.getData().getTotalItems(), page);
        }
    }

    private JScrollPane getListEventsPanel(Response<PaginatedResponse<Event>> response, int page){
        if (response.isSuccess()){
            List<Integer> widths = List.of(200, 200, 100, 150);
            List<Integer> gaps = List.of(20, 20, 20, 50);
            List<JPanel> headerLabels = List.of(
                    createLabelWithIcon("Evento", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT, "event.png",14, BorderLayout.EAST),
                    createLabelWithIcon("ODS",UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT, "sustainable.png", 14, BorderLayout.EAST),
                    createLabelWithIcon("Organizador", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT,"", 14, BorderLayout.EAST),
                    createLabelWithIcon("Data do Evento", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT,"date.png", 14, BorderLayout.EAST)
            );
            List<Event> listEvents = response.getData().getData();
            return createListPanel(
                    listEvents,
                    UIStyle.BG_USER_ADMIN_COLOR,
                    headerLabels,
                    widths,
                    gaps,
                    UIStyle.BG_SIDE_MENU_USER_COLOR,
                    (event, refreshCallback) -> new EventRowPanel(event, UIStyle.BG_USER_ADMIN_COLOR, user),
                    null
            );
        }else if(StatusCode.NOT_FOUND.equals(response.getStatusCode())){
            return createErrorListPanel("Não há eventos disponíveis no momento.", UIStyle.BG_USER_ADMIN_COLOR, Color.GRAY);
        }else{
            return createErrorListPanel("Erro ao consultar os eventos disponíveis.", UIStyle.BG_USER_ADMIN_COLOR, Color.GRAY);
        }
    }

    private void setOdsTopics(){
        Response<List<ODS>> response = odsController.getOdsTopics();
        if (response.isSuccess()){
            this.odsTopicsList = response.getData();
        }else {
            this.odsTopicsList = new ArrayList<ODS>();
        }
    }

}
