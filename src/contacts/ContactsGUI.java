package contacts;

import utils.Input;

public class ContactsGUI {
    Contacts contact = new Contacts();
    Input input = new Input();
    public void entryPoint() {
        boolean exit = true;
        contact = ContactsGateway.readFromFile();
        String name = "";
        long phoneNumber = 0;
        while (exit) {
            int choice = input.getInt("""
                    1. View contacts.
                    2. Add a new contact.
                    3. Search a contact by name.
                    4. Delete an existing contact.
                    5. Exit.
                    Enter an option (1, 2, 3, 4 or 5):""");
            switch (choice) {
                case 1 -> System.out.println(contact.getContact());
                case 2 -> {
                    name = input.getString("What is the contact's name?");
                    phoneNumber = input.getLong("What is the contact's phone number?");
                    contact.setContact(name, phoneNumber);
                }
                case 3 -> {
                    name = input.getString("Who are you looking for?");
                    if (contact.getIndividualContact(name) == null) {
                        name = input.getString("Who are you looking for?");
                    } else contact.getIndividualContact(name);
                }
                case 4 -> contact.deleteContact();
                case 5 -> {
                    ContactsGateway.writeToFile(contact);
                    exit = false;
                }
            }
        }
    }
}

