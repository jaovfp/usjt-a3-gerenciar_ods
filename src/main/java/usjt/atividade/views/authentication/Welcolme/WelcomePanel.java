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
import usjt.atividade.views.authentication.utils.UIStyle;

/**
 *
 * @author Pichau
 */
public class WelcomePanel extends JPanel{   
    public WelcomePanel() {
        setPreferredSize(new Dimension(500, 700));
        setLayout(new BorderLayout());
        
        setBackground(UIStyle.BG_AUTH_COLOR);
        JLabel welcomeLabel = new JLabel("Seja bem-vindo", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(welcomeLabel, BorderLayout.NORTH);


        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);


        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/ui_ux_auth_image.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
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
