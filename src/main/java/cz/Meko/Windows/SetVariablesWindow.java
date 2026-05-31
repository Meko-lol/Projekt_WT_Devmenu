package cz.Meko.Windows;

import cz.Meko.Data.Status;
import cz.Meko.Data.WTIO;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the window used for manually overriding game variables.
 * <p>
 * This interface provides a three-column layout consisting of:
 * <ul>
 * <li>The target variable names.</li>
 * <li>Text input fields to enter new values.</li>
 * <li>Action buttons to push the changes to the live game via the local API.</li>
 * </ul>
 * </p>
 */
public class SetVariablesWindow {
    private final JFrame frame;

    /**
     * Constructs a new SetVariablesWindow instance.
     * Initializes the underlying JFrame with the title "Set Variables Window".
     */
    public SetVariablesWindow() {
        frame = new JFrame("Set Variables Window");
    }

    /**
     * Initializes the user interface components, layout, and event handlers.
     * <p>
     * This method builds the synchronized 3-column view. It also binds the "Apply"
     * buttons to a {@link WTIO} network call, translating the UI inputs into
     * GET requests sent to the game's HTTP editor endpoints.
     * </p>
     */
    public void init() {
        // --- 1. Frame Setup ---
        this.frame.setSize(600, 600);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(new BorderLayout(10, 0));

        // --- 2. Main Container Setup ---
        // 3 columns with a 10px horizontal gap so borders don't touch
        JPanel listsPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        listsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        listsPanel.setBackground(Status.getBackGround());

        // --- 3. Data Preparation ---
        String[] setableVariableNames = getVariableNames();
        int rowHeight = 25; // Slightly increased row height so rounded buttons fit nicely

        // --- 4. Variables Column (Labels) ---
        JList<String> variablesList = new JList<>(setableVariableNames);
        Status.configureList(variablesList, rowHeight);

        // --- 5. Values Column (Input Fields) ---
        JTextField[] textFieldsArray = new JTextField[setableVariableNames.length];
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
        textFieldPanel.setBackground(Status.getMiddle());

        for (int i = 0; i < setableVariableNames.length; i++) {
            JTextField textField = new JTextField();

            // Use a very light gray (almost white) so it's instantly recognizable as an input field
            textField.setBackground(new Color(240, 240, 240));
            textField.setForeground(Color.BLACK); // Dark text for perfect readability
            textField.setCaretColor(Color.BLACK); // Dark cursor

            // Outline the text field with the medium gray to frame it cleanly
            javax.swing.border.Border line = new javax.swing.border.LineBorder(Status.getForegroundColor(), 1, true);
            javax.swing.border.Border padding = BorderFactory.createEmptyBorder(0, 8, 0, 8);
            textField.setBorder(BorderFactory.createCompoundBorder(line, padding));

            textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, rowHeight));
            textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, rowHeight));

            textFieldsArray[i] = textField;
            textFieldPanel.add(textField);
        }

        // --- 6. Actions Column (Buttons) ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Status.getMiddle());

        WTIO wtio = new WTIO(); // Reusable IO instance for the buttons

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

                switch (variableName) {
                    case "Altitude" ->
                            wtio.setAtribute("http://localhost:8111/editor/fm_commands?cmd=set" + "Alt" + "&value=" + inputValue);
                    default ->
                            wtio.setAtribute("http://localhost:8111/editor/fm_commands?cmd=set" + variableName + "&value=" + inputValue);
                }
            });

            buttonPanel.add(button);
        }

        // --- 7. ScrollPane Creation & Syncing ---
        JScrollPane listScroller = new JScrollPane(variablesList);
        JScrollPane textFieldScrollPane = new JScrollPane(textFieldPanel);
        JScrollPane buttonScrollPane = new JScrollPane(buttonPanel);

        // Link all three scrollbars together so the rows never become misaligned
        BoundedRangeModel scrollModel = listScroller.getVerticalScrollBar().getModel();
        textFieldScrollPane.getVerticalScrollBar().setModel(scrollModel);
        buttonScrollPane.getVerticalScrollBar().setModel(scrollModel);

        // Hide scrollbars on the first two columns for a cleaner look
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        textFieldScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // --- 8. ScrollPane Styling & Borders ---
        Status.configureJScrollPane(listScroller);
        Status.configureJScrollPane(textFieldScrollPane);
        Status.configureJScrollPane(buttonScrollPane);

        // Make transparent so rounded borders show correctly
        listScroller.setOpaque(false);
        listScroller.getViewport().setOpaque(false);
        textFieldScrollPane.setOpaque(false);
        textFieldScrollPane.getViewport().setOpaque(false);
        buttonScrollPane.setOpaque(false);
        buttonScrollPane.getViewport().setOpaque(false);

        // Apply custom rounded borders
        listScroller.setBorder(Status.createRoundedTitledBorder("Variables"));
        textFieldScrollPane.setBorder(Status.createRoundedTitledBorder("Values"));
        buttonScrollPane.setBorder(Status.createRoundedTitledBorder("Actions"));

        // --- 9. Final Assembly ---
        listsPanel.add(listScroller);
        listsPanel.add(textFieldScrollPane);
        listsPanel.add(buttonScrollPane);

        this.frame.add(listsPanel, BorderLayout.CENTER);
        this.frame.setVisible(true);
    }

    /**
     * Provides the list of variables that can be manually overridden in the game.
     *
     * @return An array of valid variable name strings.
     */
    private String[] getVariableNames() {
        return new String[]{
                "Altitude", "Velocity", "FuelMassRatio", "Mass",
                "CoolantOrHeadTemperature", "OilTemperature",
                "Temperature", "Pressure", "TimeSpeed"
        };
    }
}