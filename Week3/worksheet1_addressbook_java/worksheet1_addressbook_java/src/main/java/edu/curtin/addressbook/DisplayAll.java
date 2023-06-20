package edu.curtin.addressbook;

import java.util.*;

// display all subclass of option
public class DisplayAll extends Option {

    private AddressBook addressBook;

    public DisplayAll(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    @Override
    public String doOption(String s) {
        String rString = "";

        // Loop through all the entries and add to string
        for (Map.Entry<String, Entry> entry : addressBook.getAddresses().entrySet()) {
            rString += entry.getValue() + "\n";
        }

        return rString;
    }

    @Override
    public boolean requiresText() {
        return false;
    }
}
