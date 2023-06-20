package edu.curtin.addressbook.view;

import java.util.*;

import edu.curtin.addressbook.controller.DisplayAll;
import edu.curtin.addressbook.controller.Option;
import edu.curtin.addressbook.controller.SearchByEmail;
import edu.curtin.addressbook.controller.SearchByName;
import edu.curtin.addressbook.model.AddressBook;

/**
 * A simple address book application.
 * 
 * @author Dave and ...
 */
public class Menu {

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
    public void showMenu(AddressBook addressBook) {
        String searchTerm;
        int intTerm;
        Option option;
        boolean done = false;
        try (Scanner input = new Scanner(System.in)) {
            // Initialize the options hashmap
            initOptions(addressBook);

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
}
