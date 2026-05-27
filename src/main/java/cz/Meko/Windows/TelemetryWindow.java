package cz.Meko.Windows;

import cz.Meko.Data.Data;
import cz.Meko.Data.Status;
import cz.Meko.Data.UpdateTelemetry;

import javax.swing.*;
import java.awt.*;

public class TelemetryWindow {
    private final JFrame frame;

    public TelemetryWindow() {
        frame = new JFrame("Telemetry Window");
    }

    public void init() {
        this.frame.setSize(600, 600);
        this.frame.setLocationRelativeTo(null);

        this.frame.setLayout(new BorderLayout(10, 10));

        //Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 5));
        JButton settings = new JButton("Settings");
        JButton stopBtn = new JButton("Stop");
        JButton startBtn = new JButton("Start");

        Status.configureButton(settings, 20);
        Status.configureButton(stopBtn, 20);
        Status.configureButton(startBtn, 20);

        buttonPanel.add(settings);
        buttonPanel.add(startBtn);
        buttonPanel.add(stopBtn);

        buttonPanel.setBackground(Status.getBackGround());

        //Lists - Added 10px horizontal gap so the rounded borders don't overlap
        JPanel listsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        listsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] varNames = Data.VARIABLE_NAMES;
        Data data = new Data();
        String[] varValues = data.getCurrentValuesAsArray();

        int rowHeight = 25; // Defined a standard row height for the lists

        JList<String> namesList = new JList<>(varNames);
        JList<String> valuesList = new JList<>(varValues);

        // 1. Replaced the giant BasicListUI blocks with your new centralized method!
        Status.configureList(namesList, rowHeight);
        Status.configureList(valuesList, rowHeight);

        JScrollPane namesScrollPane = new JScrollPane(namesList);
        JScrollPane valuesScrollPane = new JScrollPane(valuesList);

        Timer timer = new Timer(500, e -> {
            Data freshData = Status.getData();
            if (freshData != null) {
                String[] allNames = Data.VARIABLE_NAMES;
                String[] allValues = freshData.getCurrentValuesAsArray();

                java.util.List<String> visibleNames = new java.util.ArrayList<>();
                java.util.List<String> visibleValues = new java.util.ArrayList<>();

                for (int i = 0; i < allNames.length; i++) {
                    if (Status.getFromIndex(i)) {
                        visibleNames.add(allNames[i]);
                        visibleValues.add(allValues[i]);
                    }
                }

                // Push the filtered arrays to the UI components
                namesList.setListData(visibleNames.toArray(new String[0]));
                valuesList.setListData(visibleValues.toArray(new String[0]));
            }
        });

        settings.addActionListener(e -> {
            new TelemetrySettingsWindow().init();
        });

        startBtn.addActionListener(e -> {
            UpdateTelemetry updateTelemetry = new UpdateTelemetry();
            updateTelemetry.start();
            timer.start();
        });

        stopBtn.addActionListener(e -> {
            Status.setUpdatingTelemetry(false);
            timer.stop();
        });

        BoundedRangeModel model = namesScrollPane.getVerticalScrollBar().getModel();
        valuesScrollPane.getVerticalScrollBar().setModel(model);

        namesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // 2. Apply base scrollbar UI configuration
        Status.configureJScrollPane(namesScrollPane);
        Status.configureJScrollPane(valuesScrollPane);

        // 3. Make transparent so rounded borders show correctly
        namesScrollPane.setOpaque(false);
        namesScrollPane.getViewport().setOpaque(false);
        valuesScrollPane.setOpaque(false);
        valuesScrollPane.getViewport().setOpaque(false);

        // 4. Apply custom rounded borders
        namesScrollPane.setBorder(Status.createRoundedTitledBorder("Variables"));
        valuesScrollPane.setBorder(Status.createRoundedTitledBorder("Values"));

        listsPanel.add(namesScrollPane);
        listsPanel.add(valuesScrollPane);

        this.frame.add(buttonPanel, BorderLayout.NORTH);
        this.frame.add(listsPanel, BorderLayout.CENTER);

        listsPanel.setBackground(Status.getBackGround());

        this.frame.getContentPane().setBackground(Status.getBackGround());
        this.frame.setVisible(true);
    }
}