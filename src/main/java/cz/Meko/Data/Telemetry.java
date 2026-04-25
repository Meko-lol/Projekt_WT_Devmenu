package cz.Meko.Data;

public class Telemetry {
    private static boolean updatingTelemetry = false;
    private static Data data = new Data();


    public static boolean isUpdatingTelemetry() {
        return updatingTelemetry;
    }

    public static Data getData() {
        return data;
    }
}
