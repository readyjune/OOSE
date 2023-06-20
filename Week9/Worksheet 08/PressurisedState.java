public class PressurisedState implements State {
    public void pressurise(Airlock2 airlock) {

    }

    public void depressurise(Airlock2 airlock) {
        airlock.setState(new DepressurisedState());
    }

    public void openInnerDoor(Airlock2 airlock, Door door) {
        door.open();
    }

    public void openOuterDoor(Airlock2 airlock, Door door) {

    }

}