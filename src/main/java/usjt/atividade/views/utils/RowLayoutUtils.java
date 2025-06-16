package usjt.atividade.views.utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static javax.swing.GroupLayout.PREFERRED_SIZE;

public class RowLayoutUtils {

    public static void applyRowLayout(JPanel panel, List<Component> components, List<Integer> widths, List<Integer> gaps) {
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(false);
        layout.setAutoCreateContainerGaps(false);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup().addGap(20);
        for (int i = 0; i < components.size(); i++) {
            hGroup.addComponent(components.get(i), PREFERRED_SIZE, widths.get(i), PREFERRED_SIZE);
            if (i < components.size() - 1) {
                hGroup.addGap(gaps.get(i));
            }
        }

        GroupLayout.ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        for (Component c : components) {
            vGroup.addComponent(c, PREFERRED_SIZE, 50, PREFERRED_SIZE);
        }

        layout.setHorizontalGroup(layout.createParallelGroup().addGroup(hGroup));
        layout.setVerticalGroup(layout.createSequentialGroup().addGroup(vGroup));
    }

}
