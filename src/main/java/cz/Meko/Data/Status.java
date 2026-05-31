package cz.Meko.Data;

import cz.Meko.Windows.MainWindow;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A centralized utility and state-management class.
 * <p>
 * This class serves two primary purposes:
 * <ul>
 * <li><b>Global State:</b> It stores live telemetry data, application state flags
 * (like whether updating is active), and user preferences (like variable visibility).</li>
 * <li><b>UI Theme Engine:</b> It provides reusable styling methods to ensure
 * a consistent, modern, and rounded aesthetic across all Swing components.</li>
 * </ul>
 * </p>
 */
public class Status {

    // ==========================================
    // 1. GLOBAL STATE & THEME CONFIGURATION
    // ==========================================

    private static final MainWindow mainWindow = new MainWindow();

    @Getter @Setter
    private static boolean updatingTelemetry = false;

    @Getter @Setter
    private static Data data = new Data();

    // Tracks which variables are checked in the TelemetrySettingsWindow
    private static final boolean[] rowVisibility = new boolean[Data.VARIABLE_NAMES.length];

    // --- Theme Colors ---
    @Getter
    private static final Color foregroundColor = new Color(128, 128, 128);

    @Getter
    private static final Color middle = new Color(200, 200, 200);

    @Getter
    private static final Color backGround = new Color(60, 60, 60);

    // --- Theme Metrics ---
    private static final int roundness = 30;

    @Getter
    private static final String font = "Segoe UI";


    // ==========================================
    // 2. STATE MANAGEMENT METHODS
    // ==========================================

    /**
     * Initializes and displays the main application window.
     */
    public static void openWindow() {
        mainWindow.init();
    }

    /**
     * Retrieves the visibility state for a specific telemetry variable.
     *
     * @param index The index of the variable in {@link Data#VARIABLE_NAMES}.
     * @return true if the variable should be visible, false otherwise.
     */
    public static boolean getFromIndex(int index) {
        return rowVisibility[index];
    }

    /**
     * Updates the visibility state for a specific telemetry variable.
     *
     * @param index The index of the variable in {@link Data#VARIABLE_NAMES}.
     * @param value true to make the variable visible, false to hide it.
     */
    public static void setToIndex(int index, boolean value) {
        rowVisibility[index] = value;
    }


    // ==========================================
    // 3. UI STYLING UTILITIES
    // ==========================================

    /**
     * Configures a {@link JButton} to match the application's modern, rounded theme.
     * Applies custom background rendering, anti-aliased text, and interactive hover/click effects.
     *
     * @param button The JButton to style.
     * @param size   The font size to apply to the button text.
     */
    public static void configureButton(JButton button, int size) {
        // 1. Basic properties
        button.setFont(new Font(Status.getFont(), Font.BOLD, size));
        button.setForeground(Status.getBackGround());
        button.setBackground(Status.getMiddle());
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        // Custom renderer for rounded corners
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(c.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), roundness, roundness);

