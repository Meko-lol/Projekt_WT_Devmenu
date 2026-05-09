package cz.Meko.Data;

import cz.Meko.Windows.MainWindow;

import javax.swing.*;
import java.awt.*;

public class Status {
    private static final MainWindow mainWindow = new MainWindow();
    private static boolean updatingTelemetry = false;
    private static Data data = new Data();
    private static boolean[] rowVisibility = new boolean[Data.VARIABLE_NAMES.length];

    //Colors
    private static Color foregroundColor = new Color(128, 128, 128);
    private static Color middle = new Color(200, 200, 200);
    private static Color backGround = new Color(60, 60, 60);

    //font
    private static String font = "Segoe UI";


    public static void openWindow() {
        mainWindow.init();
    }

    public static boolean isUpdatingTelemetry() {
        return updatingTelemetry;
    }

    public static void setUpdatingTelemetry(boolean updatingTelemetry) {
        Status.updatingTelemetry = updatingTelemetry;
    }

    public static Data getData() {
        return data;
    }

    public static void setData(Data data) {
        Status.data = data;
    }

    public static Color getForegroundColor() {
        return foregroundColor;
    }

    public static Color getMiddle() {
        return middle;
    }

    public static Color getBackGround() {
        return backGround;
    }

    public static String getFont() {
        return font;
    }

    public static void configureButton(JButton button, int size) {
        button.setFont(new Font(Status.getFont(), Font.BOLD, size));
        button.setBackground(Status.getMiddle());
        button.setForeground(Status.getBackGround());
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 5), BorderFactory.createEmptyBorder(4, 10, 4, 10)));
    }

    public static boolean getFromIndex (int index) {
        return rowVisibility[index];
    }


    public static void setToIndex (int index, boolean value) {
        rowVisibility[index] = value;
    }
}
