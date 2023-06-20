public interface State {
    public void pressurise(Airlock2 airlock);

    public void depressurise(Airlock2 airlock);

    public void openInnerDoor(Airlock2 airlock, Door door);

    public void openOuterDoor(Airlock2 airlock, Door door);

}