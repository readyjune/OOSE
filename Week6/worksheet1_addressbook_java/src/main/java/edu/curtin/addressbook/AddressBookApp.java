package edu.curtin.addressbook;

import java.io.*;
import java.util.*;

import edu.curtin.addressbook.controller.FileIO;
import edu.curtin.addressbook.model.AddressBook;
import edu.curtin.addressbook.view.Menu;

/**
 * A simple address book application.
 * 
 * @author Dave and ...
 */
public class AddressBookApp {

    public static void main(String[] args) {
        String fileName;
        try (Scanner input = new Scanner(System.in)) {
            Menu menu = new Menu();
            FileIO io = new FileIO();
            AddressBook aba = new AddressBook();

            System.out.print("Enter address book filename: ");
            fileName = input.nextLine();

            try {
                aba = io.readAddressBook(fileName);
                menu.showMenu(aba);
            } catch (IOException e) {
                System.out.println("Could not read from " + fileName + ": " + e.getMessage());
            }
        }
    }
}
