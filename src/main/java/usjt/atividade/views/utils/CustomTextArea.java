package usjt.atividade.views.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CustomTextArea extends JTextArea {

    private Color borderColor;
    private String placeholder = "";
    private Color placeholderColor;
    private Color textColor;
    private Color backgroundColor;

    public CustomTextArea(int rows, int columns, Color backgroundColor) {
        super(rows, columns);
        this.backgroundColor = backgroundColor;
        initStyle();
    }

    private void initStyle() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(false); // continua desenhando manualmente
        setFont(new Font("Segoe UI", Font.PLAIN, 14));

        addFocusListener(new FocusAdapter() {
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
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    public String getPlaceholder() {
        return placeholder;
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
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor));
        repaint();
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Pinta o fundo manualmente
        g.setColor(backgroundColor != null ? backgroundColor : Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        super.paintComponent(g);

        if (getText().isEmpty() && !isFocusOwner()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(placeholderColor);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            Insets insets = getInsets();
            g2.drawString(placeholder, insets.left + 2, insets.top + getFont().getSize());
            g2.dispose();
        }

        g.setColor(borderColor);
        g.fillRect(0, getHeight() - 1, getWidth(), 1);
    }
}
