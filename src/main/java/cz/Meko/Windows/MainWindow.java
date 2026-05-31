package cz.Meko.Windows;

import cz.Meko.Data.Status;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the primary graphical user interface (GUI) for the application.
 * This class serves as the main menu, providing navigation buttons to open
 * other application windows such as the Telemetry and Set Variables windows.
 */
public class MainWindow {
    private final JFrame frame;

    /**
     * Constructs a new MainWindow instance.
     * Initializes the underlying JFrame with the default title "Main Window".
     */
    public MainWindow() {
        frame = new JFrame("Main Window");
    }

    /**
     * Initializes the user interface components, layout, and styling.
     * <p>
     * This method configures the frame dimensions, sets a vertical box layout,
     * and populates it with a custom-rendered "Menu" label and functional buttons.
     * It also attaches action listeners to the buttons to launch the respective
     * application windows ({@link TelemetryWindow} and {@link SetVariablesWindow}).
     * Finally, it applies background styling and makes the window visible.
     * </p>
     */
    public void init() {
        this.frame.setSize(1000, 600);
        this.frame.setLocationRelativeTo(null);

        this.frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel jLabel = new JLabel("Menu");
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font("Segoe UI", Font.BOLD, 200));

        // Custom UI renderer for the label to enable anti-aliasing for smoother text
        jLabel.setUI(new javax.swing.plaf.basic.BasicLabelUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(c.getBackground());

                super.paint(g2, c);
                g2.dispose();
            }
        });
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Configure "Show telemetry" button and its action listener
        JButton showTelemetryButton = new JButton("Show telemetry");
        Status.configureButton(showTelemetryButton, 40);
        showTelemetryButton.addActionListener(e -> {
            new TelemetryWindow().init();
        });

        // Configure "Set variables" button and its action listener
        JButton setVariablesButton = new JButton("Set variables");
        Status.configureButton(setVariablesButton, 40);
        setVariablesButton.addActionListener(e -> {
            new SetVariablesWindow().init();
        });

        // Add components to the frame with vertical spacing
        frame.add(jLabel);
        frame.add(Box.createVerticalStrut(20));
        frame.add(showTelemetryButton);
        frame.add(Box.createVerticalStrut(20));
        frame.add(setVariablesButton);

        // Apply background color from Status configuration
        frame.getContentPane().setBackground(Status.getBackGround());

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display the window
        frame.setVisible(true);
    }
}