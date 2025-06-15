package usjt.atividade.views.User.ViewEvents.MyEvents;

import usjt.atividade.app.Events.DTO.EventRequestFilter;
import usjt.atividade.domain.entities.EventRequest;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.domain.valueObjects.EventRequestStatus;
import usjt.atividade.infra.controller.EventRequestController;
import usjt.atividade.common.Response;
import usjt.atividade.common.StatusCode;
import usjt.atividade.domain.entities.User;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.CustomTextField;
import usjt.atividade.views.utils.HeaderPanel;
import usjt.atividade.views.utils.ModernScrollBarUI;
import usjt.atividade.views.utils.PaginatedPanel.PaginationPanel;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.util.List;

import static java.util.Objects.isNull;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static usjt.atividade.views.utils.ComponentFactory.*;

public class MyEventsPanel extends AbstractPanel {

    private final User user;
    private JPanel searchPanel;
    private JScrollPane listEventsPanel;
    private EventRequestController eventRequestController;
    private PaginationPanel paginationPanel;
    private CustomTextField searchField;
    private JComboBox<EventRequestStatus> statusField;

    public MyEventsPanel(User user) {
        super(UIStyle.BG_USER_ADMIN_COLOR, UIStyle.CONTENT_USER_ADMIN_DIMENSION);
        this.eventRequestController = new EventRequestController();
        this.user = user;
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    public void initComponents(){
        statusField = createCustomComboBox(
                EventRequestStatus.values(),
                UIStyle.BG_SIDE_MENU_USER_COLOR,
                UIStyle.BG_USER_ADMIN_COLOR,
                Color.GRAY
        );
        statusField.addItem(null);
        statusField.setRenderer(createEventStatusRenderer());
        statusField.setSelectedItem(null);
        applyCustomScrollBarToComboBox(statusField);

        searchField = createCustomTextField("Busque por um evento...", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.BG_SIDE_MENU_USER_COLOR);
        searchPanel = searchField.withIcon("searchBlue.png", 15, BorderLayout.WEST);

        EventRequestFilter filter = createFilter(searchField.getText().trim(), (EventRequestStatus) statusField.getSelectedItem(), user.getUserId().toString(), null);
        Response<PaginatedResponse<EventRequest>> response = eventRequestController.getEventRequests(1, 5, filter);
        listEventsPanel = getListEventsPanel(response);
        paginationPanel = getPaginationPanel(response);
    }

    @Override
    public void layoutComponents(){
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(55)
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(55)
                                                .addComponent(statusField, PREFERRED_SIZE, 170, PREFERRED_SIZE)
                                                .addGap(460)
                                                .addComponent(searchPanel, PREFERRED_SIZE, 211, PREFERRED_SIZE)
                                        )
                                        .addComponent(listEventsPanel, PREFERRED_SIZE, 970, PREFERRED_SIZE)
                                        .addComponent(paginationPanel, PREFERRED_SIZE, 970, PREFERRED_SIZE)
                                )
                        )
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(12)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(statusField, PREFERRED_SIZE, 24, PREFERRED_SIZE)
                                        .addComponent(searchPanel, PREFERRED_SIZE, 24, PREFERRED_SIZE)
                                )
                                .addGap(42)
                                .addComponent(listEventsPanel, PREFERRED_SIZE, 350, PREFERRED_SIZE)
                                .addGap(15)
                                .addComponent(paginationPanel, PREFERRED_SIZE, 40, PREFERRED_SIZE)
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
        statusField.addActionListener(e -> { applyFilterWithPage(1);});
    }

    private JScrollPane getListEventsPanel(Response<PaginatedResponse<EventRequest>> response){
        if (response.isSuccess()){
           List<EventRequest> listEvents = response.getData().getData();
            List<JPanel> headerLabels = List.of(
                    createLabelWithIcon("Evento", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT, "event.png",14, BorderLayout.EAST),
                    createLabelWithIcon("Status",UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT, "approveGreen.png", 14, BorderLayout.EAST),
                    createLabelWithIcon("ODS", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT,"sustainable.png", 14, BorderLayout.EAST),
                    createLabelWithIcon("Data", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT,"date.png", 14, BorderLayout.EAST)
            );
            List<Integer> gaps =  List.of(20, 20, 20,50);
            List<Integer> width = List.of(200, 100, 270, 100, 120);
           return createListPanel(
               listEvents,
               UIStyle.BG_USER_ADMIN_COLOR,
               headerLabels,
               width,
               gaps,
               UIStyle.BG_SIDE_MENU_USER_COLOR,
   evento -> new MyEventsRowPanel(evento, UIStyle.BG_USER_ADMIN_COLOR));
        }else if(StatusCode.NOT_FOUND.equals(response.getStatusCode())){
            return createErrorListPanel("Você não possuí solicitação de eventos.", UIStyle.BG_USER_ADMIN_COLOR, Color.GRAY);
        }else{
            return createErrorListPanel("Erro ao consultar as suas solicitações de eventos", UIStyle.BG_USER_ADMIN_COLOR, Color.GRAY);
        }
    }

    private PaginationPanel getPaginationPanel(Response<PaginatedResponse<EventRequest>> response) {
        int totalItems = 0;
        int itemsPerPage = 5;  // default
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

    private EventRequestFilter createFilter(String eventName, EventRequestStatus status, String userId, String requestId){
        return new EventRequestFilter(eventName, status, null, userId, requestId);
    }

    private void applyFilterWithPage(int page) {
        String searchText = searchField.getText().trim();
        EventRequestStatus selectedStatus = (EventRequestStatus) statusField.getSelectedItem();

        EventRequestFilter filter = createFilter(searchText, selectedStatus, user.getUserId().toString(), null);
        int size = paginationPanel.getItemsPerPage();

        Response<PaginatedResponse<EventRequest>> response = eventRequestController.getEventRequests(page, size, filter);
        listEventsPanel.setViewportView(getListEventsPanel(response).getViewport().getView());

        if (response.isSuccess()) {
            paginationPanel.refresh(response.getData().getTotalItems(), page);
        }
    }

    private DefaultListCellRenderer createEventStatusRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value == null) {
                    setText("Todos os status");
                } else if (value instanceof EventRequestStatus) {
                    setText(((EventRequestStatus) value).getStatus());
                } else {
                    setText("");
                }

                return this;
            }
        };
    }
}
