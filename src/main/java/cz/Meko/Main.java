package cz.Meko;

import cz.Meko.Data.UpdateTelemetry;
import cz.Meko.Windows.TelemetryWindow;

public class Main {
    public static void main(String[] args) {
        UpdateTelemetry updateTelemetry = new UpdateTelemetry();
        updateTelemetry.start();
        TelemetryWindow telemetryWindow = new TelemetryWindow();
        telemetryWindow.init();


    }
}