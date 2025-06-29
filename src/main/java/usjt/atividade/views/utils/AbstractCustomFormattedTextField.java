package usjt.atividade.views.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public abstract class AbstractCustomFormattedTextField extends JFormattedTextField {

    private Color borderColor = Color.BLACK;
    private String placeholder = "";
    private Color placeholderColor = Color.GRAY;
    private Color textColor = Color.BLACK;
    private boolean enableEditOnIconClick = false;

    public AbstractCustomFormattedTextField() {
        super();
        initStyle();
    }

    private void initStyle() {
        setOpaque(false);
        setForeground(textColor);
        setCaretColor(textColor);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor));

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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isFocusOwner() && shouldShowPlaceholder()) {
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

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    public void setPlaceholderColor(Color color) {
        this.placeholderColor = color;
        repaint();
    }

    public void setTextColor(Color color) {
        this.textColor = color;
        setForeground(color);
        setCaretColor(color);
        repaint();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, color));
        repaint();
    }

    public void setEnableEditOnIconClick(boolean enable) {
        this.enableEditOnIconClick = enable;
        if (!enable) {
            setEditable(false);
        }
    }

    public JPanel withIcon(String iconFileName, int iconSize, String borderLayoutIconPosit) {
        JLabel iconLabel = new JLabel();
        ImageIcon rawIcon = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/icons/" + iconFileName))
        );
        Image scaledImage = rawIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
        iconLabel.setIcon(new ImageIcon(scaledImage));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        iconLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        iconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (enableEditOnIconClick) {
                    setEditable(true);
                    SwingUtilities.invokeLater(() -> {requestFocusInWindow();grabFocus();});
                }
            }
        });

        if (enableEditOnIconClick) {
            addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    setEditable(false);
                }
            });
        }

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.add(iconLabel, borderLayoutIconPosit);
        panel.add(this, BorderLayout.CENTER);
        return panel;
    }

    protected abstract boolean shouldShowPlaceholder();
}
