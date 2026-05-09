package cz.Meko.Windows;

import cz.Meko.Data.Data;
import cz.Meko.Data.Status;
import cz.Meko.Data.UpdateTelemetry;

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

        JPanel listsPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        listsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] varNames = Data.VARIABLE_NAMES;
        JCheckBox[] checkBoxes = new JCheckBox[varNames.length];

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        checkBoxPanel.setBackground(Status.getMiddle());

        for (int i = 0; i < varNames.length; i++) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setBackground(Status.getMiddle()); // Match panel background

            final int index = i;

            checkBox.addActionListener(e -> {
                System.out.println(varNames[index] + " is now: " + checkBox.isSelected());
            });

            checkBoxes[i] = checkBox;

            checkBox.setSelected(true);

            checkBoxPanel.add(checkBox);
        }

        JList<String> namesList = new JList<>(varNames);
        namesList.setBackground(Status.getMiddle());

        JScrollPane namesScrollPane = new JScrollPane(namesList);
        JScrollPane checkBoxScrollPane = new JScrollPane(checkBoxPanel);

        BoundedRangeModel model = namesScrollPane.getVerticalScrollBar().getModel();
        checkBoxScrollPane.getVerticalScrollBar().setModel(model);

        namesScrollPane.setBorder(BorderFactory.createTitledBorder("Variables"));
        checkBoxScrollPane.setBorder(BorderFactory.createTitledBorder("Visibility"));

        namesScrollPane.setBackground(Status.getForegroundColor());
        checkBoxScrollPane.setBackground(Status.getForegroundColor());

        listsPanel.add(namesScrollPane);
        listsPanel.add(checkBoxScrollPane);

        listsPanel.setBackground(Status.getBackGround());

        this.frame.add(listsPanel, BorderLayout.CENTER); // Middle
        this.frame.setVisible(true);
    }
}
