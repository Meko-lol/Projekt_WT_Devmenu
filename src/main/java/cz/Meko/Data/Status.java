package cz.Meko.Data;

import cz.Meko.Windows.MainWindow;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Status {
    private static final MainWindow mainWindow = new MainWindow();

    @Getter @Setter
    private static boolean updatingTelemetry = false;

    @Getter @Setter
    private static Data data = new Data();

    private static boolean[] rowVisibility = new boolean[Data.VARIABLE_NAMES.length];

    //Colors
    @Getter
    private static Color foregroundColor = new Color(128, 128, 128);

    @Getter
    private static Color middle = new Color(200, 200, 200);

    @Getter
    private static Color backGround = new Color(60, 60, 60);

    //Roundness
    private static int roundness = 30;

    //font
    @Getter
    private static String font = "Segoe UI";


    public static void openWindow() {
        mainWindow.init();
    }

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

        // 5. Hover Effect
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
     * Creates a custom, modern, rounded border with a title embedded in the line.
     */
    public static javax.swing.border.Border createRoundedTitledBorder(String title) {
        return new javax.swing.border.AbstractBorder() {
            private final int radius = 15; // How rounded the border corners are
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

    public static boolean getFromIndex (int index) {
        return rowVisibility[index];
    }

    public static void setToIndex (int index, boolean value) {
        rowVisibility[index] = value;
    }

    /**
     * Custom UI class that hides default buttons and paints rounded thumbs.
     */
    private static class ModernScrollBarUI extends BasicScrollBarUI {
        private final int ARC_SIZE = 10; // Controls how rounded the corners are
        private final int PADDING = 2; // Padding between the thumb and the track edge

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