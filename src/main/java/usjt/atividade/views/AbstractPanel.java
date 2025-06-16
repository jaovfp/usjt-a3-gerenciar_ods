package usjt.atividade.views;

import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;

public abstract class AbstractPanel extends JPanel {
    public AbstractPanel(Color backgroundColor, Dimension preferredSize) {
        setBackground(backgroundColor);
        setPreferredSize(preferredSize);
        setOpaque(true);
        setLayout(new BorderLayout());
    }

    protected abstract void initComponents() ;
    protected abstract void layoutComponents();
    protected abstract void addListeners();
}
