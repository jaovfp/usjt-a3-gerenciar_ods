package usjt.atividade.views.User.CreateEvents;

import usjt.atividade.app.Events.DTO.CreateEventRequestDto;
import usjt.atividade.app.Exceptions.NotFoundException;
import usjt.atividade.app.User.dto.requests.UpdateUserRequest;
import usjt.atividade.common.Response;
import usjt.atividade.domain.entities.ODS;
import usjt.atividade.domain.entities.User;
import usjt.atividade.infra.Cep.Dto.AddressDto;
import usjt.atividade.infra.Cep.ViaCepService;
import usjt.atividade.infra.controller.EventRequestController;
import usjt.atividade.infra.controller.OdsController;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static usjt.atividade.common.utils.DateTimeUtils.parseToLocalDate;
import static usjt.atividade.common.utils.ODSUtils.getArrayOdsTopics;
import static usjt.atividade.common.utils.ODSUtils.getOdsIdByName;
import static usjt.atividade.views.utils.ComponentFactory.*;

public class CreateEventsPanel extends AbstractPanel {

    private final User user;
    private JLabel title;
    private JLabel eventNameLabel;
    private JLabel odsLabel;
    private JLabel eventDateLabel;
    private JLabel eventDescriptionLabel;
    private JLabel cepLabel;
    private JLabel addressLineLabel;
    private JLabel cityLabel;
    private JLabel stateLabel;
    private CustomFormattedCepField cepField;
    private CustomFormattedDateTimeField eventDateField;
    private CustomTextField eventNameField;
    private CustomTextField addressLineField;
    private CustomTextField cityField;
    private CustomTextField stateField;
    private CustomTextArea eventDescriptionField;
    private JComboBox<String> odsComboBox;
    private JPanel eventNamePanel;
    private JPanel eventDatePanel;
    private JPanel odsPanel;
    private JPanel cityPanel;
    private JPanel statePanel;
    private JLabel searchCep;
    private JPanel addressLinePanel;
    private RoundedButton btnCreate;
    private List<ODS> odsList;
    private final EventRequestController eventRequestController;
    private final OdsController odsController;
    private JScrollPane eventDescriptionScrollPane;
    private final ViaCepService cepService;

