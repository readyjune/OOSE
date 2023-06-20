package edu.curtin.imageviewer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.*;

/**
 * Main class representing the entry point (and controller) of the application.
 */
public class MainApplication extends Application {
    public static void main(String[] args) {
        Application.launch(args); // Run JavaFX
        // This will effectively do 'new MainApplication()' and then call 'start(...)'.
    }

    /**
     * Loads an image album and then creates a window to display it.
     */
    @Override
    public void start(Stage stage) {
        // Construct an album object.
        Album album = new Album();

        // Make a new window.
        MainWindow window = new MainWindow(album, stage);

        // Choose which album to load.
        File albumFile = window.chooseAlbumFile();

        if (albumFile == null) {
            Platform.exit(); // Otherwise JavaFX keeps the program open, doing nothing.
        } else {
            try {
                // Attempt to read an album file.
                readAlbumFile(albumFile, album);

                // Display the window.
                window.show();
            } catch (IOException e) {
                System.err.println("Error while reading " + albumFile);
                Platform.exit();
            }
        }
    }

    /**
     * Reads an album file, given a filename and an Album object. Returns true if
     * successful, or false if the file could not be read.
     *
     * @param albumFile The file storing the list of image filenames and their
     *                  captions.
     * @param album     An Album object to populate.
     * 
     * @throws IOException If the file could not be read.
     */
    private static void readAlbumFile(File albumFile, Album album) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(albumFile))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.trim().length() > 0) // Ignore blank lines
                {
                    String[] parts = line.split(":");

                    String imageFilename = albumFile.getParent() + File.separatorChar + parts[0];
                    String imageCaption = "";
                    if (parts.length >= 2) {
                        imageCaption = parts[1];
                    }

                    ImageRecord image = new Image(imageFilename, imageCaption);

                    for (int i = 2; i < parts.length; i++) {
                        if (parts[i].contains("rating")) {
                            image = new Rating(image, Integer.parseInt(String.valueOf(parts[i].charAt(7))));
                        } else if (parts[i].contains("date")) {
                            image = new Date(image, parts[i].substring(5));
                        } else if (parts[i].contains("gps")) {
                            int lati, longi, elev;
                            String[] coordSplit = parts[i].substring(4).split(" ");
                            lati = Integer.parseInt(coordSplit[0]);
                            longi = Integer.parseInt(coordSplit[1]);
                            elev = Integer.parseInt(coordSplit[2]);
                            image = new GPS(image, lati, longi, elev);

                        }
                    }

                    album.addRecord(image);

                }

                line = reader.readLine();
            }
        }
    }

}
