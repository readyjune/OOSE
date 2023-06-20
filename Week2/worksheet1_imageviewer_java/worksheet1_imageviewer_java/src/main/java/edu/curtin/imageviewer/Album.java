
package edu.curtin.imageviewer;

import java.util.*;

/**
 * Represents an album of images.
 */
public class Album {
    // FIXME: Insert your fields, constructors and methods here.
    private ArrayList<ImageRecord> album;
    private int index = 0;

    public Album() {
        album = new ArrayList<>();
    }

    public ImageRecord getRecord() {
        return album.get(index);
    }

    public void next() {
        if (index < album.size() - 1) {
            index++;
        }
    }

    public void prev() {
        if (index > 0) {
            index--;
        }
    }

    public void addRecord(ImageRecord record) {
        album.add(record);
    }
}
