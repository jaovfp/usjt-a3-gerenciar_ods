package usjt.atividade.views.utils;

import usjt.atividade.views.AbstractPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HeaderPanel extends AbstractPanel {

    private final List<JPanel> panels;
    private final List<Integer> widths;
    private final List<Integer> gaps;

    public HeaderPanel(List<JPanel> panels, Color bgColor, List<Integer> widths, List<Integer> gaps) {
        super(bgColor, new Dimension(970, 40));
        this.panels = panels;
        this.widths = widths;
        this.gaps = gaps;
        defineWidth();
        initComponents();
        layoutComponents();
    }

    @Override
    protected void initComponents() {}

    @Override
    protected void layoutComponents() {
        List<Component> containers = new java.util.ArrayList<>();
        for (int i = 0; i < panels.size(); i++) {
            JPanel originalPanel = panels.get(i);

            int columnWidth = widths.get(i);

            int wrapperWidth = columnWidth;
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

    private void defineWidth(){
        int totalWidth = 0;
        for (int i = 0; i < widths.size(); i++) {
            totalWidth += widths.get(i);
            if (i < gaps.size()) {
                totalWidth += gaps.get(i);
            }
        }
        this.setPreferredSize(new Dimension(totalWidth, 40));
    }

}
