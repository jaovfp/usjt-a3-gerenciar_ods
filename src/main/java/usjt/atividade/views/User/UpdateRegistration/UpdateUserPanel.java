package usjt.atividade.views.User.UpdateRegistration;

import usjt.atividade.app.Exceptions.NotFoundException;
import usjt.atividade.app.User.dto.requests.UpdateUserRequest;
import usjt.atividade.common.Response;
import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.valueObjects.Address;
import usjt.atividade.domain.valueObjects.CPF;
import usjt.atividade.domain.valueObjects.Email;
import usjt.atividade.domain.valueObjects.PhoneNumber;
import usjt.atividade.infra.Cep.Dto.AddressDto;
import usjt.atividade.infra.Cep.ViaCepService;
import usjt.atividade.infra.Photo.FileSystemPhotoStorageService;
import usjt.atividade.infra.controller.UserController;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.User.UserView;
import usjt.atividade.views.utils.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Optional;

import static usjt.atividade.common.utils.DateTimeUtils.dateConverter;
import static usjt.atividade.common.utils.DateTimeUtils.parseToLocalDate;
import static usjt.atividade.infra.config.FileSystemPhotoStorageConfig.getUserImagesFolderPath;
import static usjt.atividade.views.utils.ComponentFactory.*;
import static usjt.atividade.views.utils.ImageUtils.*;

public class UpdateUserPanel extends AbstractPanel {

    private final User user;
    private final UserView userView;
    private JLabel title;
    private JLabel fullNameLabel;
    private JLabel emailLabel;
    private JLabel birthDateLabel;
    private JLabel cpfLabel;
    private JLabel phoneLabel;
    private JLabel cepLabel;
    private JLabel addressLineLabel;
    private JLabel cityLabel;
    private JLabel stateLabel;
    private CustomTextField fullNameField;
    private CustomTextField emailField;
    private CustomFormattedDateField birthDateField;
    private CustomFormattedCpfField cpfField;
    private CustomFormattedPhoneField phoneField;
    private CustomFormattedCepField cepField;
    private CustomTextField addressLineField;
    private CustomTextField cityField;
    private CustomTextField stateField;
    private JPanel fullNamePanel;
    private JPanel emailPanel;
    private JPanel birthDatePanel;
    private JPanel cpfPanel;
    private JPanel phonePanel;
    private JPanel addressLinePanel;
    private JPanel cityPanel;
    private JPanel statePanel;
    private JLabel searchCep;
    private RoundedButton btnUpdate;
    private JLabel profilePhoto;
    private JLabel labelPhoto;
    private File selectedImageFile;
    private final UserController userController;
    private final FileSystemPhotoStorageService photoStorageService;
    private final ViaCepService cepService;

    public UpdateUserPanel(UserView user) {
        super(UIStyle.BG_USER_ADMIN_COLOR, UIStyle.USER_ADMIN_CONTENT_DIMENSION);
        this.user = user.getUser();
        this.userView = user;
        this.photoStorageService = new FileSystemPhotoStorageService(getUserImagesFolderPath());
        this.userController = new UserController();
        this.cepService = new ViaCepService();

        initComponents();
        layoutComponents();
        addListeners();
        fillFieldsFromUser();
    }

