package cz.Meko.Windows;

import cz.Meko.Data.Data;

import javax.swing.*;
import java.awt.*;

public class TelemetrySettingsWindow {
    private JFrame frame;

    public TelemetrySettingsWindow() {
        frame = new JFrame("Telemetry settings window");
    }

    public void init() {
        this.frame.setSize(600, 600);
        this.frame.setLocationRelativeTo(null);

        this.frame.setLayout(new BorderLayout(10, 10));

        JPanel listsPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        // Add a nice border around the lists panel so it doesn't touch the edges
        listsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Sample data arrays
        String[] varNames = Data.VARIABLE_NAMES;
        JCheckBox[] checkBoxes = new JCheckBox[varNames.length];

        for (int i = 0; i < varNames.length; i++) {
            JCheckBox checkBox = new JCheckBox(varNames[i]);
            checkBox.addActionListener(e -> {
                System.out.println(" is now: " + checkBox.isSelected());
            });
            checkBoxes[i] = checkBox;
        }

        // Initialize the lists
        JList<String> namesList = new JList<>(varNames);
        JList<JCheckBox> checkBoxList = new JList<>(checkBoxes);

        // Wrap the lists in JScrollPanes so they become scrollable if data gets too long
        JScrollPane namesScrollPane = new JScrollPane(namesList);
        JScrollPane checkBoxScrollPane = new JScrollPane(checkBoxList);

        // Optional: Add titles to the top of the scroll panes
        namesScrollPane.setBorder(BorderFactory.createTitledBorder("Variables"));
        checkBoxScrollPane.setBorder(BorderFactory.createTitledBorder("Visibility"));

        // Add the scroll panes to our GridLayout panel
        listsPanel.add(namesScrollPane);
        listsPanel.add(checkBoxScrollPane);


        // 4. Assemble everything into the main frame
        this.frame.add(listsPanel, BorderLayout.CENTER); // Middle


        this.frame.setVisible(true);
    }
}
