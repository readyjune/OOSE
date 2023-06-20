public class DepressurisedState implements State {
    public void pressurise(Airlock2 airlock, Pump pump) {
        pump.beginReturn();
        airlock.setState(new PressurisedState());
    }

    public void depressurise(Airlock2 airlock) {
    }

    public void openInnerDoor(Airlock2 airlock, Door door) {

    }

    public void openOuterDoor(Airlock2 airlock, Door door) {
        door.open();
    }

}