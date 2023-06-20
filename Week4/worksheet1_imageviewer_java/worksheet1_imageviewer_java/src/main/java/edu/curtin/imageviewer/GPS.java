package edu.curtin.imageviewer;

public class GPS extends Label {

    int lati, longi, elev;

    public GPS(ImageRecord next, int lati, int longi, int elev) {
        super(next);
        this.lati = lati;
        this.longi = longi;
        this.elev = elev;
    }

    @Override
    public String getCaption() {
        return next.getCaption() + " Lat: " + lati + " Long: " + longi + " Ele: " + elev;
    }

    @Override
    public String getFilename() {
        return next.getFilename();
    }
}
