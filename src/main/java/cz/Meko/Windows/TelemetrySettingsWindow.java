package cz.Meko.Windows;

import cz.Meko.Data.Data;
import cz.Meko.Data.Status;

import javax.swing.*;
import java.awt.*;

public class TelemetrySettingsWindow {
    private final JFrame frame;

    public TelemetrySettingsWindow() {
        frame = new JFrame("Telemetry settings window");
    }

    public void init() {
        this.frame.setSize(600, 600);
        this.frame.setLocationRelativeTo(null);

        this.frame.setLayout(new BorderLayout(10, 10));

        int rowHeight = 25; // Adjusted to match the row height of other windows

        // Added 10px horizontal gap so the rounded borders don't touch
        JPanel listsPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        listsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] varNames = Data.VARIABLE_NAMES;
        JCheckBox[] checkBoxes = new JCheckBox[varNames.length];

        // --- Checkbox Panel ---
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        // Matched the background color to the new light gray list color!
        checkBoxPanel.setBackground(new Color(179, 179, 179));

        for (int i = 0; i < varNames.length; i++) {
            JCheckBox checkBox = new JCheckBox();
            // Matched checkbox background to the light gray theme
            checkBox.setBackground(new Color(174, 174, 174));
            // Padded the checkbox slightly so it aligns with the padded text in the list
            checkBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

            checkBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, rowHeight));
            checkBox.setPreferredSize(new Dimension(checkBox.getPreferredSize().width, rowHeight));

            final int index = i;

            Status.setToIndex(index, true);

            checkBox.addActionListener(e -> {
                Status.setToIndex(index, checkBox.isSelected());
            });

            checkBox.setUI(new javax.swing.plaf.basic.BasicCheckBoxUI() {
                @Override
                public void paint(Graphics g, JComponent c) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    g2.setColor(c.getBackground());

                    super.paint(g2, c); // Paint the text over our custom background
                    g2.dispose();
                }
            });

            checkBoxes[i] = checkBox;
            checkBox.setSelected(true);
            checkBoxPanel.add(checkBox);
        }

        // --- List Panel ---
        JList<String> namesList = new JList<>(varNames);

        // 1. Just call configureList! Removed all the redundant BasicListUI overrides that were breaking it.
        Status.configureList(namesList, rowHeight);

        JScrollPane namesScrollPane = new JScrollPane(namesList);
        JScrollPane checkBoxScrollPane = new JScrollPane(checkBoxPanel);

        BoundedRangeModel model = namesScrollPane.getVerticalScrollBar().getModel();
        checkBoxScrollPane.getVerticalScrollBar().setModel(model);

        namesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // 2. Apply your base scrollbar UI configuration
        Status.configureJScrollPane(namesScrollPane);
        Status.configureJScrollPane(checkBoxScrollPane);

        // 3. Make transparent so rounded borders show correctly
        namesScrollPane.setOpaque(false);
        namesScrollPane.getViewport().setOpaque(false);
        checkBoxScrollPane.setOpaque(false);
        checkBoxScrollPane.getViewport().setOpaque(false);

        // 4. Apply the NEW custom rounded borders
        namesScrollPane.setBorder(Status.createRoundedTitledBorder("Variables"));
        checkBoxScrollPane.setBorder(Status.createRoundedTitledBorder("Visibility"));

        listsPanel.add(namesScrollPane);
        listsPanel.add(checkBoxScrollPane);

        listsPanel.setBackground(Status.getBackGround());

        this.frame.add(listsPanel, BorderLayout.CENTER); // Middle
        this.frame.setVisible(true);
    }
}