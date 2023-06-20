package edu.curtin.addressbook;

// Email searching subclass of option
public class SearchByEmail extends Option {

    private AddressBook addressBook;

    public SearchByEmail(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    @Override
    public String doOption(String s) {
        String rString = addressBook.findName(s).toString();
        return rString;
    }
}
