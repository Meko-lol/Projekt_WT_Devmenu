package cz.Meko.Windows;

import cz.Meko.Data.Status;
import cz.Meko.Data.WTIO;

import javax.swing.*;
import java.awt.*;

public class SetVariablesWindow {
    private final JFrame frame;

    public SetVariablesWindow() {
        frame = new JFrame("Telemetry settings window");
    }

    public void init() {
        this.frame.setSize(600, 600);
        this.frame.setLocationRelativeTo(null);

        this.frame.setLayout(new BorderLayout(10, 0));

        int rowHeight = 25; // Slightly increased row height so rounded buttons fit nicely

        JPanel listsPanel = new JPanel(new GridLayout(1, 3, 10, 0)); // Added 10px horizontal gap so borders don't touch

        listsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] setableVariableNames = getVariableNames();

        // --- Apply the new centralized list configuration! ---
        JList<String> variables = new JList<>(setableVariableNames);
        Status.configureList(variables, rowHeight);

        // --- Text Field Panel ---
        JTextField[] textFieldsArray = new JTextField[setableVariableNames.length];
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
        textFieldPanel.setBackground(Status.getMiddle());

        for (int i = 0; i < setableVariableNames.length; i++) {
            JTextField textField = new JTextField();

            // FIX: Use a very light gray (almost white) so it's instantly recognizable as an input field
            textField.setBackground(new Color(240, 240, 240));
            textField.setForeground(Color.BLACK); // Dark text for perfect readability
            textField.setCaretColor(Color.BLACK); // Dark cursor

            // FIX: Outline the text field with the medium gray to frame it cleanly
            javax.swing.border.Border line = new javax.swing.border.LineBorder(Status.getForegroundColor(), 1, true);
            javax.swing.border.Border padding = BorderFactory.createEmptyBorder(0, 8, 0, 8);
            textField.setBorder(BorderFactory.createCompoundBorder(line, padding));

            textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, rowHeight));
            textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, rowHeight));

            textFieldsArray[i] = textField;
            textFieldPanel.add(textField);
        }

        // --- Button Panel ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Status.getMiddle());

        for (int i = 0; i < setableVariableNames.length; i++) {
            final String variableName = setableVariableNames[i];
            final int index = i;

            JButton button = new JButton("Apply");

            // Apply the modern button styling from Status
            Status.configureButton(button, 12);
            // Adjust borders slightly so it fits in the list row
            button.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));

            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, rowHeight));
            button.setPreferredSize(new Dimension(button.getPreferredSize().width, rowHeight));

            button.addActionListener(e -> {
                String inputValue = textFieldsArray[index].getText();

                System.out.println("Triggering update for: " + variableName + " with value: " + inputValue);

                WTIO wtio = new WTIO();

                switch (variableName) {
                    case "Altitute" ->
                            wtio.setAtribute("http://localhost:8111/editor/fm_commands?cmd=set" + "Alt" + "&value=" + inputValue);
                    default ->
                            wtio.setAtribute("http://localhost:8111/editor/fm_commands?cmd=set" + variableName + "&value=" + inputValue);
                }
            });

            buttonPanel.add(button);
        }

        // --- Create Scroll Panes ---
        JScrollPane listScroller = new JScrollPane(variables);
        JScrollPane textFieldscrollPane = new JScrollPane(textFieldPanel);
        JScrollPane buttonScrollPane = new JScrollPane(buttonPanel);

        // 1. Apply base configuration first
        Status.configureJScrollPane(listScroller);
        Status.configureJScrollPane(textFieldscrollPane);
        Status.configureJScrollPane(buttonScrollPane);

        // 2. Make transparent so rounded borders show correctly
        listScroller.setOpaque(false);
        listScroller.getViewport().setOpaque(false);
        textFieldscrollPane.setOpaque(false);
        textFieldscrollPane.getViewport().setOpaque(false);
        buttonScrollPane.setOpaque(false);
        buttonScrollPane.getViewport().setOpaque(false);

        // 3. Apply custom rounded borders
        listScroller.setBorder(Status.createRoundedTitledBorder("Variables"));
        textFieldscrollPane.setBorder(Status.createRoundedTitledBorder("Values"));
        buttonScrollPane.setBorder(Status.createRoundedTitledBorder("Actions"));

        // Add to main panel
        listsPanel.add(listScroller);
        listsPanel.add(textFieldscrollPane);
        listsPanel.add(buttonScrollPane);

        listsPanel.setBackground(Status.getBackGround());
        this.frame.add(listsPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private String[] getVariableNames() {
        return new String[]{"Altitude", "Velocity", "FuelMassRatio", "Mass", "CoolantOrHeadTemperature", "OilTemperature", "Temperature", "Pressure", "TimeSpeed"};
    }
}