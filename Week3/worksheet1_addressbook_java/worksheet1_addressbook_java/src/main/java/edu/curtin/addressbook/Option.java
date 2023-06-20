package edu.curtin.addressbook;

// Interface for options
public abstract class Option {
    public abstract String doOption(String s);

    public boolean requiresText() {
        return true;
    }
}
