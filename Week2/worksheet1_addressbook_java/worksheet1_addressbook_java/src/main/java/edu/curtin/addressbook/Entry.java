package edu.curtin.addressbook;

import java.util.*;

/**
 * Represents a single address book entry.
 * 
 * @author ...
 */
public class Entry {
    // Insert your code here.
    private String name;
    private ArrayList<String> addresses;

    public Entry(String name, ArrayList<String> addresses) {
        this.name = name;
        this.addresses = addresses;
    }

    public String getName() {
        return name;
    }

    public String getAddress(int i) {
        return addresses.get(i);
    }

    public boolean findAddress(String email) {
        boolean found = false;
        for (String e : addresses) {
            if (e.equals(email)) {
                found = true;
            }
        }
        return found;
    }

    public String toString() {
        String rString = "";
        for (String address : addresses) {
            rString += ": " + address;
        }
        return name + " " + rString;
    }

}
