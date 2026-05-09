package cz.Meko.Windows;

import cz.Meko.Data.Status;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private final JFrame frame;

    public MainWindow() {
        frame = new JFrame("Main Window");
    }

    public void init() {
        this.frame.setSize(1000, 600);
        this.frame.setLocationRelativeTo(null);

        this.frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));


        JLabel jLabel = new JLabel("Menu");
        jLabel.setFont(new Font("Segoe UI", Font.BOLD, 200));
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JButton showTelemetryButton = new JButton("Show telemetry");
        Status.configureButton(showTelemetryButton, 40);

        showTelemetryButton.addActionListener(e -> {
            new TelemetryWindow().init();
        });


        JButton setVariablesButton = new JButton("Set variables");
        Status.configureButton(setVariablesButton, 40);

        setVariablesButton.addActionListener(e -> {
            new TelemetryWindow().init();
        });


        frame.add(jLabel);
        frame.add(Box.createVerticalStrut(20));
        frame.add(showTelemetryButton);
        frame.add(Box.createVerticalStrut(20));
        frame.add(setVariablesButton);

        frame.setVisible(true);
    }
}
