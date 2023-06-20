package edu.curtin.imageviewer;

public class Rating extends Label {

    int rating;

    public Rating(ImageRecord next, int rating) {
        super(next);
        this.rating = rating;
    }

    @Override
    public String getCaption() {
        return next.getCaption() + " Rating: " + String.valueOf(rating);
    }

    @Override
    public String getFilename() {
        return next.getFilename();
    }
}
