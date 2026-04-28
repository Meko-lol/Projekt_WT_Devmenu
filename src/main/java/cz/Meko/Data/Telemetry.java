package cz.Meko.Data;

public class Telemetry {
    private static boolean updatingTelemetry = true;
    private static Data data = new Data();

    public static void setData(Data data) {
        Telemetry.data = data;
    }

    public static boolean isUpdatingTelemetry() {
        return updatingTelemetry;
    }

    public static Data getData() {
        return data;
    }
}