                super.paint(g2, c); // Paint the text over our custom background
                g2.dispose();
            }
        });

        // Hover and Click Effects
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Status.getMiddle().brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Status.getMiddle());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(Status.getMiddle().darker());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (button.contains(e.getPoint())) {
                    button.setBackground(Status.getMiddle().brighter());
                } else {
                    button.setBackground(Status.getMiddle());
                }
            }
        });
    }

    /**
     * Strips the default UI from a {@link JScrollPane} and applies a modern,
     * transparent look with custom thin, rounded scrollbars.
     *
     * @param scrollPane The JScrollPane to style.
     */
    public static void configureJScrollPane(JScrollPane scrollPane) {
        // 1. Remove harsh default borders
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());

        // 2. Ensure the background is clean (Uses Status theme color)
        scrollPane.getViewport().setBackground(Status.getBackGround());
        scrollPane.setBackground(Status.getBackGround());

        // 3. Apply the modern, rounded UI to the vertical scrollbar
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        verticalBar.setUI(new ModernScrollBarUI());
        verticalBar.setPreferredSize(new Dimension(12, 0)); // Make it thinner
        verticalBar.setOpaque(false);

        // 4. Apply the modern, rounded UI to the horizontal scrollbar
        JScrollBar horizontalBar = scrollPane.getHorizontalScrollBar();
        horizontalBar.setUI(new ModernScrollBarUI());
        horizontalBar.setPreferredSize(new Dimension(0, 12)); // Make it thinner
        horizontalBar.setOpaque(false);

        // 5. Clean up the corner where the two scrollbars meet
        JPanel emptyCorner = new JPanel();
        emptyCorner.setBackground(Status.getBackGround());
        scrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, emptyCorner);

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
    }

    /**
     * Styles a {@link JList} with custom rounded selection highlights, padding,
     * and modern colors.
     *
     * @param list      The JList to configure.
     * @param rowHeight The fixed pixel height for each row.
     */
    public static void configureList(JList<?> list, int rowHeight) {
        // 1. True "Light Gray" background
        list.setBackground(new Color(174, 174, 174));
        list.setForeground(Color.BLACK);
        list.setFont(new Font(Status.getFont(), Font.BOLD, 12));
        list.setFixedCellHeight(rowHeight);

        // 2. A slightly darker gray for the selection highlight to create contrast
        list.setSelectionBackground(new Color(125, 125, 125));
        list.setSelectionForeground(Color.BLACK);

        // 3. Smooth UI override
        list.setUI(new javax.swing.plaf.basic.BasicListUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                super.paint(g2, c);
                g2.dispose();
            }
        });

        // 4. Custom Renderer to draw ROUNDED selection highlights!
        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Add padding so text doesn't hug the edges
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

                // Prevent Java from painting its default sharp rectangular background
                setOpaque(false);

                // Apply correct colors based on selection
                if (isSelected) {
                    setForeground(list.getSelectionForeground());
                    setBackground(list.getSelectionBackground());
                } else {
                    setForeground(list.getForeground());
                    setBackground(list.getBackground());
                }

                return this;
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());

                // Draw a sleek, rounded background for the row!
                g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 15, 15);

                g2.dispose();

                // Paint the text on top of our new rounded background
                super.paintComponent(g);
            }
        });
    }

    /**
     * Creates a custom, modern, rounded border with a title embedded seamlessly in the line.
     *
     * @param title The text to display within the border line.
     * @return A compiled {@link javax.swing.border.Border} object.
     */
    public static javax.swing.border.Border createRoundedTitledBorder(String title) {
        return new javax.swing.border.AbstractBorder() {
            private final Insets insets = new Insets(25, 15, 15, 15); // Padding inside the border

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                // Enable anti-aliasing for smooth lines
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setFont(new Font(Status.getFont(), Font.BOLD, 14));
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(title);

                // 1. Draw the rounded border line (Using the foreground gray)
                g2.setColor(Status.getForegroundColor());
                g2.setStroke(new BasicStroke(2f)); // Slightly thicker modern line

                // How rounded the border corners are
                int radius = 15;
                g2.drawRoundRect(x + 1, y + 10, width - 3, height - 12, radius, radius);

                // 2. Erase the line behind the text so it looks seamless
                // We use the background color of the main panel to mask the line
                g2.setColor(Status.getBackGround());
                g2.fillRect(x + 15, y, textWidth + 10, 20);

                // 3. Draw the title text (Using the lighter middle color)
                g2.setColor(Status.getMiddle());
                g2.drawString(title, x + 20, y + 15);

                g2.dispose();
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return insets;
            }
        };
    }


    // ==========================================
    // 4. INNER UTILITY CLASSES
    // ==========================================

    /**
     * A custom UI class that hides default Swing scrollbar buttons (arrows)
     * and paints modern, rounded thumbs that react to user interaction.
     */
    private static class ModernScrollBarUI extends BasicScrollBarUI {

        // Hide the down/right arrow button
        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        // Hide the up/left arrow button
        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        // Helper to create an invisible button
        private JButton createZeroButton() {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(0, 0));
            button.setMinimumSize(new Dimension(0, 0));
            button.setMaximumSize(new Dimension(0, 0));
            return button;
        }

        // Apply a subtle track background
        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            // Draw a very subtle background for the track
            g2.setColor(Status.getBackGround().darker());
            g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
            g2.dispose();
        }

        // Paint the rounded thumb
        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
                return;
            }

            Graphics2D g2 = (Graphics2D) g.create();
            // Enable anti-aliasing for smooth rounded edges
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Change color if the user is dragging or hovering (using Status theme colors)
            boolean isHovered = isThumbRollover();
            boolean isPressed = isDragging;

            if (isPressed || isHovered) {
                g2.setColor(Status.getMiddle());
            } else {
                g2.setColor(Status.getForegroundColor());
            }

            // Draw the rounded rectangle
            // Padding between the thumb and the track edge
            int PADDING = 2;
            // Controls how rounded the corners are
            int ARC_SIZE = 10;
            g2.fillRoundRect(
                    thumbBounds.x + PADDING,
                    thumbBounds.y + PADDING,
                    thumbBounds.width - (PADDING * 2),
                    thumbBounds.height - (PADDING * 2),
                    ARC_SIZE,
                    ARC_SIZE
            );

            g2.dispose();
        }
    }
}