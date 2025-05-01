package usjt.atividade.views.authentication.utils;

import javax.swing.*;
import java.awt.*;

public class UIStyle {

    public static final Color LINK_COLOR = new Color(100, 150, 255);
    public static final Color BUTTON_COLOR = new Color(70, 130, 180);

    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 13);
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);
    public static final Font BUTTON_TITLE_FONT = new Font("Arial", Font.BOLD, 16);
    public static final Font LINK_FONT = new Font("Arial", Font.PLAIN, 14);

    public static JButton createRoundedButton(String text, Color bgColor, Color textColor, int width, int height) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getForeground());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 50, 50);
                g2.dispose();
            }
        };

        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setFont(BUTTON_TITLE_FONT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(width, height)); // aumenta o tamanho
        return button;
    }

    public static JLabel createLinkLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(LINK_COLOR);
        label.setFont(LINK_FONT);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return label;
    }

}
