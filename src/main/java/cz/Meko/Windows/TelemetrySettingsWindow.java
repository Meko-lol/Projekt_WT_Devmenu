package cz.Meko.Windows;

import cz.Meko.Data.Data;
import cz.Meko.Data.Status;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the settings window for configuring telemetry data visibility.
 * <p>
 * This graphical interface provides a list of all available telemetry variables
 * and allows the user to toggle their active/visible state using checkboxes.
 * User selections are immediately synchronized with the application's global {@link Status}.
 * </p>
 */
public class TelemetrySettingsWindow {
    private final JFrame frame;

    /**
     * Constructs a new TelemetrySettingsWindow instance.
     * Initializes the underlying JFrame with the default title "Telemetry settings window".
     */
    public TelemetrySettingsWindow() {
        frame = new JFrame("Telemetry settings window");
    }

    /**
     * Initializes the user interface components, data bindings, and layout,
     * and makes the window visible.
     * <p>
     * This method builds a split-view layout containing:
     * <ul>
     * <li>A list of variable names sourced from {@link Data#VARIABLE_NAMES}.</li>
     * <li>A parallel list of styled checkboxes to toggle each variable's visibility.</li>
     * </ul>
     * It also synchronizes the scrollbars of both lists to ensure they scroll
     * together seamlessly, and attaches action listeners to update the {@link Status}
     * when a checkbox is toggled.
     * </p>
     */
    public void init() {
        // --- 1. Frame Setup ---
        this.frame.setSize(600, 600);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(new BorderLayout(10, 10));

        // --- 2. Main Container Setup ---
        JPanel listsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        listsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        listsPanel.setBackground(Status.getBackGround());

        // --- 3. Data & Constants ---
        int rowHeight = 25;
        String[] varNames = Data.VARIABLE_NAMES;

        // --- 4. Variables List Creation ---
        JList<String> namesList = new JList<>(varNames);
        Status.configureList(namesList, rowHeight);

        // --- 5. Checkbox Panel Creation ---
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        checkBoxPanel.setBackground(new Color(179, 179, 179));

        JCheckBox[] checkBoxes = new JCheckBox[varNames.length];

        for (int i = 0; i < varNames.length; i++) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setBackground(new Color(174, 174, 174));
            checkBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            checkBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, rowHeight));
            checkBox.setPreferredSize(new Dimension(checkBox.getPreferredSize().width, rowHeight));

            checkBox.setUI(new javax.swing.plaf.basic.BasicCheckBoxUI() {
                @Override
                public void paint(Graphics g, JComponent c) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(c.getBackground());
                    super.paint(g2, c);
                    g2.dispose();
                }
            });

            // Action logic and defaults
            final int index = i;
            checkBox.setSelected(true);
            Status.setToIndex(index, true);
            checkBox.addActionListener(e -> Status.setToIndex(index, checkBox.isSelected()));

            checkBoxes[i] = checkBox;
            checkBoxPanel.add(checkBox);
        }

        // --- 6. ScrollPane Creation & Syncing ---
        JScrollPane namesScrollPane = new JScrollPane(namesList);
        JScrollPane checkBoxScrollPane = new JScrollPane(checkBoxPanel);

        // Link the scroll bars so scrolling one side scrolls the other
        BoundedRangeModel model = namesScrollPane.getVerticalScrollBar().getModel();
        checkBoxScrollPane.getVerticalScrollBar().setModel(model);

        // Hide the scrollbar on the list side for a cleaner look
        namesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // --- 7. ScrollPane Styling & Borders ---
        Status.configureJScrollPane(namesScrollPane);
        Status.configureJScrollPane(checkBoxScrollPane);

        namesScrollPane.setOpaque(false);
        namesScrollPane.getViewport().setOpaque(false);
        namesScrollPane.setBorder(Status.createRoundedTitledBorder("Variables"));

        checkBoxScrollPane.setOpaque(false);
        checkBoxScrollPane.getViewport().setOpaque(false);
        checkBoxScrollPane.setBorder(Status.createRoundedTitledBorder("Visibility"));

        // --- 8. Final Assembly ---
        listsPanel.add(namesScrollPane);
        listsPanel.add(checkBoxScrollPane);

        this.frame.add(listsPanel, BorderLayout.CENTER);
        this.frame.setVisible(true);
    }
}