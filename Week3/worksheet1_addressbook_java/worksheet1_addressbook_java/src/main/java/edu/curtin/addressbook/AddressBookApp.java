package edu.curtin.addressbook;

import java.io.*;
import java.util.*;

/**
 * A simple address book application.
 * 
 * @author Dave and ...
 */
public class AddressBookApp {

    public static void main(String[] args) {
        String fileName;
        Scanner input = new Scanner(System.in);
        AddressBookApp aba = new AddressBookApp();

        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();

        try {
            aba.readAddressBook(fileName);
            aba.showMenu();
        } catch (IOException e) {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
    }

    /**
     * Read the address book file, containing all the names and email addresses.
     *
     * @param fileName The name of the address book file.
     * @return A new AddressBook object containing all the information.
     * @throws IOException If the file cannot be read.
     */
    private AddressBook readAddressBook(String fileName) throws IOException {
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
        // Initialize the options hashmap
        initOptions(addressBook);
        return addressBook;
    }

    // The hashmap storing the different options
    private Map<Integer, Option> options = new HashMap<>();

    // Setting up the hashmap to point to the correct option for each key
    public void initOptions(AddressBook addressBook) {
        options.put(1, new SearchByName(addressBook));
        options.put(2, new SearchByEmail(addressBook));
        options.put(3, new DisplayAll(addressBook));
    }

    // Add another option to the hashmap
    public void addOption(Integer i, Option option) {
        options.put(i, option);
    }

    /**
     * Show the main menu, offering the user options to (1) search entries by
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */
    private void showMenu() {
        String searchTerm;
        int intTerm;
        Option option;
        boolean done = false;
        Scanner input = new Scanner(System.in);

        while (!done) {
            System.out.println("(1) Search by name, (2) Search by email, (3) Display All, (4) Quit");

            try {
                intTerm = Integer.parseInt(input.nextLine());
                if (intTerm == 4) {
                    done = true;
                } else {
                    // Get the option mapped to the user selection
                    option = options.get(intTerm);
                    if (option != null) {
                        searchTerm = "";
                        // Checks if the selection requires a user input
                        if (option.requiresText()) {
                            System.out.print("Enter search term: ");
                            searchTerm = input.nextLine();
                        }
                        // At this level code does not know (or care) which version of doOption is
                        // running
                        System.out.println(option.doOption(searchTerm));
                    } else {
                        System.out.println("Enter a valid number");
                    }
                }

            } catch (NumberFormatException e) {
                // The user entered something non-numerical.
                System.out.println("Enter a number");
            }
        }
    }
}
