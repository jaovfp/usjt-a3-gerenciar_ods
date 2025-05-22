package usjt.atividade.views.authentication.utils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ComponentFactory {
    public static JLabel createLabel(String text, Font font, Color color, int alignment) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setHorizontalAlignment(alignment);
        return label;
    }

    public static RoundedButton createRoundedButton(String text, Font font, Color bgColor, Color textColor) {
        RoundedButton btn = new RoundedButton();
        btn.setText(text);
        btn.setFont(font);
        btn.setForeground(textColor);
        btn.setBackground(bgColor);
        btn.setBorderColor(bgColor);
        btn.setColorPressed(bgColor.darker());
        btn.setColorRollover(bgColor.brighter());
        return btn;
    }

    public static CustomTextField createCustomTextField(String placeholder, Color textColor, Color borderColor) {
        CustomTextField field = new CustomTextField();
        field.setPlaceholder(placeholder);
        field.setPlaceholderColor(UIStyle.PLACEHOLDER_COLOR);
        field.setTextColor(textColor);
        field.setBorderColor(borderColor);
        return field;
    }

    public static CustomPasswordField createCustomPasswordField(String placeholder, Color textColor, Color borderColor) {
        CustomPasswordField field = new CustomPasswordField();
        field.setPlaceholder(placeholder);
        field.setPlaceholderColor(UIStyle.PLACEHOLDER_COLOR);
        field.setTextColor(textColor);
        field.setBorderColor(borderColor);
        return field;
    }

    public static JLabel createLinkLabel(String text, Color textColor, Font font) {
        JLabel label = new JLabel("<html><u>" + text + "</u></html>");
        label.setForeground(textColor);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.setFont(font);
        return label;
    }


    public static JLabel createImageLabel(String imageName, int horizontalAlignment, int width, int height) {
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(horizontalAlignment);

        ImageIcon icon = new ImageIcon(
                Objects.requireNonNull(ComponentFactory.class.getResource("/images/" + imageName))
        );

        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        return imageLabel;
    }
}
