package usjt.atividade.views.authentication.utils;

import javax.swing.*;
import java.awt.*;

public class UIStyle {

    public static final Color BACKGROUND_COLOR = new Color(50, 50, 50);
    public static final Color TITLE_COLOR = Color.WHITE;
    public static final Color TEXT_COLOR = Color.LIGHT_GRAY;
    public static final Color LINK_COLOR = new Color(100, 150, 255);
    public static final Color BUTTON_COLOR = new Color(70, 130, 180);

    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 16);
    public static final Font LINK_FONT = new Font("Arial", Font.PLAIN, 14);

    public static JButton createRoundedButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFont(TEXT_FONT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(BUTTON_COLOR, 1, true));
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
