package cz.Meko.Data;

import cz.Meko.Windows.TelemetryWindow;

public class Telemetry {
    private static boolean updatingTelemetry = false;
    private static Data data = new Data();
    private static TelemetryWindow telemetryWindow = new TelemetryWindow();

    public static void openTelemetryWindow () {
        telemetryWindow.init();
    }

    public static boolean isUpdatingTelemetry() {
        return updatingTelemetry;
    }

    public static void setUpdatingTelemetry(boolean updatingTelemetry) {
        Telemetry.updatingTelemetry = updatingTelemetry;
    }

    public static Data getData() {
        return data;
    }

    public static void setData(Data data) {
        Telemetry.data = data;
    }

    public static TelemetryWindow getTelemetryWindow() {
        return telemetryWindow;
    }

    public static void setTelemetryWindow(TelemetryWindow telemetryWindow) {
        Telemetry.telemetryWindow = telemetryWindow;
    }
}
