/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usjt.atividade.views.authentication.Welcolme;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import usjt.atividade.views.authentication.utils.UIStyle;

/**
 *
 * @author Pichau
 */
public class WelcomePanel extends JPanel{   
    public WelcomePanel(String text, String nameImage) {
        setPreferredSize(new Dimension(500, 700));
        setLayout(new BorderLayout());

        setBackground(Color.WHITE);
        JLabel welcomeLabel = new JLabel(text, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(UIStyle.BG_AUTH_COLOR);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(65, 0, 0, 0));
        topPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40));


        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/"+nameImage)));
        Image scaledImage = originalIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(scaledIcon);

        add(imageLabel, BorderLayout.CENTER);

        Locale locale = new Locale("pt", "BR");
        SimpleDateFormat sdf = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", locale);
        String currentDate = sdf.format(new Date());

        JLabel dateLabel = new JLabel(currentDate, SwingConstants.CENTER);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        dateLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(dateLabel, BorderLayout.SOUTH);
    }
}
