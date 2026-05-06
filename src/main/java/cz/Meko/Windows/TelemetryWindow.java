package cz.Meko.Windows;

import cz.Meko.Data.Data;
import cz.Meko.Data.Status;
import cz.Meko.Data.UpdateTelemetry;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

        settings.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        settings.setBackground(new Color(245, 245, 245)); // Soft Light Gray
        settings.setForeground(new Color(50, 50, 50));    // Dark Graphite Text
        settings.setFocusPainted(false);                 // Removes the ugly inner border
        settings.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1), // Thin border
                BorderFactory.createEmptyBorder(4, 10, 4, 10)                // Internal padding
        ));

        startBtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        startBtn.setBackground(new Color(245, 245, 245)); // Soft Light Gray
        startBtn.setForeground(new Color(50, 50, 50));    // Dark Graphite Text
        startBtn.setFocusPainted(false);                 // Removes the ugly inner border
        startBtn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1), // Thin border
                BorderFactory.createEmptyBorder(4, 10, 4, 10)                // Internal padding
        ));


        stopBtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        stopBtn.setBackground(new Color(245, 245, 245)); // Soft Light Gray
        stopBtn.setForeground(new Color(50, 50, 50));    // Dark Graphite Text
        stopBtn.setFocusPainted(false);                 // Removes the ugly inner border
        stopBtn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1), // Thin border
                BorderFactory.createEmptyBorder(4, 10, 4, 10)                // Internal padding
        ));


        // --- 2. Make it Interactive ---
        settings.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                settings.setBackground(new Color(230, 230, 230)); // Subtle hover
            }

            public void mouseExited(MouseEvent evt) {
                settings.setBackground(new Color(245, 245, 245)); // Return to normal
            }

            public void mousePressed(MouseEvent evt) {
                settings.setBackground(new Color(210, 210, 210)); // Click feedback
            }
        });

        buttonPanel.add(settings);
        buttonPanel.add(startBtn);
        buttonPanel.add(stopBtn);

        buttonPanel.setBackground(Status.getBackGround());

        settings.addActionListener(e -> {
            new TelemetrySettingsWindow().init();
        });

        stopBtn.addActionListener(e -> {
            Status.setUpdatingTelemetry(false);
        });

        startBtn.addActionListener(e -> {
            UpdateTelemetry updateTelemetry = new UpdateTelemetry();
            updateTelemetry.start();
        });


        //Lists
        JPanel listsPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        listsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] varNames = Data.VARIABLE_NAMES;
        Data data = new Data();
        String[] varValues = data.getCurrentValuesAsArray();

        JList<String> namesList = new JList<>(varNames);
        JList<String> valuesList = new JList<>(varValues);

        namesList.setBackground(Status.getMiddle());
        valuesList.setBackground(Status.getMiddle());

        JScrollPane namesScrollPane = new JScrollPane(namesList);
        JScrollPane valuesScrollPane = new JScrollPane(valuesList);

        namesScrollPane.setBorder(BorderFactory.createTitledBorder("Variables"));
        valuesScrollPane.setBorder(BorderFactory.createTitledBorder("Values"));


        namesScrollPane.setBackground(Status.getForegroundColor());
        valuesScrollPane.setBackground(Status.getForegroundColor());


        listsPanel.add(namesScrollPane);
        listsPanel.add(valuesScrollPane);

        this.frame.add(buttonPanel, BorderLayout.NORTH);
        this.frame.add(listsPanel, BorderLayout.CENTER);

        listsPanel.setBackground(Status.getBackGround());

        Timer timer = new Timer(500, e -> {
            Data freshData = Status.getData();
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