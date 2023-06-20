import java.security.AllPermission;

public class App {

    public static void main(String[] args) {
        Hardware hw = new Hardware();
        SensorBundle sens = hw.getSensors();
        MotionSensor motionSensor = sens.getMotionSensor();
        HeatSensor heatSensor = sens.getHeatSensor();
        Alarm alarm = new Alarm();
        SecuritySystem system = new SecuritySystem(motionSensor, heatSensor, alarm, false);
    }
}
