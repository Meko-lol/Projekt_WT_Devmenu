package cz.Meko.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Data {

    public static final String[] VARIABLE_NAMES = {
            "Valid", "Army", "Type", "Speed", "RPM Min", "RPM Hour",

            // Tank Specific
            "Gear", "Gear Neutral", "Cruise Control", "Stabilizer",
            "First Stage Ammo", "Crew Total", "Crew Current", "Crew Distance",
            "Gunner State", "Driver State", "LWS", "IRCM",

            // Pedals
            "Pedals", "Pedals1", "Pedals2", "Pedals3", "Pedals4", "Pedals5", "Pedals6", "Pedals7",

            // Stick & Vario
            "Stick Elevator", "Stick Ailerons", "Stick Ailerons1", "Vario",

            // Altitude
            "Alt Hour", "Alt Min", "Alt 10k", "Alt1 Hour", "Alt1 Min", "Alt1 10k",

            // Horizon
            "Horizon Roll", "Horizon Pitch", "Horizon Roll1", "Horizon Pitch1",

            // Navigation & Time
            "Bank", "Compass", "Compass1", "Compass2",
            "Clock Hour", "Clock Min", "Clock Sec",

            // Engines & Temps
            "Water Temp", "Oil Temp", "Fuel", "Fuel1",
            "Throttle", "Mach", "Mach1",

            // G-Meter & AoA
            "G Meter", "G Min", "G Max", "AoA",

            // Mechanical & Surfaces
            "Airbrake", "Gears Dev", "Gear Lamp Down", "Gear Lamp Up", "Gear Lamp Off",
            "Flaps", "Flaps1", "Flaps Ind", "Flaps Ind1", "Trimmer",

            // Weapons
            "Weapon2", "Weapon4",

            // Blisters
            "Blister1", "Blister2", "Blister3", "Blister4", "Blister5", "Blister7", "Blister8", "Blister11"
    };

    private boolean valid;
    private String army;
    private String type;
    private double speed;

    @JsonProperty("rpm_min")
    private double rpmMin;
    @JsonProperty("rpm_hour")
    private double rpmHour;

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

    // --- Mechanical & Flaps ---
    @JsonProperty("airbrake_lever")
    private double airbrakeLever;
    private double gears;

    @JsonProperty("gear_lamp_down")
    private double gearLampDown;
    @JsonProperty("gear_lamp_up")
    private double gearLampUp;
    @JsonProperty("gear_lamp_off")
    private double gearLampOff;

    private double flaps;
    private double flaps1;

    @JsonProperty("flaps_indicator")
    private double flapsIndicator;
    @JsonProperty("flaps_indicator1")
    private double flapsIndicator1;

    private double trimmer;

    // --- Weapons ---
    private double weapon2;
    private double weapon4;

    // --- Blisters ---
    private double blister1;
    private double blister2;
    private double blister3;
    private double blister4;
    private double blister5;
    private double blister7;
    private double blister8;
    private double blister11;


    @Override
    public String toString() {
        return "Data{" +
                "valid=" + valid +
                ", army='" + army + '\'' +
                ", type='" + type + '\'' +
                ", speed=" + speed +
                ", rpmMin=" + rpmMin +
                ", rpmHour=" + rpmHour +
                ", gear=" + gear +
                ", gearNeutral=" + gearNeutral +
                ", cruiseControl=" + cruiseControl +
                ", stabilizer=" + stabilizer +
                ", firstStageAmmo=" + firstStageAmmo +
                ", crewTotal=" + crewTotal +
                ", crewCurrent=" + crewCurrent +
                ", crewDistance=" + crewDistance +
                ", gunnerState=" + gunnerState +
                ", driverState=" + driverState +
                ", lws=" + lws +
                ", ircm=" + ircm +
                ", pedals=" + pedals +
                ", pedals1=" + pedals1 +
                ", pedals2=" + pedals2 +
                ", pedals3=" + pedals3 +
                ", pedals4=" + pedals4 +
                ", pedals5=" + pedals5 +
                ", pedals6=" + pedals6 +
                ", pedals7=" + pedals7 +
                ", stickElevator=" + stickElevator +
                ", stickAilerons=" + stickAilerons +
                ", stickAilerons1=" + stickAilerons1 +
                ", vario=" + vario +
                ", altitudeHour=" + altitudeHour +
                ", altitudeMin=" + altitudeMin +
                ", altitude10k=" + altitude10k +
                ", altitude1Hour=" + altitude1Hour +
                ", altitude1Min=" + altitude1Min +
                ", altitude110k=" + altitude110k +
                ", aviahorizonRoll=" + aviahorizonRoll +
                ", aviahorizonPitch=" + aviahorizonPitch +
                ", aviahorizonRoll1=" + aviahorizonRoll1 +
                ", aviahorizonPitch1=" + aviahorizonPitch1 +
                ", bank=" + bank +
                ", compass=" + compass +
                ", compass1=" + compass1 +
                ", compass2=" + compass2 +
                ", clockHour=" + clockHour +
                ", clockMin=" + clockMin +
                ", clockSec=" + clockSec +
                ", waterTemperature=" + waterTemperature +
                ", oilTemperature=" + oilTemperature +
                ", fuel=" + fuel +
                ", fuel1=" + fuel1 +
                ", throttle=" + throttle +
                ", mach=" + mach +
                ", mach1=" + mach1 +
                ", gMeter=" + gMeter +
                ", gMeterMin=" + gMeterMin +
                ", gMeterMax=" + gMeterMax +
                ", aoa=" + aoa +
                ", airbrakeLever=" + airbrakeLever +
                ", gears=" + gears +
                ", gearLampDown=" + gearLampDown +
                ", gearLampUp=" + gearLampUp +
                ", gearLampOff=" + gearLampOff +
                ", flaps=" + flaps +
                ", flaps1=" + flaps1 +
                ", flapsIndicator=" + flapsIndicator +
                ", flapsIndicator1=" + flapsIndicator1 +
                ", trimmer=" + trimmer +
                ", weapon2=" + weapon2 +
                ", weapon4=" + weapon4 +
                ", blister1=" + blister1 +
                ", blister2=" + blister2 +
                ", blister3=" + blister3 +
                ", blister4=" + blister4 +
                ", blister5=" + blister5 +
                ", blister7=" + blister7 +
                ", blister8=" + blister8 +
                ", blister11=" + blister11 +
                '}';
    }

    public String[] getCurrentValuesAsArray() {
        return new String[]{
                String.valueOf(valid),
                army != null ? army : "N/A",
                type != null ? type : "N/A",
                String.valueOf(speed),
                String.valueOf(rpmMin),
                String.valueOf(rpmHour),

                // Tank
                String.valueOf(gear), String.valueOf(gearNeutral), String.valueOf(cruiseControl),
                String.valueOf(stabilizer), String.valueOf(firstStageAmmo), String.valueOf(crewTotal),
                String.valueOf(crewCurrent), String.valueOf(crewDistance), String.valueOf(gunnerState),
                String.valueOf(driverState), String.valueOf(lws), String.valueOf(ircm),

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

                // Navigation & Time
                String.valueOf(bank), String.valueOf(compass), String.valueOf(compass1), String.valueOf(compass2),
                String.valueOf(clockHour), String.valueOf(clockMin), String.valueOf(clockSec),

                // Engines & Temps
                String.valueOf(waterTemperature), String.valueOf(oilTemperature),
                String.valueOf(fuel), String.valueOf(fuel1),
                String.valueOf(throttle), String.valueOf(mach), String.valueOf(mach1),

                // G-Meter & AoA
                String.valueOf(gMeter), String.valueOf(gMeterMin), String.valueOf(gMeterMax), String.valueOf(aoa),

                // Mechanical
                String.valueOf(airbrakeLever), String.valueOf(gears),
                String.valueOf(gearLampDown), String.valueOf(gearLampUp), String.valueOf(gearLampOff),
                String.valueOf(flaps), String.valueOf(flaps1),
                String.valueOf(flapsIndicator), String.valueOf(flapsIndicator1), String.valueOf(trimmer),

                // Weapons
                String.valueOf(weapon2), String.valueOf(weapon4),

                // Blisters
                String.valueOf(blister1), String.valueOf(blister2), String.valueOf(blister3),
                String.valueOf(blister4), String.valueOf(blister5), String.valueOf(blister7),
                String.valueOf(blister8), String.valueOf(blister11)
        };
    }
}