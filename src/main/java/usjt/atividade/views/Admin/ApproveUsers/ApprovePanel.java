package usjt.atividade.views.Admin.ApproveUsers;

import usjt.atividade.app.Events.DTO.EventRequestFilter;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.common.Response;
import usjt.atividade.common.StatusCode;
import usjt.atividade.domain.entities.EventRequest;
import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.valueObjects.EventRequestStatus;
import usjt.atividade.infra.controller.EventRequestController;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.CustomTextField;
import usjt.atividade.views.utils.PaginatedPanel.PaginationPanel;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.util.Objects.isNull;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static usjt.atividade.common.utils.DateTimeUtils.dateConverter;
import static usjt.atividade.views.utils.ComponentFactory.*;
import static usjt.atividade.views.utils.ComponentFactory.createLabelWithIcon;

public class ApprovePanel extends AbstractPanel {

    private final User user;
    private final EventRequestController eventRequestController;
    private CustomTextField searchUserField;
    private CustomTextField searchEventField;
    private JPanel searchUserPanel;
    private JPanel searchEventPanel;
    private JLabel title;
    private JScrollPane listEventRequestsPanel;
    private PaginationPanel paginationPanel;
    private JComboBox<EventRequestStatus> statusField;

    public ApprovePanel(User user) {
        super(UIStyle.BG_USER_ADMIN_COLOR, UIStyle.CONTENT_TOPIC_USER_ADMIN_DIMENSION);
        this.user = user;
        this.eventRequestController = new EventRequestController();
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    public void initComponents(){
        title = createLabel("Aprovar Eventos", UIStyle.USER_ADMIN_TITLE_FONT, UIStyle.BG_SIDE_MENU_ADMIN_COLOR, SwingConstants.LEFT);

        statusField = createCustomComboBox(
                EventRequestStatus.values(),
                UIStyle.BG_SIDE_MENU_ADMIN_COLOR,
                UIStyle.BG_USER_ADMIN_COLOR,
                Color.GRAY
        );
        statusField.addItem(null);
        statusField.setRenderer(createEventStatusRenderer());
        statusField.setSelectedItem(null);
        applyCustomScrollBarToComboBox(statusField);

        searchUserField = createCustomTextField("Busque por um email...", UIStyle.BG_SIDE_MENU_ADMIN_COLOR, UIStyle.BG_SIDE_MENU_ADMIN_COLOR);
        searchUserPanel = searchUserField.withIcon("searchGreen.png", 15, BorderLayout.WEST);
        searchEventField = createCustomTextField("Busque por um evento...", UIStyle.BG_SIDE_MENU_ADMIN_COLOR, UIStyle.BG_SIDE_MENU_ADMIN_COLOR);
        searchEventPanel = searchEventField.withIcon("searchGreen.png", 15, BorderLayout.WEST);

        EventRequestFilter eventRequestFilter = createFilter(searchEventField.getText(), (EventRequestStatus) statusField.getSelectedItem(),searchUserField.getText(), null, null);
        Response<PaginatedResponse<EventRequest>> response = eventRequestController.getEventRequests(1, 6, eventRequestFilter);
        listEventRequestsPanel = getListEventsPanel(response);
        paginationPanel = getPaginationPanel(response);
    }

    @Override
    public void layoutComponents(){
        JPanel topSpacer = new JPanel();
        topSpacer.setPreferredSize(new Dimension(1100, 50));
        topSpacer.setBackground(UIStyle.BG_USER_ADMIN_COLOR);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(topSpacer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(55)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(title, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(55)
                                                .addComponent(searchEventPanel, PREFERRED_SIZE, 170, PREFERRED_SIZE)
                                                .addGap(145)
                                                .addComponent(statusField, PREFERRED_SIZE, 170, PREFERRED_SIZE)
                                                .addGap(145)
                                                .addComponent(searchUserPanel, PREFERRED_SIZE, 170, PREFERRED_SIZE)
                                        )
                                        .addComponent(paginationPanel, PREFERRED_SIZE, 970, PREFERRED_SIZE)
                                )
                        )
                        .addComponent(listEventRequestsPanel)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(topSpacer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(title, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchEventPanel, PREFERRED_SIZE, 24, PREFERRED_SIZE)
                                        .addComponent(statusField, PREFERRED_SIZE, 24, PREFERRED_SIZE)
                                        .addComponent(searchUserPanel, PREFERRED_SIZE, 24, PREFERRED_SIZE)
                        )
                        .addGap(20)
                        .addComponent(listEventRequestsPanel)
                        .addGap(5)
                        .addComponent(paginationPanel, PREFERRED_SIZE, 40, PREFERRED_SIZE)
        );
    }

    private EventRequestFilter createFilter(String eventName, EventRequestStatus status, String creatorEmail, String userId, String requestId){
        return new EventRequestFilter(eventName, status, creatorEmail, userId, requestId);
    }

    private JScrollPane getListEventsPanel(Response<PaginatedResponse<EventRequest>> response) {
        if (response.isSuccess()) {
            List<EventRequest> listEvents = response.getData().getData();
            List<JPanel> headerLabels = List.of(
                    createLabelWithIcon("Evento", UIStyle.BG_SIDE_MENU_ADMIN_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT, "event.png",14, BorderLayout.EAST),
                    createLabelWithIcon("Status", UIStyle.BG_SIDE_MENU_ADMIN_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT, "approveGreen.png",14, BorderLayout.EAST),
                    createLabelWithIcon("Organizador", UIStyle.BG_SIDE_MENU_ADMIN_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT,"user.png", 14, BorderLayout.EAST),
                    createLabelWithIcon("Email do Organizador", UIStyle.BG_SIDE_MENU_ADMIN_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT,"email.png", 14, BorderLayout.EAST),
                    createLabelWithIcon("ODS",UIStyle.BG_SIDE_MENU_ADMIN_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT, "sustainable.png", 14, BorderLayout.EAST),
                    createLabelWithIcon("Data do Evento", UIStyle.BG_SIDE_MENU_ADMIN_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT,"date.png", 14, BorderLayout.EAST)
            );
            List<Integer> widths = List.of(200, 100, 150, 200, 250, 150);
            List<Integer> gaps = List.of(20, 20, 20, 20, 20);
            return createListPanel(
                    listEvents,
                    UIStyle.BG_USER_ADMIN_COLOR,
                    headerLabels,
                    widths,
                    gaps,
                    UIStyle.BG_SIDE_MENU_ADMIN_COLOR.brighter(),
                    (eventRequest, refreshCallback) -> new ApproveRowPanel(eventRequest, UIStyle.BG_USER_ADMIN_COLOR, eventRequestController, user, refreshCallback),
                    this::refreshEventRequestsList
            );
        } else if (StatusCode.NOT_FOUND.equals(response.getStatusCode())) {
            return createErrorListPanel("Não há solicitações de eventos.", UIStyle.BG_USER_ADMIN_COLOR, Color.GRAY);
        } else {
            return createErrorListPanel("Erro ao consultar solicitações de eventos", UIStyle.BG_USER_ADMIN_COLOR, Color.GRAY);
        }
    }

    private void applyFilterWithPage(int page) {
        String searchEventText = searchEventField.getText().trim();
        String searchEmailText= searchUserField.getText().trim();
        EventRequestStatus selectedStatus = (EventRequestStatus) statusField.getSelectedItem();
        EventRequestFilter filter = createFilter(searchEventText, selectedStatus, searchEmailText, null, null);
        int size = paginationPanel.getItemsPerPage();
        Response<PaginatedResponse<EventRequest>> response = eventRequestController.getEventRequests(page, size, filter);
        listEventRequestsPanel.setViewportView(getListEventsPanel(response).getViewport().getView());
        if (response.isSuccess()) {
            paginationPanel.refresh(response.getData().getTotalItems(), page);
        }
    }

    private PaginationPanel getPaginationPanel(Response<PaginatedResponse<EventRequest>> response) {
        int totalItems = 0;
        int itemsPerPage = 6;
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
                UIStyle.BG_SIDE_MENU_ADMIN_COLOR
        );
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

    @Override
    public void addListeners(){
        searchUserField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
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

        searchEventField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
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

    private void refreshEventRequestsList() {
        EventRequestFilter eventRequestFilter = createFilter(
                searchEventField.getText(),
                (EventRequestStatus) statusField.getSelectedItem(),
                searchUserField.getText(),
                null,
                null
        );

        Response<PaginatedResponse<EventRequest>> response = eventRequestController.getEventRequests(1, 6, eventRequestFilter);

        JScrollPane newScrollPane = getListEventsPanel(response);
        Component newView = newScrollPane.getViewport().getView();
        listEventRequestsPanel.setViewportView(newView);

        listEventRequestsPanel.revalidate();
        listEventRequestsPanel.repaint();
    }
}
