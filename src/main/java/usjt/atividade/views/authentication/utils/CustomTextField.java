package usjt.atividade.views.authentication.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CustomTextField extends JTextField {
    private Color borderColor;
    private String placeholder = "";
    private Color placeholderColor;
    private Color textColor;

    public CustomTextField() {
        this(20);
    }

    public CustomTextField(int columns) {
        super(columns);
        setOpaque(false);
        setForeground(textColor);
        setCaretColor(textColor);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor));
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor));
                repaint();
            }
        });

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor));
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty() && !isFocusOwner()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(placeholderColor);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            Insets insets = getInsets();
            g2.drawString(placeholder, insets.left + 2, getHeight() / 2 + getFont().getSize() / 2 - 4);
            g2.dispose();
        }

        g.setColor(borderColor);
        g.fillRect(0, getHeight() - 1, getWidth(), 1);
    }

    public void setPlaceholderColor(Color color) {
        this.placeholderColor = color;
        repaint();
    }

    public void setTextColor(Color color) {
        this.textColor = color;
        setForeground(textColor);
        setCaretColor(textColor);
        repaint();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }
}
