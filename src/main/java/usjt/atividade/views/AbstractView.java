package usjt.atividade.views;

import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;

public abstract class AbstractView extends JFrame {

    public AbstractView(String screenTitle, int width, int height) {
        setTitle(screenTitle);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
    }

    public void render() {
        setLocationRelativeTo(null);
    }

    protected abstract void initPanels() ;
    protected abstract void layoutPanels();
}
