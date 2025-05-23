package usjt.atividade.views.authentication;

import usjt.atividade.views.AbstractView;
import usjt.atividade.views.authentication.Welcolme.WelcomePanel;
import usjt.atividade.views.authentication.utils.SplitBGPanel;
import usjt.atividade.views.authentication.utils.UIStyle;

import javax.swing.*;
import java.awt.*;

public abstract class AuthView extends AbstractView {

    protected JPanel leftPanel;
    protected JPanel rightPanel;
    private final String welcomeTitle;
    private final String welcomeImage;

    public AuthView(String screenTitle, String welcomeTitle, String welcomeImage) {
        super(screenTitle, 1000, 700);
        this.welcomeTitle = welcomeTitle;
        this.welcomeImage = welcomeImage;
        initPanels();
        layoutPanels();
    }

    @Override
    protected void initPanels(){
        leftPanel = new WelcomePanel(this.welcomeTitle, this.welcomeImage);
        rightPanel = new JPanel();
    }

    @Override
    protected void layoutPanels(){
        SplitBGPanel backgroundPanel = new SplitBGPanel(
                Color.WHITE,
                UIStyle.BG_AUTH_COLOR
        );
        backgroundPanel.setLayout(new GridLayout(1, 2));

        leftPanel.setOpaque(false);
        rightPanel.setOpaque(false);

        backgroundPanel.add(leftPanel);
        backgroundPanel.add(rightPanel);

        setContentPane(backgroundPanel);
    }
}
