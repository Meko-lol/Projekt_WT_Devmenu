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

        jLabel.setForeground(Color.WHITE);

        jLabel.setFont(new Font("Segoe UI", Font.BOLD, 200));
        jLabel.setUI(new javax.swing.plaf.basic.BasicLabelUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(c.getBackground());

                super.paint(g2, c); // Paint the text over our custom background
                g2.dispose();
            }
        });
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JButton showTelemetryButton = new JButton("Show telemetry");
        Status.configureButton(showTelemetryButton, 40);

        showTelemetryButton.addActionListener(e -> {
            new TelemetryWindow().init();
        });


        JButton setVariablesButton = new JButton("Set variables");
        Status.configureButton(setVariablesButton, 40);

        setVariablesButton.addActionListener(e -> {
            new SetVariablesWindow().init();
        });


        frame.add(jLabel);
        frame.add(Box.createVerticalStrut(20));
        frame.add(showTelemetryButton);
        frame.add(Box.createVerticalStrut(20));
        frame.add(setVariablesButton);

        frame.getContentPane().setBackground(Status.getBackGround());

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}
