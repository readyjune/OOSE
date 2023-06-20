package edu.curtin.imageviewer;

public abstract class Label implements ImageRecord {
    protected ImageRecord next;

    public Label(ImageRecord next) {
        this.next = next;
    }

    @Override
    public String getCaption() {
        return next.getCaption();
    }
}
