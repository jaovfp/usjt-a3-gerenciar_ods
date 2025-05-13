package usjt.atividade.views.authentication;

import usjt.atividade.views.AbstractView;
import usjt.atividade.views.authentication.utils.SplitBGPanel;
import usjt.atividade.views.authentication.utils.UIStyle;

import javax.swing.*;
import java.awt.*;

public abstract class AuthView extends AbstractView {

    protected JPanel leftPanel;
    protected JPanel rightPanel;

    public AuthView(String title) {
        super(title);
        setSize(1000, 700);
        SplitBGPanel backgroundPanel = new SplitBGPanel(
                Color.WHITE,
                UIStyle.BG_AUTH_COLOR
        );
        backgroundPanel.setLayout(new GridLayout(1, 2));

        leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        rightPanel = new JPanel();
        rightPanel.setOpaque(false);

        backgroundPanel.add(leftPanel);
        backgroundPanel.add(rightPanel);

        setContentPane(backgroundPanel);
    }
}
