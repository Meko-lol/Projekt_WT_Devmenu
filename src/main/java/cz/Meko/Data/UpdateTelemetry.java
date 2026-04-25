package cz.Meko.Data;

public class UpdateTelemetry extends Thread{
    public void run(int interval){
        while(Telemetry.isUpdatingTelemetry()){
            WTIO.updateTelemetry();
        }
    }
}
