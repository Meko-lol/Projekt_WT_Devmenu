package cz.Meko.Data;

public class UpdateTelemetry extends Thread {
    @Override
    public void run() {
        // Force the flag to true so the loop actually starts

        while (Telemetry.isUpdatingTelemetry()) {
            WTIO wtio = new WTIO();
            Data fetchedData = wtio.getTelemetry(); // Fetch once

            if (fetchedData != null) {
                Telemetry.setData(fetchedData);
            }

            try {
                Thread.sleep(500); // Wait 0.5s so you don't overwhelm the CPU/API
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}