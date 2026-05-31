package cz.Meko.Windows;

import cz.Meko.Data.Data;
import cz.Meko.Data.Status;
import cz.Meko.Data.UpdateTelemetry;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the main telemetry viewing window.
 * <p>
 * This graphical interface displays live telemetry data in a two-column format
 * (Variables and Values). It provides controls to start and stop the live data
 * feed, and a settings button to filter which variables are currently visible.
 * </p>
 */
public class TelemetryWindow {
    private final JFrame frame;

    /**
     * Constructs a new TelemetryWindow instance.
     * Initializes the underlying JFrame with the default title "Telemetry Window".
     */
    public TelemetryWindow() {
        frame = new JFrame("Telemetry Window");
    }

    /**
     * Initializes the user interface components, layout, and event handlers.
     * <p>
     * This method builds a layout consisting of:
     * <ul>
     * <li>A top control panel with Settings, Start, and Stop buttons.</li>
     * <li>A central split-pane containing synchronized, auto-updating lists
     * for variable names and their current values.</li>
     * </ul>
     * It also sets up a {@link Timer} that continuously polls for fresh telemetry
     * data and updates the UI, respecting the visibility preferences set in the
     * settings window.
     * </p>
     */
    public void init() {
        // --- 1. Frame Setup ---
        this.frame.setSize(600, 600);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(new BorderLayout(10, 10));

        // --- 2. Control Panel (Buttons) Setup ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 5));
        buttonPanel.setBackground(Status.getBackGround());

        JButton settings = new JButton("Settings");
        JButton startBtn = new JButton("Start");
        JButton stopBtn = new JButton("Stop");

        Status.configureButton(settings, 20);
        Status.configureButton(startBtn, 20);
        Status.configureButton(stopBtn, 20);

        buttonPanel.add(settings);
        buttonPanel.add(startBtn);
        buttonPanel.add(stopBtn);

        // --- 3. Main Data Container Setup ---
        // Added 10px horizontal gap so the rounded borders don't overlap
        JPanel listsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        listsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        listsPanel.setBackground(Status.getBackGround());

        // --- 4. Data Initialization & List Creation ---
        String[] varNames = Data.VARIABLE_NAMES;
        Data data = new Data();
        String[] varValues = data.getCurrentValuesAsArray();

        int rowHeight = 25; // Standard row height for the lists

        JList<String> namesList = new JList<>(varNames);
        JList<String> valuesList = new JList<>(varValues);

        Status.configureList(namesList, rowHeight);
        Status.configureList(valuesList, rowHeight);

        // --- 5. ScrollPane Creation & Syncing ---
        JScrollPane namesScrollPane = new JScrollPane(namesList);
        JScrollPane valuesScrollPane = new JScrollPane(valuesList);

        // Link the scroll bars so scrolling one side scrolls the other
        BoundedRangeModel model = namesScrollPane.getVerticalScrollBar().getModel();
        valuesScrollPane.getVerticalScrollBar().setModel(model);
        namesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // --- 6. ScrollPane Styling & Borders ---
        Status.configureJScrollPane(namesScrollPane);
        Status.configureJScrollPane(valuesScrollPane);

        // Make transparent so rounded borders show correctly
        namesScrollPane.setOpaque(false);
        namesScrollPane.getViewport().setOpaque(false);
        valuesScrollPane.setOpaque(false);
        valuesScrollPane.getViewport().setOpaque(false);

        // Apply custom rounded borders
        namesScrollPane.setBorder(Status.createRoundedTitledBorder("Variables"));
        valuesScrollPane.setBorder(Status.createRoundedTitledBorder("Values"));

        // --- 7. Live Update Timer Logic ---
        Timer timer = new Timer(500, e -> {
            Data freshData = Status.getData();
            if (freshData != null) {
                String[] allNames = Data.VARIABLE_NAMES;
                String[] allValues = freshData.getCurrentValuesAsArray();

                java.util.List<String> visibleNames = new java.util.ArrayList<>();
                java.util.List<String> visibleValues = new java.util.ArrayList<>();

                for (int i = 0; i < allNames.length; i++) {
                    // Only display variables toggled "on" in the settings
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

        // --- 8. Action Listeners Setup ---
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

        // --- 9. Final Assembly ---
        listsPanel.add(namesScrollPane);
        listsPanel.add(valuesScrollPane);

        this.frame.add(buttonPanel, BorderLayout.NORTH);
        this.frame.add(listsPanel, BorderLayout.CENTER);

        this.frame.getContentPane().setBackground(Status.getBackGround());
        this.frame.setVisible(true);
    }
}