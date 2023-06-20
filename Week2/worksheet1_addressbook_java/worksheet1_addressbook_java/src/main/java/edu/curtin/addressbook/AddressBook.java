package edu.curtin.addressbook;

import java.util.*;

/**
 * Contains all the address book entries.
 * 
 * @author ...
 */
public class AddressBook {
    // Insert your code here.
    private HashMap<String, Entry> addresses;

    public AddressBook() {
        addresses = new HashMap<>();
    }

    public void addAddress(String name, Entry entry) {
        addresses.put(name, entry);
    }

    public Entry findAddress(String name) {
        return addresses.get(name);
    }

    public Entry findName(String email) {

        Entry foundEntry = null;

        for (Map.Entry<String, Entry> entry : addresses.entrySet()) {
            if (entry.getValue().findAddress(email) == true) {
                foundEntry = entry.getValue();
            }
        }

        return foundEntry;
    }
}
