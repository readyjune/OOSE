package edu.curtin.addressbook.controller;

import edu.curtin.addressbook.model.AddressBook;

// Email searching subclass of option
public class SearchByEmail extends Option {

    private AddressBook addressBook;

    public SearchByEmail(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    @Override
    public String doOption(String s) {
        String rString = "No Match for your search...";
        if (addressBook.findName(s) != null) {
            rString = addressBook.findName(s).toString();
        }
        return rString;
    }
}
