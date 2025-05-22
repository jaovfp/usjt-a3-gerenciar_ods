package usjt.atividade.views.authentication.Welcolme;

import javax.swing.*;
import java.awt.*;

import usjt.atividade.views.AbstractPanel;
import static usjt.atividade.views.authentication.utils.ComponentFactory.*;
import usjt.atividade.views.authentication.utils.UIStyle;

public class WelcomePanel extends AbstractPanel {
    private JLabel welcomeLabel;
    private JLabel imageLabel;

    public WelcomePanel(String text, String nameImage) {
        super(Color.WHITE, UIStyle.AUTH_DIMENSION);
        initComponents(text,nameImage);
        layoutComponents();
        addListeners();
    }

    @Override
    protected void initComponents() {
    }

    protected void initComponents(String welcomeText, String nameImage){
        welcomeLabel = createLabel(welcomeText, UIStyle.AUTH_TITLE_FONT, UIStyle.BG_AUTH_COLOR, SwingConstants.CENTER);
        imageLabel = createImageLabel(nameImage, SwingConstants.CENTER, 350, 350);
    }

    @Override
    protected void layoutComponents(){
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(65, 0, 0, 0));
        topPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        add(imageLabel, BorderLayout.CENTER);
    }

    @Override
    protected void addListeners(){

    }
}
