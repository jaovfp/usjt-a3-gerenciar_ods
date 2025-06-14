package usjt.atividade.views.utils;

import usjt.atividade.views.AbstractPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HeaderPanel extends AbstractPanel {

    private final List<JPanel> panels;
    private final List<Integer> widths;

    public HeaderPanel(List<JPanel> panels, Color bgColor, List<Integer> widths) {
        super(bgColor, new Dimension(970, 40));
        this.panels = panels;
        this.widths = widths;

        setPreferredSize(new Dimension(970, 40));
        setMaximumSize(new Dimension(970, 40));
        setMinimumSize(new Dimension(970, 40));

        initComponents();
        layoutComponents();
    }

    @Override
    protected void initComponents() {}

    @Override
    protected void layoutComponents() {
        List<Integer> gaps = List.of(20, 20, 20, 50);

        List<Component> containers = new java.util.ArrayList<>();
        for (int i = 0; i < panels.size(); i++) {
            JPanel originalPanel = panels.get(i);

            int columnWidth = widths.get(i);

            int wrapperWidth = 80;
            int wrapperHeight = 40;

            JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            wrapper.setOpaque(false);
            wrapper.setPreferredSize(new Dimension(wrapperWidth, wrapperHeight));
            wrapper.setMinimumSize(new Dimension(wrapperWidth, wrapperHeight));
            wrapper.setMaximumSize(new Dimension(wrapperWidth, wrapperHeight));
            wrapper.add(originalPanel);

            JPanel container = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            container.setOpaque(false);
            container.setPreferredSize(new Dimension(columnWidth, wrapperHeight));
            container.setMinimumSize(new Dimension(columnWidth, wrapperHeight));
            container.setMaximumSize(new Dimension(columnWidth, wrapperHeight));
            container.add(wrapper);

            containers.add(container);
        }

        RowLayoutUtils.applyRowLayout(this, containers, widths, gaps);
    }

    @Override
    public void addListeners() {}

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(970, 40);
    }

}
