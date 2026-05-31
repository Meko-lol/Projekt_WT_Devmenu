package cz.Meko.Data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Handles network Input/Output (I/O) operations for the application.
 * <p>
 * This class is responsible for communicating with the local War Thunder web map API
 * (typically hosted on localhost:8111). It provides methods to fetch live telemetry
 * data and send fire-and-forget commands to alter game variables.
 * </p>
 */
public class WTIO {

    /**
     * Sends a "fire-and-forget" GET request to a specific URL to trigger an action or set a variable.
     * <p>
     * Because the target server often drops the connection or returns no data when receiving
     * these control commands, this method intentionally uses short timeouts and suppresses
     * the resulting {@link IOException}.
     * </p>
     *
     * @param targetUrl The full URL string to send the GET request to.
     */
    public void setAtribute(String targetUrl) {
        HttpURLConnection connection = null;

        try {
            // 1. Open a basic connection to the URL
            URL url = new URL(targetUrl);
            connection = (HttpURLConnection) url.openConnection();

            // 2. Set the disguise and connection parameters
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/120.0.0.0");
            connection.setConnectTimeout(1000); // Only wait 1 second to connect
            connection.setReadTimeout(1000);    // Don't wait around for a response

            // 3. Actually fire the request!
            connection.getResponseCode();
            System.out.println("Fired command to: " + targetUrl);

        } catch (IOException e) {
            // We expect an IOException here! The server often drops the connection
            // after receiving a command, so we fail silently.
        } finally {
            // 4. Clean up the connection
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * Fetches the current live telemetry data from the game's local API.
     * <p>
     * Connects to {@code http://127.0.0.1:8111/indicators}, reads the JSON response,
     * and maps it to a {@link Data} object using the Jackson {@link ObjectMapper}.
     * </p>
     *
     * @return A populated {@link Data} object if the request is successful; {@code null} otherwise.
     */
    public Data getTelemetry() {
        HttpURLConnection connection = null;

        try {
            // 1. Setup the connection to the indicators endpoint
            URL url = new URL("http://127.0.0.1:8111/indicators");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/120.0.0.0");

            // Setting a reasonable timeout so the UI doesn't freeze if the game is closed
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);

            // 2. If the server responds with HTTP 200 (OK), parse the JSON
            if (connection.getResponseCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(connection.getInputStream(), Data.class);
            }

        } catch (IOException e) {
            // Fails cleanly if the game is not running or the web map is disabled
            System.out.println("Data couldn't be loaded (Game might be closed or API unreachable).");
        } finally {
            // 3. Clean up the connection
            if (connection != null) {
                connection.disconnect();
            }
        }

        return null;
    }
}