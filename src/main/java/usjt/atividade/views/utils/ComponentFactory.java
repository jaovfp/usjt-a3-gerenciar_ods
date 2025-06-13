package usjt.atividade.views.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Objects;

public class ComponentFactory {
    public static JLabel createLabel(String text, Font font, Color color, int alignment) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setHorizontalAlignment(alignment);
        return label;
    }

    public static RoundedButton createRoundedButton(String text, Font font, Color bgColor, Color textColor, int arcWidth, int arcHeight) {
        RoundedButton btn = new RoundedButton(60, 60);
        btn.setText(text);
        btn.setFont(font);
        btn.setForeground(textColor);
        btn.setBackground(bgColor);
        btn.setBorderColor(bgColor);
        btn.setColorPressed(bgColor.darker());
        btn.setColorRollover(bgColor.brighter());
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public static RoundedButton createRoundedButtonWithIcon(String text, Font font, Color bgColor, Color colorPressed, Color colorRollover, Color textColor, String iconFileName) {
        RoundedButton btn = new RoundedButton(60, 60);
        btn.setText(text);
        btn.setFont(font);
        btn.setForeground(textColor);
        btn.setBackground(bgColor);
        btn.setBorderColor(bgColor);
        btn.setColorPressed(colorPressed);
        btn.setColorRollover(colorRollover);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ImageIcon rawIcon = new ImageIcon(
                Objects.requireNonNull(ComponentFactory.class.getResource("/icons/" + iconFileName))
        );
        Image scaledImage = rawIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        btn.setIcon(scaledIcon);

        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn.setIconTextGap(10);
        btn.setMargin(new Insets(0, 20, 0, 20));

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

    public static CustomFormattedCpfField createCustomFormattedCpfField(String placeholder, Color textColor, Color borderColor) {
        CustomFormattedCpfField field = new CustomFormattedCpfField();
        field.setPlaceholder(placeholder);
        field.setPlaceholderColor(UIStyle.PLACEHOLDER_COLOR);
        field.setTextColor(textColor);
        field.setBorderColor(borderColor);
        return field;
    }

    public static CustomFormattedCepField createCustomFormattedCepField(String placeholder, Color textColor, Color borderColor) {
        CustomFormattedCepField field = new CustomFormattedCepField();
        field.setPlaceholder(placeholder);
        field.setPlaceholderColor(UIStyle.PLACEHOLDER_COLOR);
        field.setTextColor(textColor);
        field.setBorderColor(borderColor);
        return field;
    }

    public static CustomFormattedPhoneField createCustomFormattedPhoneField(String placeholder, Color textColor, Color borderColor) {
        CustomFormattedPhoneField field = new CustomFormattedPhoneField();
        field.setPlaceholder(placeholder);
        field.setPlaceholderColor(UIStyle.PLACEHOLDER_COLOR);
        field.setTextColor(textColor);
        field.setBorderColor(borderColor);
        return field;
    }

    public static CustomFormattedDateField createCustomFormattedDateField(String placeholder, Color textColor, Color borderColor) {
        CustomFormattedDateField field = new CustomFormattedDateField();
        field.setPlaceholder(placeholder);
        field.setPlaceholderColor(UIStyle.PLACEHOLDER_COLOR);
        field.setTextColor(textColor);
        field.setBorderColor(borderColor);
        return field;
    }

    public static CustomFormattedDateTimeField createCustomFormattedDateTimeField(String placeholder, Color textColor, Color borderColor) {
        CustomFormattedDateTimeField field = new CustomFormattedDateTimeField();
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


    public static JLabel createImageLabel(String imageName, String typeImage, int horizontalAlignment, int width, int height) {
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(horizontalAlignment);

        ImageIcon icon = new ImageIcon(
                Objects.requireNonNull(ComponentFactory.class.getResource("/" + typeImage + "/" + imageName))
        );

        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        return imageLabel;
    }

    public static JLabel createRoundedImageLabel(String imageName, String typeImage, int horizontalAlignment, int width, int height){
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(horizontalAlignment);
        try {
            URL imageUrl = Objects.requireNonNull(ComponentFactory.class.getResource("/" + typeImage + "/" + imageName));

            BufferedImage originalImage = ImageIO.read(imageUrl);
            Image scaled = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            BufferedImage circleBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = circleBuffer.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setClip(new Ellipse2D.Float(0, 0, width, height));
            g2.drawImage(scaled, 0, 0, null);
            g2.dispose();

            imageLabel.setIcon(new ImageIcon(circleBuffer));
        } catch (Exception e) {
            e.printStackTrace();
            imageLabel.setText("Erro");
        }
        return imageLabel;
    }

    public static JLabel createBtnLabelTextAndIcon(String text, Font textFont, String iconFileName) {
        ImageIcon rawIcon = new ImageIcon(
                Objects.requireNonNull(ComponentFactory.class.getResource("/icons/" + iconFileName))
        );
        Image scaledImage = rawIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel btnLabelTextAndIcon = new JLabel(text, scaledIcon, JLabel.LEFT);
        btnLabelTextAndIcon.setFont(textFont);
        btnLabelTextAndIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnLabelTextAndIcon.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnLabelTextAndIcon.setIconTextGap(10);
        btnLabelTextAndIcon.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        return btnLabelTextAndIcon;
    }

    public static JPanel createMenuPanel(int width, int height, Color lineColor, JButton[] buttons) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(lineColor);
                g.fillRect(0, getHeight() - 2, getWidth(), 2);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);

        JPanel buttonContainer = new JPanel(new GridLayout(1, buttons.length, 40, 0));
        buttonContainer.setOpaque(false);

        for (JButton button : buttons) {
            buttonContainer.add(button);
        }

        panel.add(buttonContainer);
        return panel;
    }

    public static JButton createMenuButton(String text, Color textColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(textColor);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.putClientProperty("selected", false);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!(Boolean.TRUE.equals(button.getClientProperty("selected")))) {
                    button.setForeground(hoverColor.brighter());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!(Boolean.TRUE.equals(button.getClientProperty("selected")))) {
                    button.setForeground(textColor);
                }
            }
        });

        return button;
    }

    public static <T> JComboBox<T> createCustomComboBox(T[] items, Color arrowColor, Color backgroundColor, Color textColor) {
        JComboBox<T> comboBox = new JComboBox<>(items);
        comboBox.setBackground(backgroundColor);
        comboBox.setForeground(textColor);
        comboBox.setBorder(BorderFactory.createEmptyBorder());
        comboBox.setFocusable(false);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        comboBox.setOpaque(false);

        comboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton(new ArrowIcon(arrowColor));
                button.setContentAreaFilled(false);
                button.setFocusPainted(false);
                button.setBorderPainted(false);
                button.setBorder(BorderFactory.createEmptyBorder());
                return button;
            }
        });

        return comboBox;
    }

    public static <T> JPanel createComboBoxWithIcon(
            JComboBox<T> comboBox,
            String iconFileName,
            int iconSize,
            String iconPosition
    ) {

        JLabel iconLabel = new JLabel();
        ImageIcon rawIcon = new ImageIcon(
                Objects.requireNonNull(CustomTextField.class.getResource("/icons/" + iconFileName))
        );
        Image scaledImage = rawIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
        iconLabel.setIcon(new ImageIcon(scaledImage));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        iconLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.add(comboBox, BorderLayout.CENTER);
        panel.add(iconLabel, iconPosition);

        return panel;
    }

    public static CustomTextArea createCustomTextArea(String placeholder, Color textColor, Color borderColor, Color bgColor, int rows, int columns) {
        CustomTextArea area = new CustomTextArea(rows, columns, bgColor);
        area.setPlaceholder(placeholder);
        area.setPlaceholderColor(UIStyle.PLACEHOLDER_COLOR);
        area.setTextColor(textColor);
        area.setBorderColor(borderColor);
        area.setBackground(Color.WHITE);
        return area;
    }

    public static void applyCustomScrollBarToComboBox(JComboBox<?> comboBox) {
        comboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {
                Object comp = comboBox.getUI().getAccessibleChild(comboBox, 0);
                if (comp instanceof BasicComboPopup) {
                    BasicComboPopup popup = (BasicComboPopup) comp;
                    JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
                    JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
                    verticalBar.setUI(new ModernScrollBarUI(UIStyle.BG_SIDE_MENU_USER_COLOR.brighter(), Color.LIGHT_GRAY));
                    verticalBar.setPreferredSize(new Dimension(6, Integer.MAX_VALUE));
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {}

            @Override
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {}
        });
    }
}
