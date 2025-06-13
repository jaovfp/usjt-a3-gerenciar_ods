package usjt.atividade.views.User.ViewEvents.MyEvents;

import usjt.atividade.app.Events.DTO.EventRequestFilter;
import usjt.atividade.domain.entities.EventsRequest;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.domain.valueObjects.EventRequestStatus;
import usjt.atividade.infra.controller.EventController;
import usjt.atividade.common.Response;
import usjt.atividade.common.StatusCode;
import usjt.atividade.domain.entities.User;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.CustomTextField;
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
    private EventController eventController;
    private PaginationPanel paginationPanel;
    private CustomTextField searchField;
    private JComboBox<EventRequestStatus> statusField;

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
        Response<PaginatedResponse<EventsRequest>> response = eventController.getEventRequests(1, 5, filter);
        listEventsPanel = getListEventsPanel(response, 1);
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
        statusField.addActionListener(e -> { applyFilterWithPage(1);});
    }

    private static JScrollPane createMyEventsListPanel(List<EventsRequest> eventos, Color bgListColor, Color rowColor) {
        JPanel listPanel = new JPanel();
        listPanel.setBackground(bgListColor);
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBorder(new EmptyBorder(0, 30, 0, 0));

        for (EventsRequest evento : eventos) {
            MyEventsRowPanel row = new MyEventsRowPanel(evento, rowColor);

            Dimension rowSize = new Dimension(890, 50);
            row.setMaximumSize(rowSize);
            row.setPreferredSize(rowSize);
            row.setMinimumSize(rowSize);

            JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            wrapper.setBackground(bgListColor);
            wrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
            wrapper.add(row);

            listPanel.add(wrapper);
            listPanel.add(Box.createVerticalStrut(20));
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI(UIStyle.BG_SIDE_MENU_USER_COLOR.brighter(), Color.lightGray));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(6, Integer.MAX_VALUE));

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


    private JScrollPane getListEventsPanel(Response<PaginatedResponse<EventsRequest>> response, int page){
        if (response.isSuccess()){
           List<EventsRequest> listEvents = response.getData().getData();
           return createMyEventsListPanel(listEvents, UIStyle.BG_USER_ADMIN_COLOR, new Color(240, 240, 240) );
        }else if(StatusCode.NOT_FOUND.equals(response.getStatusCode())){
            return createErrorPanel("Você não possuí solicitação de eventos.", UIStyle.BG_USER_ADMIN_COLOR, Color.GRAY);
        }else{
            return createErrorPanel("Erro ao consultar as suas solicitações de eventos", UIStyle.BG_USER_ADMIN_COLOR, Color.GRAY);
        }
    }

    private PaginationPanel getPaginationPanel(Response<PaginatedResponse<EventsRequest>> response) {
        int totalItems = 0;
        int itemsPerPage = 5;  // default
        if (response.isSuccess() && !isNull(response.getData().getData())) {
            totalItems = response.getData().getTotalItems();
        }
        PaginationPanel panel = new PaginationPanel(
                totalItems,
                1,
                itemsPerPage,
                e -> {
                    int page = paginationPanel.getCurrentPage();
                    applyFilterWithPage(page);
                },
                UIStyle.USER_ADMIN_PAGINATION_DIMENSION,
                UIStyle.BG_USER_ADMIN_COLOR,
                Color.BLACK
        );
        if (totalItems == 0) {
            panel = new PaginationPanel(0, 1, 10, e -> {}, UIStyle.USER_ADMIN_PAGINATION_DIMENSION, UIStyle.BG_USER_ADMIN_COLOR, Color.BLACK);
            panel.setVisible(false);
        }
        return panel;
    }

    private EventRequestFilter createFilter(String eventName, EventRequestStatus status, String userId, String requestId){
        return new EventRequestFilter(eventName, status, userId, requestId);
    }

    private void applyFilterWithPage(int page) {
        String searchText = searchField.getText().trim();
        EventRequestStatus selectedStatus = (EventRequestStatus) statusField.getSelectedItem();

        EventRequestFilter filter = createFilter(searchText, selectedStatus, user.getUserId().toString(), null);
        int size = paginationPanel.getItemsPerPage();

        Response<PaginatedResponse<EventsRequest>> response = eventController.getEventRequests(page, size, filter);
        listEventsPanel.setViewportView(getListEventsPanel(response, page).getViewport().getView());

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
