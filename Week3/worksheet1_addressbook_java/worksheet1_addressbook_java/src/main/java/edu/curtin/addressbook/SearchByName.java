package edu.curtin.addressbook;

// Name searching subclass of option
public class SearchByName extends Option {

    private AddressBook addressBook;

    public SearchByName(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    @Override
    public String doOption(String s) {
        String rString = addressBook.findAddress(s).toString();
        return rString;
    }
}