    @Override
    public void initComponents(){
        title = createLabel("Atualizar Cadastro", UIStyle.USER_ADMIN_TITLE_FONT, UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);

        fullNameLabel = createLabel("Nome Completo:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        fullNameField = createCustomTextField("Nome Completo...", Color.black, UIStyle.BG_SIDE_MENU_USER_COLOR);
        fullNameField.setEditable(false);
        fullNameField.setEnableEditOnIconClick(true);
        fullNamePanel = fullNameField.withIcon("edit.png", 14, BorderLayout.EAST);

        emailLabel = createLabel("Email:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        emailField = createCustomTextField("Email...", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        emailField.setEditable(false);
        emailField.setEnableEditOnIconClick(true);
        emailPanel = emailField.withIcon("edit.png", 14, BorderLayout.EAST);

        birthDateLabel = createLabel("Data de Nascimento:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        birthDateField = createCustomFormattedDateField("", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        birthDateField.setEditable(false);
        birthDateField.setEnableEditOnIconClick(true);
        birthDatePanel = birthDateField.withIcon("edit.png", 14, BorderLayout.EAST);

        cpfLabel = createLabel("CPF:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        cpfField = createCustomFormattedCpfField("", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        cpfField.setEditable(false);
        cpfField.setEnableEditOnIconClick(true);
        cpfPanel = cpfField.withIcon("edit.png", 14, BorderLayout.EAST);

        phoneLabel = createLabel("Telefone:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        phoneField = createCustomFormattedPhoneField("", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        phoneField.setEditable(false);
        phoneField.setEnableEditOnIconClick(true);
        phonePanel = phoneField.withIcon("edit.png", 14, BorderLayout.EAST);

        cepLabel = createLabel("CEP:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        cepField = createCustomFormattedCepField("", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        searchCep = createImageLabel("searchBlue.png", "icons", SwingConstants.CENTER, 14, 14);
        searchCep.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addressLineLabel = createLabel("Endereço:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        addressLineField = createCustomTextField("Endereço...", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        addressLineField.setEditable(false);
        addressLinePanel = addressLineField.withIcon("address.png", 14, BorderLayout.EAST);

        cityLabel = createLabel("Cidade:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        cityField = createCustomTextField("Cidade...", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        cityField.setEditable(false);
        cityPanel = cityField.withIcon("city.png", 14, BorderLayout.EAST);

        stateLabel = createLabel("Estado:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        stateField = createCustomTextField("Estado...", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        stateField.setEditable(false);
        statePanel = stateField.withIcon("state.png", 14, BorderLayout.EAST);

        btnUpdate = createRoundedButton("Atualizar Cadastro", UIStyle.USER_CONTENT_BTN_FONT, UIStyle.BG_SIDE_MENU_USER_COLOR, Color.WHITE, 120, 120);
        profilePhoto = createRoundedImageLabel(getDefaultProfileAbsolutePath(), SwingConstants.CENTER, 140, 140);
        profilePhoto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelPhoto = createLabel("Clique na foto para alterar a foto de perfil", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.CENTER);
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

        JPanel panelLabel = new JPanel();
        panelLabel.setPreferredSize(new Dimension(700, 470));
        panelLabel.setRequestFocusEnabled(false);
        panelLabel.setBackground(UIStyle.BG_USER_ADMIN_COLOR);

        GroupLayout panelLabelLayout = new GroupLayout(panelLabel);
        panelLabel.setLayout(panelLabelLayout);
        panelLabelLayout.setHorizontalGroup(
                panelLabelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, panelLabelLayout.createSequentialGroup()
                                .addGap(60)
                                .addGroup(panelLabelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(birthDateLabel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(birthDatePanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelLabelLayout.createSequentialGroup()
                                                .addGroup(panelLabelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(phoneLabel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(phonePanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                                                .addGap(77, 77, 77)
                                                .addGroup(panelLabelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(stateLabel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(statePanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(panelLabelLayout.createSequentialGroup()
                                                .addGroup(panelLabelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(cpfLabel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cpfPanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                                                .addGap(77, 77, 77)
                                                .addGroup(panelLabelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(cityLabel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cityPanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(panelLabelLayout.createSequentialGroup()
                                                .addGroup(panelLabelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(emailPanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                                                .addGap(77, 77, 77)
                                                .addGroup(panelLabelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(addressLineLabel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addressLinePanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(panelLabelLayout.createSequentialGroup()
                                                .addGroup(panelLabelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(fullNameLabel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(fullNamePanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                                                .addGap(77, 77, 77)
                                                .addGroup(panelLabelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(cepLabel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(panelLabelLayout.createSequentialGroup()
                                                                .addComponent(cepField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(searchCep, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))))
                                .addGap(157, 157, 157))
        );
        panelLabelLayout.setVerticalGroup(
                panelLabelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, panelLabelLayout.createSequentialGroup()
                                .addContainerGap(77, Short.MAX_VALUE)
                                .addGroup(panelLabelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelLabelLayout.createSequentialGroup()
                                                .addComponent(cepLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panelLabelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(cepField)
                                                        .addComponent(searchCep, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(panelLabelLayout.createSequentialGroup()
                                                .addComponent(fullNameLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(fullNamePanel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(panelLabelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelLabelLayout.createSequentialGroup()
                                                .addComponent(addressLineLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(addressLinePanel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelLabelLayout.createSequentialGroup()
                                                .addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(emailPanel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(panelLabelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelLabelLayout.createSequentialGroup()
                                                .addComponent(cityLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cityPanel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelLabelLayout.createSequentialGroup()
                                                .addComponent(cpfLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cpfPanel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(panelLabelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelLabelLayout.createSequentialGroup()
                                                .addComponent(stateLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(statePanel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelLabelLayout.createSequentialGroup()
                                                .addComponent(phoneLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(phonePanel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(birthDateLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(birthDatePanel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71))
        );

        JPanel panelImage = new JPanel();
        panelImage.setPreferredSize(new Dimension(330, 450));
        panelImage.setBackground(UIStyle.BG_USER_ADMIN_COLOR);

        GroupLayout panelImageLayout = new GroupLayout(panelImage);
        panelImage.setLayout(panelImageLayout);
        panelImageLayout.setHorizontalGroup(
                panelImageLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelImageLayout.createSequentialGroup()
                                .addGap(25)
                                .addComponent(profilePhoto, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(84, Short.MAX_VALUE))
                        .addGroup(panelImageLayout.createSequentialGroup()
                                .addComponent(labelPhoto, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                                .addContainerGap())
        );
        panelImageLayout.setVerticalGroup(
                panelImageLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelImageLayout.createSequentialGroup()
                                .addGap(100)
                                .addComponent(labelPhoto, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(profilePhoto, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
        );
        
        JPanel panelButton = new JPanel();
        panelButton.setPreferredSize(new java.awt.Dimension(1100, 130));
        panelButton.setBackground(UIStyle.BG_USER_ADMIN_COLOR);

        GroupLayout panelButtonLayout = new GroupLayout(panelButton);
        panelButton.setLayout(panelButtonLayout);
        panelButtonLayout.setHorizontalGroup(
                panelButtonLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelButtonLayout.createSequentialGroup()
                                .addGap(350)
                                .addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE))
        );
        panelButtonLayout.setVerticalGroup(
                panelButtonLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelButtonLayout.createSequentialGroup()
                                .addGap(20)
                                .addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                .addGap(30))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(panelTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(panelLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(panelImage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(panelButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(panelLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panelImage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
    }

    private void fillFieldsFromUser() {
        fullNameField.setText(Optional.ofNullable(user.getFullname()).orElse(""));
        emailField.setText(Optional.ofNullable(user.getEmail()).map(Email::getValue).orElse(""));

        birthDateField.setText(Optional.ofNullable(user.getBirthDate())
                .map(date -> dateConverter(date, "dd/MM/yyyy"))
                .orElse(""));

        cpfField.setText(Optional.ofNullable(user.getCpf()).map(CPF::getValue).orElse(""));
        phoneField.setText(Optional.ofNullable(user.getPhoneNumber()).map(PhoneNumber::getValue).orElse(""));

        cepField.setText(Optional.ofNullable(user.getAddress())
                .map(Address::getPostalCode)
                .orElse(""));

        addressLineField.setText(Optional.ofNullable(user.getAddress())
                .map(Address::getAddressLine)
                .orElse(""));

        cityField.setText(Optional.ofNullable(user.getAddress())
                .map(Address::getCity)
                .orElse(""));

        stateField.setText(Optional.ofNullable(user.getAddress())
                .map(Address::getState)
                .orElse(""));

        profilePhoto.setIcon(loadImage(user.getProfilePhotoUrl(), 140, 140));
    }

    @Override
    public void addListeners(){
        btnUpdate.addActionListener(e -> btnUpdateClick());
        profilePhoto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png"));
                int result = chooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                    selectedImageFile = selectedFile;
                    profilePhoto.setIcon(loadImage(selectedFile.getAbsolutePath(), 140, 140));
                }
            }
        });

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

    private void btnUpdateClick() {
        try {
            String photoUrl = uploadPhotoIfNeeded();
            UpdateUserRequest request = buildUpdateRequest(photoUrl);
            Response<User> response = userController.updateUser(request);

            if (response.isSuccess()) {
                handleSuccess(response.getData(), response.getMessage());
            } else {
                showError(response.getMessage());
            }

        } catch (DateTimeParseException e) {
            showError("Data de nascimento inválida.");
            birthDateField.setText("");
        } catch (Exception e) {
            showError("Erro inesperado ao atualizar usuário: " + e.getMessage());
        }
    }

    private String uploadPhotoIfNeeded() {
        if (Objects.nonNull(selectedImageFile)) {
            try {
                return photoStorageService.save(selectedImageFile);
            } catch (Exception e) {
                showError("Erro ao salvar a foto de perfil.");
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private UpdateUserRequest buildUpdateRequest(String photoUrl) {
        UpdateUserRequest request = getUpdateRequest();
        if (Objects.nonNull(photoUrl)) {
            request.setProfilePhotoUrl(photoUrl);
        }
        return request;
    }

    private void handleSuccess(User updatedUser, String message) {
        userView.refreshUser(updatedUser);
        fillFieldsFromUser();
        JOptionPane.showMessageDialog(null, message, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private UpdateUserRequest getUpdateRequest(){
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setUserId(user.getUserId().toString());
        updateUserRequest.setPostalCode(cepField.getText());
        updateUserRequest.setState(stateField.getText());
        updateUserRequest.setAddressLine(addressLineField.getText());
        updateUserRequest.setCity(cityField.getText());
        updateUserRequest.setEmail(emailField.getText());
        updateUserRequest.setFullname(fullNameField.getText());
        updateUserRequest.setPhoneNumber(phoneField.getText());
        String birthDate = birthDateField.getText().equals("__/__/____") ? null : birthDateField.getText();
        updateUserRequest.setBirthDate(parseToLocalDate(birthDate, "dd/MM/yyyy"));
        updateUserRequest.setCpf(cpfField.getText());
        updateUserRequest.setIsActive(Boolean.TRUE);
        return updateUserRequest;
    }
}
