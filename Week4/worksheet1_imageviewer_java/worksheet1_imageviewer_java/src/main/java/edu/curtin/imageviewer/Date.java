package edu.curtin.imageviewer;

public class Date extends Label {

    String date;

    public Date(ImageRecord next, String date) {
        super(next);
        this.date = date;
    }

    @Override
    public String getCaption() {
        return next.getCaption() + " Date: " + date;
    }

    @Override
    public String getFilename() {
        return next.getFilename();
    }
}
