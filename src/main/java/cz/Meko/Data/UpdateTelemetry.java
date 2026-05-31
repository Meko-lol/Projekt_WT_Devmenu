package cz.Meko.Data;

/**
 * A background thread responsible for continuously fetching live telemetry data.
 * <p>
 * When started, this thread polls the local API at regular intervals (500ms)
 * using {@link WTIO} and updates the globally accessible {@link Status} object
 * with the latest {@link Data}. It runs until the updating flag is set to false
 * or the thread is formally interrupted.
 * </p>
 */
public class UpdateTelemetry extends Thread {

    /**
     * Begins the continuous background polling loop.
     */
    @Override
    public void run() {
        // Force the flag to true so the loop actually starts
        Status.setUpdatingTelemetry(true);

        // Instantiate WTIO once outside the loop to save memory and CPU overhead
        WTIO wtio = new WTIO();

        while (Status.isUpdatingTelemetry()) {
            Data fetchedData = wtio.getTelemetry(); // Fetch once per iteration

            if (fetchedData != null) {
                Status.setData(fetchedData);
            }

            try {
                Thread.sleep(500); // Wait 0.5s so you don't overwhelm the CPU/API
            } catch (InterruptedException e) {
                // Properly restore the interrupted status and break the loop
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}