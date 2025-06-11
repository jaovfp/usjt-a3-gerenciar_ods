package usjt.atividade.views.User.UpdateRegistration;

import usjt.atividade.domain.entities.User;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.*;

import javax.swing.*;

import java.awt.*;

import static usjt.atividade.views.utils.ComponentFactory.*;

public class UpdateUserPanel extends AbstractPanel {

    private final User user;
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
    private JPanel cepPanel;
    private JPanel addressLinePanel;
    private JPanel cityPanel;
    private JPanel statePanel;
    private JLabel searchCep;
    private RoundedButton btnUpdate;
    JLabel profilePhoto;



    public UpdateUserPanel(User user) {
        super(UIStyle.BG_USER_ADMIN_COLOR, UIStyle.USER_ADMIN_CONTENT_DIMENSION);
        this.user = user;
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    public void initComponents(){
        title = createLabel("Atualizar Cadastro", UIStyle.USER_ADMIN_TITLE_FONT, UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);

        fullNameLabel = createLabel("Nome Completo:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        fullNameField = createCustomTextField("Nome Completo...", Color.black, UIStyle.BG_SIDE_MENU_USER_COLOR);
        fullNameField.setEnableEditOnIconClick(true);
        fullNamePanel = fullNameField.withIcon("edit.png", 14, BorderLayout.EAST);

        emailLabel = createLabel("Email:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        emailField = createCustomTextField("Email...", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        emailField.setEnableEditOnIconClick(true);
        emailPanel = emailField.withIcon("edit.png", 14, BorderLayout.EAST);

        birthDateLabel = createLabel("Data de Nascimento:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        birthDateField = createCustomFormattedDateField("Data de nascimento", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        birthDateField.setEnableEditOnIconClick(true);
        birthDatePanel = birthDateField.withIcon("edit.png", 14, BorderLayout.EAST);

        cpfLabel = createLabel("CPF:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        cpfField = createCustomFormattedCpfField("CPF...", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        cpfField.setEnableEditOnIconClick(true);
        cpfPanel = cpfField.withIcon("edit.png", 14, BorderLayout.EAST);

        phoneLabel = createLabel("Telefone:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        phoneField = createCustomFormattedPhoneField("Telefone...", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        phoneField.setEnableEditOnIconClick(true);
        phonePanel = phoneField.withIcon("edit.png", 14, BorderLayout.EAST);

        cepLabel = createLabel("CEP:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        cepField = createCustomFormattedCepField("CEP...", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        searchCep = createImageLabel("search.png", "icons", SwingConstants.CENTER, 14, 14);

        addressLineLabel = createLabel("Endereço:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        addressLineField = createCustomTextField("Endereço...", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        addressLinePanel = addressLineField.withIcon("edit.png", 14, BorderLayout.EAST);

        cityLabel = createLabel("Cidade:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        cityField = createCustomTextField("Cidade...", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        cityPanel = cityField.withIcon("edit.png", 14, BorderLayout.EAST);

        stateLabel = createLabel("Estado:", UIStyle.CONTENT_USER_ADMIN_TEXT_FONT, Color.GRAY, SwingConstants.LEFT);
        stateField = createCustomTextField("Estado...", Color.BLACK, UIStyle.BG_SIDE_MENU_USER_COLOR);
        statePanel = stateField.withIcon("edit.png", 14, BorderLayout.EAST);

        btnUpdate = createRoundedButton("Atualizar Cadastro", UIStyle.USER_CONTENT_BTN_FONT, UIStyle.BG_SIDE_MENU_USER_COLOR, Color.WHITE, 60, 60);
        profilePhoto = createRoundedImageLabel("default_profile.png", "userImages", SwingConstants.CENTER, 200, 200);
    }

    @Override
    public void layoutComponents(){
        JPanel topSpacer = new JPanel();
        topSpacer.setPreferredSize(new Dimension(1100, 50));
        topSpacer.setBackground(UIStyle.BG_USER_ADMIN_COLOR);

    }

    @Override
    public void addListeners(){
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
    }
}
