public class SecuritySystem implements SensorObserver {
    private MotionSensor motionSensor;
    private HeatSensor heatSensor;
    private Alarm alarm;
    private boolean armed;

    public SecuritySystem(MotionSensor inMotionSensor, HeatSensor inHeatSensor, Alarm inAlarm, boolean inArmed) {
        motionSensor = inMotionSensor;
        heatSensor = inHeatSensor;
        alarm = inAlarm;
        armed = inArmed;
        motionSensor.addSensorObserver(this);
        heatSensor.addSensorObserver(this);
    }

    public void setArmed(boolean newArmed) {
        armed = newArmed;
        EmailSystem.sendMessage("Armed: " + newArmed);
    }

    @Override
    public void sensorDetection(Sensor s) {
        if (armed) {
            alarm.ring();
            EmailSystem.sendMessage("Sensor detection for " +
                    s.toString());
        }
    }
}
