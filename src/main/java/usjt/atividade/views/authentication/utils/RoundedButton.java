package usjt.atividade.views.authentication.utils;

import javax.swing.JButton;
import java.awt.*;

public class RoundedButton extends JButton {

    private int arcWidth = 60;
    private int arcHeight = 60;
    
    public RoundedButton(){
        this("");
    }

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        if (getModel().isPressed()) {
            g2.setColor(new Color(0, 200, 200)); // Cor ao pressionar
        } else if (getModel().isRollover()) {
            g2.setColor(new Color(0, 180, 180)); // Cor ao passar o mouse
        } else {
            g2.setColor(getBackground()); // Cor normal
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        g2.dispose();
    }

    @Override
    public boolean isOpaque() {
        return false;
    }
    
}
