package usjt.atividade.views.User.ViewEvents.RegisteredEvents;

import usjt.atividade.app.Events.DTO.EventSubscribeFilter;
import usjt.atividade.common.PaginatedResponse;
import usjt.atividade.common.Response;
import usjt.atividade.common.StatusCode;
import usjt.atividade.domain.entities.EventSubscribe;
import usjt.atividade.domain.entities.ODS;
import usjt.atividade.domain.entities.User;
import usjt.atividade.infra.controller.EventSubscribeController;
import usjt.atividade.infra.controller.OdsController;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.*;
import usjt.atividade.views.utils.PaginatedPanel.PaginationPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static usjt.atividade.common.utils.ODSUtils.getArrayOdsTopics;
import static usjt.atividade.common.utils.ODSUtils.getOdsIdByName;
import static usjt.atividade.views.utils.ComponentFactory.*;

public class SubscribeEventsPanel extends AbstractPanel {

    private final User user;
    private JLabel odsLabel;
    private JComboBox<String> odsComboBox;
    private JPanel odsPanel;
    private CustomTextField searchField;
    private JPanel searchPanel;
    private List<ODS> odsTopicsList;
    private EventSubscribeController eventSubscribeController;
    private OdsController odsController;
    private JScrollPane listSubscribesPanel;
    private PaginationPanel paginationPanel;

    public SubscribeEventsPanel(User user) {
        super(UIStyle.BG_USER_ADMIN_COLOR, UIStyle.CONTENT_TOPIC_USER_ADMIN_DIMENSION);
        this.user = user;
        this.eventSubscribeController = new EventSubscribeController();
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
        EventSubscribeFilter eventSubscribeFilter = createFilter(searchField.getText().trim(), (String) odsComboBox.getSelectedItem(), user.getUserId().toString(), null);
        Response<PaginatedResponse<EventSubscribe>> response = eventSubscribeController.getPaginatedEventSubscribes(1, 5, eventSubscribeFilter);
        listSubscribesPanel = getListSubscribesPanel(response, 1);
        paginationPanel = getPaginationPanel(response);

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
//                                                .addGap(145)
//                                                .addComponent(filterDatePanel, PREFERRED_SIZE, 170, PREFERRED_SIZE)
//                                                .addGap(145)
                                                .addComponent(searchPanel, PREFERRED_SIZE, 211, PREFERRED_SIZE)
                                        )
                                        .addComponent(listSubscribesPanel, PREFERRED_SIZE, 970, PREFERRED_SIZE)
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
//                                        .addComponent(filterDatePanel, PREFERRED_SIZE, 24, PREFERRED_SIZE)
                                        .addComponent(searchPanel, PREFERRED_SIZE, 24, PREFERRED_SIZE)
                                )
                                .addGap(42)
                                .addComponent(listSubscribesPanel, PREFERRED_SIZE, 360, PREFERRED_SIZE)
                                .addGap(5)
                                .addComponent(paginationPanel, PREFERRED_SIZE, 50, PREFERRED_SIZE)
                        )
        );
    }

    private static JScrollPane createListSubscribesPanel(List<EventSubscribe> subscribes, Color bgListColor, Color rowColor) {
        JPanel listPanel = new JPanel();
        listPanel.setBackground(bgListColor);
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBorder(new EmptyBorder(0, 30, 0, 0));

        List<JPanel> headerLabels = List.of(
                createLabelWithIcon("Evento", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT, "event.png",14, BorderLayout.EAST),
                createLabelWithIcon("ODS",UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT, "sustainable.png", 14, BorderLayout.EAST),
                createLabelWithIcon("Organizador", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT,"", 14, BorderLayout.EAST),
                createLabelWithIcon("Data do Evento", UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.HEADER_FONT, SwingConstants.LEFT,"date.png", 14, BorderLayout.EAST)
        );

        HeaderPanel headerPanel = new HeaderPanel(headerLabels, bgListColor, List.of(200, 200, 100, 100, 160, 160));
        headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        listPanel.add(headerPanel);
        listPanel.add(Box.createVerticalStrut(15));

        for (EventSubscribe subscribe : subscribes) {
            SubscribeEventsRowPanel row = new SubscribeEventsRowPanel(subscribe, rowColor);

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
        scrollPane.getHorizontalScrollBar().setUI(new ModernScrollBarUI(UIStyle.BG_SIDE_MENU_USER_COLOR.brighter(), Color.lightGray));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(6, Integer.MAX_VALUE));
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(6, Integer.MAX_VALUE));

        return scrollPane;
    }

    private PaginationPanel getPaginationPanel(Response<PaginatedResponse<EventSubscribe>> response) {
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

    private JScrollPane getListSubscribesPanel(Response<PaginatedResponse<EventSubscribe>> response, int page){
        if (response.isSuccess()){
            List<EventSubscribe> listSubscribes = response.getData().getData();
            return createListSubscribesPanel(listSubscribes, UIStyle.BG_USER_ADMIN_COLOR, new Color(240, 240, 240) );
        }else if(StatusCode.NOT_FOUND.equals(response.getStatusCode())){
            return createErrorListPanel("Você não se inscreveu em nenhum evento.", UIStyle.BG_USER_ADMIN_COLOR, Color.GRAY);
        }else{
            return createErrorListPanel("Erro ao consultar as suas inscrições.", UIStyle.BG_USER_ADMIN_COLOR, Color.GRAY);
        }
    }

    private void applyFilterWithPage(int page) {
        String searchText = searchField.getText().trim();
        String selectedOds = (String) odsComboBox.getSelectedItem();
        UUID odsId = getOdsIdByName(odsTopicsList, selectedOds);


        EventSubscribeFilter filter = createFilter(searchText, odsId.toString(), user.getUserId().toString(), null);
        int size = paginationPanel.getItemsPerPage();

        Response<PaginatedResponse<EventSubscribe>> response = eventSubscribeController.getPaginatedEventSubscribes(page, size, filter);
        listSubscribesPanel.setViewportView(getListSubscribesPanel(response, page).getViewport().getView());

        if (response.isSuccess()) {
            paginationPanel.refresh(response.getData().getTotalItems(), page);
        }
    }

    private EventSubscribeFilter createFilter(String eventName, String odsId, String userId, String subscribeId){
        return new EventSubscribeFilter(eventName, odsId, userId, subscribeId);
    }

    @Override
    public void addListeners(){

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
