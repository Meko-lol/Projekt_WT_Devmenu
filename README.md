# War Thunder Telemetry Dashboard

A modern, Java-based desktop application designed to interface with the local War Thunder web API. This tool allows users to monitor live flight/tank telemetry in a sleek, customizable dashboard and manually override game variables in real-time.

## Features

* **Live Telemetry Monitoring:** Fetch and display real-time data from the game (speed, altitude, engine temps, control surfaces, etc.) polling at 500ms intervals.
* **Variable Filtering:** A dedicated settings menu allows you to toggle the visibility of over 40 different telemetry variables so you only see what matters.
* **Live Variable Injection:** Manually override specific game variables (e.g., Altitude, Velocity, Mass, TimeSpeed) via direct API commands to the game engine.
* **Modern UI:** A fully custom, dark-themed Java Swing GUI featuring:
    * Anti-aliased typography.
    * Seamless rounded corners and custom borders.
    * Minimalist, custom-painted scrollbars.
    * Interactive hover and click states for buttons.

---

## Prerequisites

To run and compile this project, you will need:

1.  **Java Development Kit (JDK):** Version 11 or higher recommended.
2.  **Dependencies:** 
    * **Jackson:** For parsing the JSON telemetry data.
        * `com.fasterxml.jackson.core:jackson-databind`
        * `com.fasterxml.jackson.core:jackson-annotations`
    * **Lombok:** To reduce boilerplate code (used in `Status.java`).
3.  **War Thunder Local Web Interface:** The game must be running, and the local browser map must be accessible. The application connects to `http://127.0.0.1:8111`.

---

## Project Structure

The codebase is cleanly divided into data management and graphical interfaces.

### `cz.Meko.Data` (Backend & State)
* **`Data.java`**: The core data model mapping the JSON response from the game's `/indicators` endpoint to Java fields.
* **`WTIO.java`**: Handles all network I/O, including fetching live telemetry and firing variable-override GET requests.
* **`Status.java`**: The heart of the application. It acts as a global state manager (holding current data and visibility preferences) and serves as the **UI Theme Engine**, providing reusable methods to style Swing components.
* **`UpdateTelemetry.java`**: A background thread that continuously polls the API to keep the UI refreshed without freezing the main thread.

### `cz.Meko.Windows` (Frontend)
* **`MainWindow.java`**: The main entry point and navigation menu.
* **`TelemetryWindow.java`**: The primary dashboard displaying live, synchronized lists of variable names and their current values.
* **`TelemetrySettingsWindow.java`**: A configuration panel with checkboxes to hide or show specific telemetry data points.
* **`SetVariablesWindow.java`**: A control panel featuring text inputs and action buttons to push new values (like Altitude or Speed) directly into the game environment.

---

## How to Run

1. Build the project using your preferred build tool (Maven/Gradle) ensuring Jackson and Lombok are included.
2. Launch War Thunder and load into a test drive or custom battle.
3. Run the application via your main method (which should call `Status.openWindow()` or instantiate `MainWindow`).
4. Click **Show Telemetry** to start the live feed, or **Set Variables** to interact with the game engine.

---

## Important Notes

* **API Timeouts:** The `WTIO` class intentionally suppresses `IOExceptions` when setting variables, as the War Thunder API often drops connections upon receiving commands. This is expected behavior.
* **Anti-Cheat:** This tool utilizes the official, locally hosted web API provided by Gaijin (`localhost:8111`). It is generally safe for monitoring, but modifying variables (`/editor/fm_commands`) only works in environments where the game permits it (e.g., Test Drives, custom local environments).
