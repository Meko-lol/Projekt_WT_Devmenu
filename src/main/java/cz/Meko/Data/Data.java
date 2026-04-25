package cz.Meko.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// This prevents the app from crashing if the JSON payload adds new fields later
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    public static final String[] VARIABLE_NAMES = {
            "valid", "army", "type", "speed",
            "pedals", "pedals1", "pedals2", "pedals3", "pedals4", "pedals5",
            "stickElevator", "stickAilerons", "vario",
            "altitudeHour", "altitudeMin", "altitude10k", "altitude1Hour", "altitude1Min", "altitude110k",
            "aviahorizonRoll", "aviahorizonPitch", "aviahorizonRoll1", "aviahorizonPitch1",
            "bank", "compass", "compass1", "compass2",
            "clockHour", "clockMin", "clockSec",
            "rpmMin", "rpmHour", "waterTemperature",
            "fuel", "fuel1",
            "airbrakeLever", "gears",
            "gearLampDown", "gearLampUp", "gearLampOff",
            "flaps", "flaps1", "trimmer", "throttle",
            "weapon2", "weapon4",
            "flapsIndicator", "flapsIndicator1",
            "mach", "mach1",
            "gMeter", "gMeterMin", "gMeterMax",
            "aoa",
            "blister1", "blister2", "blister3", "blister4", "blister5", "blister11"
    };

    private boolean valid;
    private String army;
    private String type;
    private double speed;

    private double pedals;
    private double pedals1;
    private double pedals2;
    private double pedals3;
    private double pedals4;
    private double pedals5;

    @JsonProperty("stick_elevator")
    private double stickElevator;

    @JsonProperty("stick_ailerons")
    private double stickAilerons;

    private double vario;

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

    @JsonProperty("rpm_min")
    private int rpmMin;

    @JsonProperty("rpm_hour")
    private double rpmHour;

    @JsonProperty("water_temperature")
    private double waterTemperature;

    private int fuel;
    private int fuel1;

    @JsonProperty("airbrake_lever")
    private double airbrakeLever;

    private double gears;

    @JsonProperty("gear_lamp_down")
    private int gearLampDown;

    @JsonProperty("gear_lamp_up")
    private int gearLampUp;

    @JsonProperty("gear_lamp_off")
    private int gearLampOff;

    private double flaps;
    private double flaps1;
    private double trimmer;
    private double throttle;

    private int weapon2;
    private int weapon4;

    @JsonProperty("flaps_indicator")
    private double flapsIndicator;

    @JsonProperty("flaps_indicator1")
    private double flapsIndicator1;

    private double mach;
    private double mach1;

    @JsonProperty("g_meter")
    private double gMeter;

    @JsonProperty("g_meter_min")
    private double gMeterMin;

    @JsonProperty("g_meter_max")
    private double gMeterMax;

    private double aoa;

    private double blister1;
    private double blister2;
    private double blister3;
    private double blister4;
    private double blister5;
    private double blister11;

    // --- GETTERS ---

    public boolean isValid() { return valid; }
    public String getArmy() { return army; }
    public String getType() { return type; }
    public double getSpeed() { return speed; }

    public double getPedals() { return pedals; }
    public double getPedals1() { return pedals1; }
    public double getPedals2() { return pedals2; }
    public double getPedals3() { return pedals3; }
    public double getPedals4() { return pedals4; }
    public double getPedals5() { return pedals5; }

    public double getStickElevator() { return stickElevator; }
    public double getStickAilerons() { return stickAilerons; }

    public double getVario() { return vario; }

    public double getAltitudeHour() { return altitudeHour; }
    public double getAltitudeMin() { return altitudeMin; }
    public double getAltitude10k() { return altitude10k; }
    public double getAltitude1Hour() { return altitude1Hour; }
    public double getAltitude1Min() { return altitude1Min; }
    public double getAltitude110k() { return altitude110k; }

    public double getAviahorizonRoll() { return aviahorizonRoll; }
    public double getAviahorizonPitch() { return aviahorizonPitch; }
    public double getAviahorizonRoll1() { return aviahorizonRoll1; }
    public double getAviahorizonPitch1() { return aviahorizonPitch1; }

    public double getBank() { return bank; }

    public double getCompass() { return compass; }
    public double getCompass1() { return compass1; }
    public double getCompass2() { return compass2; }

    public double getClockHour() { return clockHour; }
    public int getClockMin() { return clockMin; }
    public int getClockSec() { return clockSec; }

    public int getRpmMin() { return rpmMin; }
    public double getRpmHour() { return rpmHour; }

    public double getWaterTemperature() { return waterTemperature; }

    public int getFuel() { return fuel; }
    public int getFuel1() { return fuel1; }

    public double getAirbrakeLever() { return airbrakeLever; }
    public double getGears() { return gears; }

    public int getGearLampDown() { return gearLampDown; }
    public int getGearLampUp() { return gearLampUp; }
    public int getGearLampOff() { return gearLampOff; }

    public double getFlaps() { return flaps; }
    public double getFlaps1() { return flaps1; }
    public double getTrimmer() { return trimmer; }
    public double getThrottle() { return throttle; }

    public int getWeapon2() { return weapon2; }
    public int getWeapon4() { return weapon4; }

    public double getFlapsIndicator() { return flapsIndicator; }
    public double getFlapsIndicator1() { return flapsIndicator1; }

    public double getMach() { return mach; }
    public double getMach1() { return mach1; }

    public double getgMeter() { return gMeter; }
    public double getgMeterMin() { return gMeterMin; }
    public double getgMeterMax() { return gMeterMax; }

    public double getAoa() { return aoa; }

    public double getBlister1() { return blister1; }
    public double getBlister2() { return blister2; }
    public double getBlister3() { return blister3; }
    public double getBlister4() { return blister4; }
    public double getBlister5() { return blister5; }
    public double getBlister11() { return blister11; }

    public String[] getCurrentValuesAsArray() {
        return new String[]{
                String.valueOf(this.valid),
                this.army != null ? this.army : "N/A",
                this.type != null ? this.type : "N/A",
                String.valueOf(this.speed),

                String.valueOf(this.pedals),
                String.valueOf(this.pedals1),
                String.valueOf(this.pedals2),
                String.valueOf(this.pedals3),
                String.valueOf(this.pedals4),
                String.valueOf(this.pedals5),

                String.valueOf(this.stickElevator),
                String.valueOf(this.stickAilerons),
                String.valueOf(this.vario),

                String.valueOf(this.altitudeHour),
                String.valueOf(this.altitudeMin),
                String.valueOf(this.altitude10k),
                String.valueOf(this.altitude1Hour),
                String.valueOf(this.altitude1Min),
                String.valueOf(this.altitude110k),

                String.valueOf(this.aviahorizonRoll),
                String.valueOf(this.aviahorizonPitch),
                String.valueOf(this.aviahorizonRoll1),
                String.valueOf(this.aviahorizonPitch1),

                String.valueOf(this.bank),
                String.valueOf(this.compass),
                String.valueOf(this.compass1),
                String.valueOf(this.compass2),

                String.valueOf(this.clockHour),
                String.valueOf(this.clockMin),
                String.valueOf(this.clockSec),

                String.valueOf(this.rpmMin),
                String.valueOf(this.rpmHour),
                String.valueOf(this.waterTemperature),

                String.valueOf(this.fuel),
                String.valueOf(this.fuel1),

                String.valueOf(this.airbrakeLever),
                String.valueOf(this.gears),

                String.valueOf(this.gearLampDown),
                String.valueOf(this.gearLampUp),
                String.valueOf(this.gearLampOff),

                String.valueOf(this.flaps),
                String.valueOf(this.flaps1),
                String.valueOf(this.trimmer),
                String.valueOf(this.throttle),

                String.valueOf(this.weapon2),
                String.valueOf(this.weapon4),

                String.valueOf(this.flapsIndicator),
                String.valueOf(this.flapsIndicator1),

                String.valueOf(this.mach),
                String.valueOf(this.mach1),

                String.valueOf(this.gMeter),
                String.valueOf(this.gMeterMin),
                String.valueOf(this.gMeterMax),

                String.valueOf(this.aoa),

                String.valueOf(this.blister1),
                String.valueOf(this.blister2),
                String.valueOf(this.blister3),
                String.valueOf(this.blister4),
                String.valueOf(this.blister5),
                String.valueOf(this.blister11)
        };
    }
}