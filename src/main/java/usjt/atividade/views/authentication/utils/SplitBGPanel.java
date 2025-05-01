package usjt.atividade.views.authentication.utils;

import javax.swing.*;
import java.awt.*;

public class SplitBGPanel extends JPanel {

    private final Color leftColor;
    private final Color rightColor;

    public SplitBGPanel(Color leftColor, Color rightColor) {
        this.leftColor = leftColor;
        this.rightColor = rightColor;
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        g.setColor(leftColor);
        g.fillRect(0, 0, width / 2, height);

        g.setColor(rightColor);
        g.fillRect(width / 2, 0, width / 2, height);
    }

}
