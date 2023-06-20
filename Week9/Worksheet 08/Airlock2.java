public class Airlock2 {
    private double pressure;
    private Sensor sensor;
    private Pump pump;
    private Door innerDoor;
    private Door outerDoor;
    private State state;

    public setState(State state) {
        this.state = state;
    }

    public void pressurise() {
        state.pressurise(this);
    }

    public void depressurise() {
        state.depressurise(this);
    }

    public void openInnerDoor() {
        if (!outerDoor.isOpen) {
            state.openInnerDoor(this);
        }
    }

    public void openOuterDoor() {
        if (!innerDoor.isOpen) {
            state.openOuterDoor(this);
        }
    }

    public void updatePressure(double pressure) {
        this.pressure = pressure;
    }
}