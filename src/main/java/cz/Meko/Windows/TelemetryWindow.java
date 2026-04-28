package cz.Meko.Windows;

import cz.Meko.Data.Data;
import cz.Meko.Data.Telemetry;

import javax.swing.*;
import java.awt.*;

public class TelemetryWindow {
    private JFrame frame;

    public TelemetryWindow() {
        frame = new JFrame("Telemetry Window");
    }

    public void init() {
        this.frame.setSize(600, 600);
        this.frame.setLocationRelativeTo(null);

        // 1. Set the main layout to BorderLayout
        this.frame.setLayout(new BorderLayout(10, 10)); // 10px padding between regions

        // 2. Create the top panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton startBtn = new JButton("Settings");
        JButton stopBtn = new JButton("Stop");
        JButton startUBtn = new JButton("Start");

        buttonPanel.add(startBtn);
        buttonPanel.add(startUBtn);
        buttonPanel.add(stopBtn);

        // 3. Create the center panel for the side-by-side lists
        // GridLayout(rows, columns, horizontalGap, verticalGap)
        JPanel listsPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        // Add a nice border around the lists panel so it doesn't touch the edges
        listsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Sample data arrays
        String[] varNames = Data.VARIABLE_NAMES;
        Data data = new Data();
        String[] varValues = data.getCurrentValuesAsArray();

        // Initialize the lists
        JList<String> namesList = new JList<>(varNames);
        JList<String> valuesList = new JList<>(varValues);

        // Wrap the lists in JScrollPanes so they become scrollable if data gets too long
        JScrollPane namesScrollPane = new JScrollPane(namesList);
        JScrollPane valuesScrollPane = new JScrollPane(valuesList);

        // Optional: Add titles to the top of the scroll panes
        namesScrollPane.setBorder(BorderFactory.createTitledBorder("Variables"));
        valuesScrollPane.setBorder(BorderFactory.createTitledBorder("Values"));

        // Add the scroll panes to our GridLayout panel
        listsPanel.add(namesScrollPane);
        listsPanel.add(valuesScrollPane);

        // 4. Assemble everything into the main frame
        this.frame.add(buttonPanel, BorderLayout.NORTH); // Top
        this.frame.add(listsPanel, BorderLayout.CENTER); // Middle

        Timer timer = new Timer(500, e -> {
            Data freshData = Telemetry.getData();
            if (freshData != null) {
                // You must update the Model of the JList
                valuesList.setListData(freshData.getCurrentValuesAsArray());
            }
        });
        timer.start();

        // Finally, make the frame visible
        this.frame.setVisible(true);
    }

    // Main method to test it out
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelemetryWindow window = new TelemetryWindow();
            window.init();
        });
    }
}