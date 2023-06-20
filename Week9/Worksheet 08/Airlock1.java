public class Airlock1 {
    private double pressure;
    private boolean innerDoorOpen;
    private boolean outerDoorOpen;
    private boolean pressurised;
    private Sensor sensor;
    private Pump pump;
    private Door innerDoor;
    private Door outerDoor;

    public void pressurise() {
        pump.beginReturn();
        while (pressure <= 90) {
            pressure = sensor.getPressure();
        }
        pump.stop();
        pressurised = true;
    }

    public void depressurise() {
        pump.beginExtraction();
        while (pressure >= 5) {
            pressure = sensor.getPressure();
        }
        pump.stop();
        pressurised = false;
    }

    public void openInnerDoor() {
        if (!outerDoor.isOpen() && pressure > 90) {
            innerDoor.open();
            innerDoorOpen = true;
        }
    }

    public void openOuterDoor() {
        if (!innerDoor.isOpen() && pressure < 5) {
            outerDoor.open();
            outerDoorOpen = true;
        }
    }

    public void updatePressure(double pressure) {
        this.pressure = pressure;
    }
}