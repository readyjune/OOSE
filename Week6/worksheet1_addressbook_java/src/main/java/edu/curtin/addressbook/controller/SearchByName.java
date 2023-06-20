package edu.curtin.addressbook.controller;

import edu.curtin.addressbook.model.AddressBook;

// Name searching subclass of option
public class SearchByName extends Option {

    private AddressBook addressBook;

    public SearchByName(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    @Override
    public String doOption(String s) {
        String rString = "No matches for your search...";
        if (addressBook.findAddress(s) != null) {
            rString = addressBook.findAddress(s).toString();
        }
        return rString;
    }
}
