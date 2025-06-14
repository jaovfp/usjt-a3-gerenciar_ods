package usjt.atividade.views.utils;

import javax.swing.*;
import java.awt.*;

public class ArrowIcon implements Icon {

    private final Color color;

    public ArrowIcon(Color color) {
        this.color = color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        int width = getIconWidth();
        int height = getIconHeight();
        int[] xPoints = {x, x + width, x + width / 2};
        int[] yPoints = {y, y, y + height};

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.fillPolygon(xPoints, yPoints, 3);
        g2.dispose();
    }

    @Override
    public int getIconWidth() {
        return 10;
    }

    @Override
    public int getIconHeight() {
        return 6;
    }
}
