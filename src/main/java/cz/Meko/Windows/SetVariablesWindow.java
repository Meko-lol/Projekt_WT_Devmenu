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

        int rowHeight = 20;

        JPanel listsPanel = new JPanel(new GridLayout(1, 3, 0, 0));

        listsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] setableVariableNames = getVariableNames();

        JList<String> variables = new JList<>(setableVariableNames);
        variables.setBackground(Status.getMiddle());
        variables.setFixedCellHeight(rowHeight);


        //variables
        JScrollPane listScroller = new JScrollPane(variables);
        listScroller.setBorder(BorderFactory.createTitledBorder("Variables"));
        listScroller.setBackground(Status.getForegroundColor());
        listsPanel.add(listScroller);


        JTextField[] textFieldsArray = new JTextField[setableVariableNames.length];

        // --- Text Field Panel ---
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
        textFieldPanel.setBackground(Status.getMiddle());

        for (int i = 0; i < setableVariableNames.length; i++) {
            JTextField textField = new JTextField();
            textField.setBackground(Status.getMiddle());

            textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, rowHeight));
            textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, rowHeight));

            // 2. Save the created text field into our array at the current index
            textFieldsArray[i] = textField;

            textFieldPanel.add(textField);
        }

        JScrollPane textFieldscrollPane = new JScrollPane(textFieldPanel);
        textFieldscrollPane.setBorder(BorderFactory.createTitledBorder("values"));
        textFieldscrollPane.setBackground(Status.getForegroundColor());
        listsPanel.add(textFieldscrollPane);


        // --- Button Panel ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Status.getMiddle());

        for (int i = 0; i < setableVariableNames.length; i++) {
            final String variableName = setableVariableNames[i];
            final int index = i; // Save the current index for the button's action listener

            JButton button = new JButton("Apply");
            button.setBackground(Status.getMiddle());

            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, rowHeight));
            button.setPreferredSize(new Dimension(button.getPreferredSize().width, rowHeight));

            button.addActionListener(e -> {
                // 3. Grab the text from the matching text field using the index
                String inputValue = textFieldsArray[index].getText();

                System.out.println("Triggering update for: " + variableName + " with value: " + inputValue);

                WTIO wtio = new WTIO();

                // 4. Append the inputValue to the end of your URL string
                switch (variableName) {
                    case "Altitute" -> wtio.setAtribute("http://localhost:8111/editor/fm_commands?cmd=set" + "Alt" + "&value=" + inputValue);
                    default -> wtio.setAtribute("http://localhost:8111/editor/fm_commands?cmd=set" + variableName + "&value=" + inputValue);

                }


            });

            buttonPanel.add(button);
        }
        JScrollPane buttonScrollPane = new JScrollPane(buttonPanel);
        buttonScrollPane.setBorder(BorderFactory.createTitledBorder("Actions"));
        buttonScrollPane.setBackground(Status.getForegroundColor());
        listsPanel.add(buttonScrollPane);


        listsPanel.setBackground(Status.getBackGround());
        this.frame.add(listsPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private String[] getVariableNames() {
        return new String[]{
                "Altitude",
                "Velocity",
                "FuelMassRatio",
                "Mass",
                "CoolantOrHeadTemperature",
                "OilTemperature",
                "Temperature",
                "Pressure",
                "TimeSpeed"
        };
    }
}
