package usjt.atividade.views;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractView extends JFrame {

    public AbstractView(String title, int width, int height) {
        setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
    }

    public void render() {
        setLocationRelativeTo(null);
    }

    protected abstract void initPanels();
    protected abstract void layoutPanels();
}
