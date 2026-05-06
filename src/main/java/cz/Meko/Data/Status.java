package cz.Meko.Data;

import cz.Meko.Windows.TelemetryWindow;

import java.awt.*;

public class Status {
    private static boolean updatingTelemetry = false;
    private static Data data = new Data();
    private static TelemetryWindow telemetryWindow = new TelemetryWindow();

    //Colors
    private static Color foregroundColor = new Color(128, 128, 128);
    private static Color middle = new Color(200, 200, 200);
    private static Color backGround = new Color(60,60,60);

    public static void openTelemetryWindow() {
        telemetryWindow.init();
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

    public static TelemetryWindow getTelemetryWindow() {
        return telemetryWindow;
    }

    public static void setTelemetryWindow(TelemetryWindow telemetryWindow) {
        Status.telemetryWindow = telemetryWindow;
    }

    public static Color getForegroundColor() {
        return foregroundColor;
    }

    public static void setForegroundColor(Color foregroundColor) {
        Status.foregroundColor = foregroundColor;
    }

    public static Color getMiddle() {
        return middle;
    }

    public static void setMiddle(Color middle) {
        Status.middle = middle;
    }

    public static Color getBackGround() {
        return backGround;
    }

    public static void setBackGround(Color backGround) {
        Status.backGround = backGround;
    }
}
