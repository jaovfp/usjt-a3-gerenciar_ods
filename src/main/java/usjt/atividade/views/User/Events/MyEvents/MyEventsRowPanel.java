package usjt.atividade.views.User.Events.MyEvents;

import usjt.atividade.app.Events.DTO.MyEventsRequest;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.RoundedButton;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;
import java.awt.*;

import static usjt.atividade.views.utils.ComponentFactory.createLabel;
import static usjt.atividade.views.utils.ComponentFactory.createRoundedButton;

public class MyEventsRowPanel extends AbstractPanel {

    private final MyEventsRequest event;

    private JLabel lblNome, lblDescricao, lblOds, lblStatus, lblData;
    private RoundedButton btnVerDetalhes;

    public MyEventsRowPanel(MyEventsRequest event) {
        super(Color.WHITE, new Dimension(970, 50));
        this.event = event;
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    protected void initComponents() {
        lblNome = createLabel(event.getEventName(), new Font("Segoe UI", Font.BOLD, 12), UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);
        lblStatus = createLabel(event.getStatus(), new Font("Segoe UI", Font.PLAIN, 11), Color.GRAY, SwingConstants.LEFT);
        lblOds = createLabel(event.getEventName(), new Font("Segoe UI", Font.PLAIN, 11), UIStyle.BG_SIDE_MENU_USER_COLOR, SwingConstants.LEFT);
        lblData = createLabel(event.getStatus(), new Font("Segoe UI", Font.PLAIN, 11), Color.GRAY, SwingConstants.LEFT);
        btnVerDetalhes = createRoundedButton("Ver Detalhes", new Font("Segoe UI", Font.PLAIN, 10), UIStyle.BG_SIDE_MENU_USER_COLOR, UIStyle.SIDE_MENU_TEXT_COLOR);
    }

    @Override
    protected void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.weightx = 0;
        lblNome.setPreferredSize(new Dimension(200, lblNome.getPreferredSize().height));
        add(lblNome, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0;
        JTextArea descricaoArea = new JTextArea(event.getEventDescription());
        descricaoArea.setLineWrap(true);
        descricaoArea.setWrapStyleWord(true);
        descricaoArea.setEditable(false);
        descricaoArea.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        descricaoArea.setBackground(getBackground());
        descricaoArea.setBorder(null);
        descricaoArea.setPreferredSize(new Dimension(150, getTextAreaHeight(descricaoArea, 150)));
        add(descricaoArea, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        lblOds.setPreferredSize(new Dimension(100, lblOds.getPreferredSize().height));
        add(lblOds, gbc);

        gbc.gridx = 3;
        lblStatus.setPreferredSize(new Dimension(100, lblStatus.getPreferredSize().height));
        add(lblStatus, gbc);

        gbc.gridx = 4;
        lblData.setPreferredSize(new Dimension(100, lblData.getPreferredSize().height));
        add(lblData, gbc);

        gbc.gridx = 5;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        add(btnVerDetalhes, gbc);

        int maxHeight = Math.max(50, descricaoArea.getPreferredSize().height + 10);
        setPreferredSize(new Dimension(970, maxHeight));
        revalidate();
    }

    private int getTextAreaHeight(JTextArea area, int width) {
        FontMetrics fm = area.getFontMetrics(area.getFont());
        int lines = (int) Math.ceil((double) area.getText().length() * fm.charWidth('a') / width);
        int height = lines * fm.getHeight();
        return Math.max(height, fm.getHeight());
    }

    @Override
    protected void addListeners() {
        //btnVerDetalhes.addActionListener(e -> showDetailsDialog());
    }
}
