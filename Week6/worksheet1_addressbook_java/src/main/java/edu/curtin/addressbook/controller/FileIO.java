package edu.curtin.addressbook.controller;

import java.io.*;
import java.util.*;

import edu.curtin.addressbook.model.AddressBook;
import edu.curtin.addressbook.model.Entry;

/**
 * A simple address book application.
 * 
 * @author Dave and ...
 */
public class FileIO {

    /**
     * Read the address book file, containing all the names and email addresses.
     *
     * @param fileName The name of the address book file.
     * @return A new AddressBook object containing all the information.
     * @throws IOException If the file cannot be read.
     */
    public AddressBook readAddressBook(String fileName) throws IOException {
        AddressBook addressBook = new AddressBook();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(":");

                ArrayList<String> emailAddresses = new ArrayList<>();

                for (int i = 1; i < parts.length; i++) {
                    emailAddresses.add(parts[i]);
                }

                Entry entry = new Entry(parts[0], emailAddresses);

                addressBook.addAddress(parts[0], entry);

                line = reader.readLine();
            }
        }
        return addressBook;
    }
}
