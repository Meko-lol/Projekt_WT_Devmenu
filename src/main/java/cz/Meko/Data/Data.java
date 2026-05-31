package cz.Meko.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Data {

    /**
     * Contains the displayed data.
     */
    public static final String[] VARIABLE_NAMES = {
            "Valid", "Army", "Type", "Speed",
            "Pedals1", "Pedals2", "Pedals3", "Pedals4",
            "Stick Elevator", "Stick Ailerons", "Vario",
            "Altitude Hour", "Altitude Min", "Altitude 10k", "Altitude1 10k",
            "Aviahorizon Roll", "Aviahorizon Pitch",
            "Compass", "Compass1",
            "Clock Hour", "Clock Min", "Clock Sec",
            "Oil Pressure", "Oil Pressure1", "Fuel",
            "Airbrake Lever", "Gears", "Gear Lamp Down", "Gear Lamp Up",
            "Flaps", "Trimmer", "Throttle", "Throttle1",
            "Weapon2", "Weapon3",
            "Flaps Indicator", "Flaps Indicator1", "Trimmer Indicator",
            "AoA",
    };

    private boolean valid;
    private String army;
    private String type;
    private double speed;

    private double pedals1;
    private double pedals2;
    private double pedals3;
    private double pedals4;

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
    @JsonProperty("altitude1_10k")
    private double altitude110k;

    @JsonProperty("aviahorizon_roll")
    private double aviahorizonRoll;
    @JsonProperty("aviahorizon_pitch")
    private double aviahorizonPitch;

    private double compass;
    private double compass1;

    @JsonProperty("clock_hour")
    private double clockHour;
    @JsonProperty("clock_min")
    private int clockMin;
    @JsonProperty("clock_sec")
    private int clockSec;

    @JsonProperty("oil_pressure")
    private double oilPressure;
    @JsonProperty("oil_pressure1")
    private double oilPressure1;

    private double fuel;

    @JsonProperty("airbrake_lever")
    private double airbrakeLever;

    private double gears;

    @JsonProperty("gear_lamp_down")
    private double gearLampDown;
    @JsonProperty("gear_lamp_up")
    private double gearLampUp;

    private double flaps;
    private double trimmer;
    private double throttle;
    private double throttle1;

    private double weapon2;
    private double weapon3;

    @JsonProperty("flaps_indicator")
    private double flapsIndicator;
    @JsonProperty("flaps_indicator1")
    private double flapsIndicator1;
    @JsonProperty("trimmer_indicator")
    private double trimmerIndicator;

    private double aoa;

    private double blister1;
    private double blister2;
    private double blister3;
    private double blister4;
    private double blister5;
    private double blister6;
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
                ", pedals1=" + pedals1 +
                ", pedals2=" + pedals2 +
                ", pedals3=" + pedals3 +
                ", pedals4=" + pedals4 +
                ", stickElevator=" + stickElevator +
                ", stickAilerons=" + stickAilerons +
                ", vario=" + vario +
                ", altitudeHour=" + altitudeHour +
                ", altitudeMin=" + altitudeMin +
                ", altitude10k=" + altitude10k +
                ", altitude110k=" + altitude110k +
                ", aviahorizonRoll=" + aviahorizonRoll +
                ", aviahorizonPitch=" + aviahorizonPitch +
                ", compass=" + compass +
                ", compass1=" + compass1 +
                ", clockHour=" + clockHour +
                ", clockMin=" + clockMin +
                ", clockSec=" + clockSec +
                ", oilPressure=" + oilPressure +
                ", oilPressure1=" + oilPressure1 +
                ", fuel=" + fuel +
                ", airbrakeLever=" + airbrakeLever +
                ", gears=" + gears +
                ", gearLampDown=" + gearLampDown +
                ", gearLampUp=" + gearLampUp +
                ", flaps=" + flaps +
                ", trimmer=" + trimmer +
                ", throttle=" + throttle +
                ", throttle1=" + throttle1 +
                ", weapon2=" + weapon2 +
                ", weapon3=" + weapon3 +
                ", flapsIndicator=" + flapsIndicator +
                ", flapsIndicator1=" + flapsIndicator1 +
                ", trimmerIndicator=" + trimmerIndicator +
                ", aoa=" + aoa +
                '}';
    }

    public String[] getCurrentValuesAsArray() {
        return new String[]{
                String.valueOf(valid),
                army != null ? army : "N/A",
                type != null ? type : "N/A",
                String.valueOf(speed),
                String.valueOf(pedals1), String.valueOf(pedals2), String.valueOf(pedals3), String.valueOf(pedals4),
                String.valueOf(stickElevator), String.valueOf(stickAilerons), String.valueOf(vario),
                String.valueOf(altitudeHour), String.valueOf(altitudeMin), String.valueOf(altitude10k), String.valueOf(altitude110k),
                String.valueOf(aviahorizonRoll), String.valueOf(aviahorizonPitch),
                String.valueOf(compass), String.valueOf(compass1),
                String.valueOf(clockHour), String.valueOf(clockMin), String.valueOf(clockSec),
                String.valueOf(oilPressure), String.valueOf(oilPressure1), String.valueOf(fuel),
                String.valueOf(airbrakeLever), String.valueOf(gears), String.valueOf(gearLampDown), String.valueOf(gearLampUp),
                String.valueOf(flaps), String.valueOf(trimmer), String.valueOf(throttle), String.valueOf(throttle1),
                String.valueOf(weapon2), String.valueOf(weapon3),
                String.valueOf(flapsIndicator), String.valueOf(flapsIndicator1), String.valueOf(trimmerIndicator),
                String.valueOf(aoa)
        };
    }
}