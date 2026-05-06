package cz.Meko.Windows;

import cz.Meko.Data.Data;
import cz.Meko.Data.Telemetry;
import cz.Meko.Data.UpdateTelemetry;

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

        this.frame.setLayout(new BorderLayout(10, 10));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton settings = new JButton("Settings");
        JButton stopBtn = new JButton("Stop");
        JButton startBtn = new JButton("Start");

        buttonPanel.add(settings);
        buttonPanel.add(startBtn);
        buttonPanel.add(stopBtn);

        buttonPanel.setBackground(new Color(60, 60, 60));

        settings.addActionListener(e -> {
        new TelemetrySettingsWindow().init();
        });

        stopBtn.addActionListener(e -> {
            Telemetry.setUpdatingTelemetry(false);
        });

        startBtn.addActionListener(e -> {
            UpdateTelemetry updateTelemetry = new UpdateTelemetry();
            updateTelemetry.start();
        });

        JPanel listsPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        listsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] varNames = Data.VARIABLE_NAMES;
        Data data = new Data();
        String[] varValues = data.getCurrentValuesAsArray();

        JList<String> namesList = new JList<>(varNames);
        JList<String> valuesList = new JList<>(varValues);

        namesList.setBackground(Color.LIGHT_GRAY);
        valuesList.setBackground(Color.LIGHT_GRAY);

        JScrollPane namesScrollPane = new JScrollPane(namesList);
        JScrollPane valuesScrollPane = new JScrollPane(valuesList);

        namesScrollPane.setBorder(BorderFactory.createTitledBorder("Variables"));
        valuesScrollPane.setBorder(BorderFactory.createTitledBorder("Values"));


        namesScrollPane.setBackground(new Color(128, 128, 128));
        valuesScrollPane.setBackground(new Color(128, 128, 128));


        listsPanel.add(namesScrollPane);
        listsPanel.add(valuesScrollPane);

        this.frame.add(buttonPanel, BorderLayout.NORTH);
        this.frame.add(listsPanel, BorderLayout.CENTER);

        listsPanel.setBackground(new Color(60, 60, 60));

        Timer timer = new Timer(500, e -> {
            Data freshData = Telemetry.getData();
            if (freshData != null) {
                valuesList.setListData(freshData.getCurrentValuesAsArray());
            }
        });
        timer.start();

        this.frame.setVisible(true);
    }

    public boolean isOpen() {
        return this.frame != null && this.frame.isVisible();
    }
}