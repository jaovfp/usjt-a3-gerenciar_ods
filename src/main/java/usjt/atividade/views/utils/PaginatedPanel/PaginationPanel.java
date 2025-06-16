package usjt.atividade.views.utils.PaginatedPanel;

import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.UIStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

import static usjt.atividade.views.utils.ComponentFactory.createCustomComboBox;

public class PaginationPanel extends AbstractPanel {
    private JComboBox<Integer> itemsPerPageCombo;
    private JLabel infoLabel;
    private JComboBox<Integer> pageCombo;
    private JButton prevButton;
    private JButton nextButton;

    private int totalItems;
    private int currentPage;
    private int itemsPerPage;

    private final ActionListener onChange;
    private final Color backGroundColor;
    private final Color textColor;
    private final Color comboColor;
    private boolean suppressPageComboListener = false;

    public PaginationPanel(int totalItems, int currentPage, int itemsPerPage, ActionListener onChange, Dimension dimension, Color backGroundColor, Color textColor, Color comboColor) {
        super(backGroundColor, dimension);
        this.totalItems = totalItems;
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
        this.onChange = onChange;
        this.backGroundColor = backGroundColor;
        this.textColor = textColor;
        this.comboColor = comboColor;

        initComponents();
        layoutComponents();
        addListeners();
        updateInfo();
    }

    @Override
    protected void initComponents() {
        itemsPerPageCombo = createCustomComboBox(
                new Integer[]{5, 10, 25, 50, 100},
                comboColor,
                backGroundColor,
                textColor
        );
        itemsPerPageCombo.setSelectedItem(itemsPerPage);

        infoLabel = new JLabel();

        pageCombo = createCustomComboBox(
                new Integer[]{},
                comboColor,
                backGroundColor,
                textColor
        );

        prevButton = new JButton("⟨");
        prevButton.setForeground(comboColor);
        nextButton = new JButton("⟩");
        nextButton.setForeground(comboColor);
    }

    @Override
    protected void layoutComponents() {
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        contentPanel.setOpaque(false);

        contentPanel.add(new JLabel("Exibir"));
        contentPanel.add(itemsPerPageCombo);
        contentPanel.add(new JLabel("|"));
        contentPanel.add(infoLabel);
        contentPanel.add(Box.createHorizontalStrut(20));
        contentPanel.add(new JLabel("Página"));
        contentPanel.add(pageCombo);
        contentPanel.add(new JLabel("|"));
        contentPanel.add(prevButton);
        contentPanel.add(nextButton);

        add(contentPanel, BorderLayout.CENTER);
    }

    @Override
    protected void addListeners() {
        itemsPerPageCombo.addActionListener(e -> {
            this.itemsPerPage = (Integer) itemsPerPageCombo.getSelectedItem();
            this.currentPage = 1;
            onChange.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "change"));
        });

        pageCombo.addActionListener(e -> {
            if (suppressPageComboListener) return;

            Integer selected = (Integer) pageCombo.getSelectedItem();
            if (selected != null && selected != currentPage) {
                currentPage = selected;
                onChange.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "change"));
            }
        });

        prevButton.addActionListener(e -> {
            if (currentPage > 1) {
                currentPage--;
                onChange.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "change"));
            }
        });

        nextButton.addActionListener(e -> {
            if (currentPage < getTotalPages()) {
                currentPage++;
                onChange.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "change"));
            }
        });
    }

    private void updateInfo() {
        int start = (currentPage - 1) * itemsPerPage + 1;
        int end = Math.min(totalItems, currentPage * itemsPerPage);
        infoLabel.setText(MessageFormat.format("{0}-{1} de {2} itens", start, end, totalItems));

        suppressPageComboListener = true;
        pageCombo.removeAllItems();
        for (int i = 1; i <= getTotalPages(); i++) {
            pageCombo.addItem(i);
        }
        pageCombo.setSelectedItem(currentPage);
        suppressPageComboListener = false;

        prevButton.setEnabled(currentPage > 1);
        nextButton.setEnabled(currentPage < getTotalPages());
    }

    private int getTotalPages() {
        return (int) Math.ceil((double) totalItems / itemsPerPage);
    }

    public void refresh(int totalItems, int currentPage) {
        this.totalItems = totalItems;
        this.currentPage = currentPage;
        updateInfo();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }
}
