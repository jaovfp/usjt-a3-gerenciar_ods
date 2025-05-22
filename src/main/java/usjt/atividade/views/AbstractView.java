package usjt.atividade.views;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractView extends JFrame {

    public AbstractView(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
    }

    public void render() {
        setLocationRelativeTo(null);
    }
}
