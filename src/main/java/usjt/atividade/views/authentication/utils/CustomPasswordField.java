package usjt.atividade.views.authentication.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CustomPasswordField extends JPasswordField {
    private Color borderColor = Color.BLACK;

    public CustomPasswordField(int columns) {
        super(columns);
        setOpaque(false);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor));
            }
            @Override
            public void focusLost(FocusEvent e) {
                setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor));
            }
        });

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(borderColor);
        g.fillRect(0, getHeight() - 1, getWidth(), 1);
    }
}