    public CreateEventsPanel(User user){
        super(UIStyle.BG_USER_ADMIN_COLOR, UIStyle.USER_ADMIN_CONTENT_DIMENSION);
        this.user = user;
        this.odsController = new OdsController();
        this.eventRequestController = new EventRequestController();
        this.cepService = new ViaCepService();

        setOdsTopics();
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    public void initComponents(){
        title = createLabel("Criar Eventos", UIStyle.USER_ADMIN_TITLE_FONT, UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);

        eventNameLabel = createLabel("Nome do evento:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        eventNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        eventNameField = createCustomTextField("Nome do evento...", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        eventNamePanel = eventNameField.withIcon("event.png", 14, BorderLayout.WEST);

        List<String> odsTopics = getArrayOdsTopics(odsList);
        odsTopics.add(0, "Selecione uma ODS");
        odsLabel = createLabel("Selecione a ODS relacionada ao evento:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        odsLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        odsComboBox = createCustomComboBox(odsTopics.toArray(new String[0]), UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.BG_USER_ADMIN_COLOR, Color.black);
        odsComboBox.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, UIStyle.BG_SIDE_MENU_USER_COLOR));
        applyCustomScrollBarToComboBox(odsComboBox);
        odsPanel = createComboBoxWithIcon(odsComboBox, "sustainable.png", 14, BorderLayout.WEST);

        eventDateLabel = createLabel("Data do Evento:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        eventDateLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        eventDateField = createCustomFormattedDateTimeField("", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        eventDatePanel = eventDateField.withIcon("date.png", 14, BorderLayout.WEST);

        eventDescriptionLabel = createLabel("Descrição do evento:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        eventDescriptionField = createCustomTextArea("Descreva aqui os detalhes do evento...", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.BG_USER_ADMIN_COLOR, 5, 30);
        eventDescriptionScrollPane = new JScrollPane();
        eventDescriptionScrollPane.setViewportView(eventDescriptionField);
        eventDescriptionScrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI(UIStyle.BG_SIDE_MENU_USER_COLOR.brighter(), Color.lightGray));
        eventDescriptionScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(6, Integer.MAX_VALUE));

        cepLabel = createLabel("Informe o CEP do local do evento:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        cepField = createCustomFormattedCepField("", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        cepLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        searchCep = createImageLabel("searchBlue.png", "icons", SwingConstants.CENTER, 14, 14);
        searchCep.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addressLineLabel = createLabel("Endereço:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        addressLineLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        addressLineField = createCustomTextField("Endereço...", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        addressLinePanel = addressLineField.withIcon("address.png", 14, BorderLayout.WEST);

        cityLabel = createLabel("Cidade:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        cityLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        cityField = createCustomTextField("Cidade...", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        cityPanel = cityField.withIcon("city.png", 14, BorderLayout.WEST);

        stateLabel = createLabel("Estado:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        stateLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        stateField = createCustomTextField("Estado...", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        statePanel = stateField.withIcon("state.png", 14, BorderLayout.WEST);

        btnCreate = createRoundedButton("Criar Evento", UIStyle.USER_CONTENT_BTN_FONT, UIStyle.BG_SIDE_MENU_USER_COLOR, Color.WHITE, 120, 120);

    }

    @Override
    public void layoutComponents(){
        JPanel panelTitle = new JPanel();
        panelTitle.setPreferredSize(new Dimension(1100, 150));

        GroupLayout panelTitleLayout = new GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
                panelTitleLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelTitleLayout.createSequentialGroup()
                                .addGap(55)
                                .addComponent(title, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(1071, Short.MAX_VALUE))
        );
        panelTitleLayout.setVerticalGroup(
                panelTitleLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                                .addGap(75)
                                .addComponent(title, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        panelTitle.setBackground(UIStyle.BG_USER_ADMIN_COLOR);

        JPanel panelButton = new JPanel();
        panelButton.setPreferredSize(new java.awt.Dimension(1100, 130));
        panelButton.setBackground(UIStyle.BG_USER_ADMIN_COLOR);

        GroupLayout panelButtonLayout = new GroupLayout(panelButton);
        panelButton.setLayout(panelButtonLayout);
        panelButtonLayout.setHorizontalGroup(
                panelButtonLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelButtonLayout.createSequentialGroup()
                                .addGap(350)
                                .addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE))
        );
        panelButtonLayout.setVerticalGroup(
                panelButtonLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelButtonLayout.createSequentialGroup()
                                .addGap(20)
                                .addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                .addGap(30))
        );

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(UIStyle.BG_USER_ADMIN_COLOR);
        GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
                contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                .addGap(215)
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                            .addComponent(eventDescriptionScrollPane))
                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(eventDescriptionLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(eventNameLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(eventNamePanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(odsLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(odsPanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(eventDateLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(eventDatePanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                                                        .addGap(36, 36, 36)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(stateLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(statePanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(cityLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(cityPanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(addressLineLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(addressLinePanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(cepLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                                            .addGap(25)
                                                                            .addComponent(cepField, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(searchCep, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))))))
                                .addGap(279, 279, 279))
        );
        contentPanelLayout.setVerticalGroup(
                contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(contentPanelLayout.createSequentialGroup()
                                .addGap(10)
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                                .addComponent(cepLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(cepField)
                                                                        .addComponent(searchCep, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(37)
                                                                .addComponent(addressLineLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(addressLinePanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                                        .addComponent(eventNameLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(eventNamePanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(94, 94, 94))
                                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                                        .addComponent(odsLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(odsPanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))
                                                .addGap(18, 18, 18)
                                                .addComponent(cityLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cityPanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                .addComponent(eventDateLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(eventDatePanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(stateLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statePanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(eventDescriptionLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eventDescriptionScrollPane, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(panelTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panelButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(20)
                                .addComponent(panelButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
    }

    private void setOdsTopics(){
        Response<List<ODS>> response = odsController.getOdsTopics();
        if (response.isSuccess()){
            this.odsList = response.getData();
        }else {
            this.odsList = new ArrayList<ODS>();
        }
    }

    @Override public void addListeners(){
        searchCep.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String cep = cepField.getText().replaceAll("[^0-9]", "");
                if (cep.length() == 8) {
                    applySearchCep(cep);
                } else {
                    JOptionPane.showMessageDialog(null, "Digite um CEP válido com 8 dígitos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnCreate.addActionListener(e -> btnCreateClick());
    }

    private void btnCreateClick() {
        try {
            CreateEventRequestDto request = getCreateEventRequestDto();
            Response<Void> response = eventRequestController.createEventRequest(request);
            if (response.isSuccess()) {
                showSuccess(response.getMessage());
                clearFields();
            } else {
                showError(response.getMessage());
                clearFields();
            }
        } catch (DateTimeParseException e) {
            showError("Data para o evento inválida.");
            eventDateField.setText("");
        }
    }

    private void showSuccess(String message){
        JOptionPane.showMessageDialog(null, message, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void clearFields(){
        cepField.setText("");
        stateField.setText("");
        addressLineField.setText("");
        cityField.setText("");
        eventDescriptionField.setText("");
        eventNameField.setText("");
        odsComboBox.setSelectedIndex(0);
        eventDateField.setText("");
    }

    private CreateEventRequestDto getCreateEventRequestDto(){
        CreateEventRequestDto createEventRequestDto = new CreateEventRequestDto();
        createEventRequestDto.setUserId(user.getUserId());
        createEventRequestDto.setPostalCode(cepField.getText());
        createEventRequestDto.setState(stateField.getText());
        createEventRequestDto.setAddressLine(addressLineField.getText());
        createEventRequestDto.setCity(cityField.getText());
        String eventDate = eventDateField.getText().equals("__/__/____ __:__") ? null : eventDateField.getText();
        createEventRequestDto.setEventDate(parseToLocalDate(eventDate, "dd/MM/yyyy HH:mm"));
        createEventRequestDto.setEventDescription(eventDescriptionField.getText());
        createEventRequestDto.setEventName(eventNameField.getText());
        String selectedOds = (String) odsComboBox.getSelectedItem();
        UUID odsId = getOdsIdByName(odsList, selectedOds);
        createEventRequestDto.setOdsId(odsId);
        return createEventRequestDto;
    }

    private void applySearchCep(String cep){
        try {
            AddressDto dto = cepService.searchAddressByCep(cep);
            addressLineField.setText(dto.getBairro() + " - " + dto.getLogradouro());
            cityField.setText(dto.getLocalidade());
            stateField.setText(dto.getUf());
        }catch (NotFoundException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            cepField.setText("");
            addressLineField.setText("");
            cityField.setText("");
            stateField.setText("");
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro interno na consulta do cep.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


}
