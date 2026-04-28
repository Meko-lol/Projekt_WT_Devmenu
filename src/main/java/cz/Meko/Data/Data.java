package cz.Meko.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    public static final String[] VARIABLE_NAMES = {
            "Valid", "Army", "Type", "Speed", "RPM",
            "Gear", "Gear Neutral", "Cruise Control", "Stabilizer",
            "First Stage Ammo", "Crew Total", "Crew Current", "Crew Distance",
            "Gunner State", "Driver State", "LWS", "IRCM",
            "Pedals", "Pedals1", "Pedals2", "Pedals3", "Pedals4", "Pedals5", "Pedals6", "Pedals7",
            "Stick Elevator", "Stick Ailerons", "Stick Ailerons1", "Vario",
            "Alt Hour", "Alt Min", "Alt 10k", "Alt1 Hour", "Alt1 Min", "Alt1 10k",
            "Horizon Roll", "Horizon Pitch", "Horizon Roll1", "Horizon Pitch1",
            "Bank", "Compass", "Compass1", "Compass2",
            "Clock Hour", "Clock Min", "Clock Sec",
            "Water Temp", "Oil Temp", "Fuel", "Fuel1",
            "Throttle", "Mach", "Mach1", "G Meter", "G Min", "G Max", "AoA",
            "Airbrake", "Gears Dev", "Flaps", "Flaps1", "Trimmer",
            "Blister1", "Blister2", "Blister3", "Blister4", "Blister5", "Blister7", "Blister8", "Blister11"
    };

    private boolean valid;
    private String army;
    private String type;
    private double speed;
    private double rpm;

    // --- Tank Specific ---
    private double gear;
    @JsonProperty("gear_neutral")
    private double gearNeutral;
    @JsonProperty("cruise_control")
    private double cruiseControl;
    private double stabilizer;
    @JsonProperty("first_stage_ammo")
    private double firstStageAmmo;
    @JsonProperty("crew_total")
    private double crewTotal;
    @JsonProperty("crew_current")
    private double crewCurrent;
    @JsonProperty("crew_distance")
    private double crewDistance;
    @JsonProperty("gunner_state")
    private double gunnerState;
    @JsonProperty("driver_state")
    private double driverState;
    private double lws;
    private double ircm;

    // --- Controls ---
    private double pedals;
    private double pedals1;
    private double pedals2;
    private double pedals3;
    private double pedals4;
    private double pedals5;
    private double pedals6;
    private double pedals7;

    @JsonProperty("stick_elevator")
    private double stickElevator;
    @JsonProperty("stick_ailerons")
    private double stickAilerons;
    @JsonProperty("stick_ailerons1")
    private double stickAilerons1;

    private double vario;

    // --- Flight Instruments ---
    @JsonProperty("altitude_hour")
    private double altitudeHour;
    @JsonProperty("altitude_min")
    private double altitudeMin;
    @JsonProperty("altitude_10k")
    private double altitude10k;
    @JsonProperty("altitude1_hour")
    private double altitude1Hour;
    @JsonProperty("altitude1_min")
    private double altitude1Min;
    @JsonProperty("altitude1_10k")
    private double altitude110k;

    @JsonProperty("aviahorizon_roll")
    private double aviahorizonRoll;
    @JsonProperty("aviahorizon_pitch")
    private double aviahorizonPitch;
    @JsonProperty("aviahorizon_roll1")
    private double aviahorizonRoll1;
    @JsonProperty("aviahorizon_pitch1")
    private double aviahorizonPitch1;

    private double bank;
    private double compass;
    private double compass1;
    private double compass2;

    @JsonProperty("clock_hour")
    private double clockHour;
    @JsonProperty("clock_min")
    private int clockMin;
    @JsonProperty("clock_sec")
    private int clockSec;

    @JsonProperty("water_temperature")
    private double waterTemperature;
    @JsonProperty("oil_temperature")
    private double oilTemperature;

    private double fuel;
    private double fuel1;
    private double throttle;
    private double mach;
    private double mach1;

    @JsonProperty("g_meter")
    private double gMeter;
    @JsonProperty("g_meter_min")
    private double gMeterMin;
    @JsonProperty("g_meter_max")
    private double gMeterMax;

    private double aoa;

    @JsonProperty("airbrake_lever")
    private double airbrakeLever;
    private double gears;
    private double flaps;
    private double flaps1;
    private double trimmer;

    // --- Blisters ---
    private double blister1;
    private double blister2;
    private double blister3;
    private double blister4;
    private double blister5;
    private double blister7;
    private double blister8;
    private double blister11;

    // --- Logic for UI List ---

    public String[] getCurrentValuesAsArray() {
        return new String[]{
                String.valueOf(valid),
                army != null ? army : "N/A",
                type != null ? type : "N/A",
                String.valueOf(speed),
                String.valueOf(rpm),

                // Tank
                String.valueOf(gear),
                String.valueOf(gearNeutral),
                String.valueOf(cruiseControl),
                String.valueOf(stabilizer),
                String.valueOf(firstStageAmmo),
                String.valueOf(crewTotal),
                String.valueOf(crewCurrent),
                String.valueOf(crewDistance),
                String.valueOf(gunnerState),
                String.valueOf(driverState),
                String.valueOf(lws),
                String.valueOf(ircm),

                // Pedals
                String.valueOf(pedals), String.valueOf(pedals1), String.valueOf(pedals2),
                String.valueOf(pedals3), String.valueOf(pedals4), String.valueOf(pedals5),
                String.valueOf(pedals6), String.valueOf(pedals7),

                // Controls
                String.valueOf(stickElevator), String.valueOf(stickAilerons), String.valueOf(stickAilerons1),
                String.valueOf(vario),

                // Altitude
                String.valueOf(altitudeHour), String.valueOf(altitudeMin), String.valueOf(altitude10k),
                String.valueOf(altitude1Hour), String.valueOf(altitude1Min), String.valueOf(altitude110k),

                // Horizon
                String.valueOf(aviahorizonRoll), String.valueOf(aviahorizonPitch),
                String.valueOf(aviahorizonRoll1), String.valueOf(aviahorizonPitch1),

                // Navigation & Temp
                String.valueOf(bank), String.valueOf(compass), String.valueOf(compass1), String.valueOf(compass2),
                String.valueOf(clockHour), String.valueOf(clockMin), String.valueOf(clockSec),
                String.valueOf(waterTemperature), String.valueOf(oilTemperature),
                String.valueOf(fuel), String.valueOf(fuel1),
                String.valueOf(throttle), String.valueOf(mach), String.valueOf(mach1),
                String.valueOf(gMeter), String.valueOf(gMeterMin), String.valueOf(gMeterMax), String.valueOf(aoa),

                // Mechanical
                String.valueOf(airbrakeLever), String.valueOf(gears), String.valueOf(flaps),
                String.valueOf(flaps1), String.valueOf(trimmer),

                // Blisters
                String.valueOf(blister1), String.valueOf(blister2), String.valueOf(blister3),
                String.valueOf(blister4), String.valueOf(blister5), String.valueOf(blister7),
                String.valueOf(blister8), String.valueOf(blister11)
        };
    }
}