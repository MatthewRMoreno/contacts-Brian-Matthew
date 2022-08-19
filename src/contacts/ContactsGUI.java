package contacts;

import utils.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContactsGUI {
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";
    public static final String RESET = "\033[0m";

    Contacts contact = new Contacts();
    Input input = new Input();
    public void entryPoint() {
        boolean exit = true;
        contact = ContactsGateway.readFromFile();
        String name = "";
        long phoneNumber = 0;
        System.out.println("""
                    
                               ___                                       ___                         ___        \s
                              (   )                                     (   )                       (   )       \s
                       .-..    | | .-.     .--.    ___ .-.     .--.      | |.-.     .--.     .--.    | |   ___  \s
                      /    \\   | |/   \\   /    \\  (   )   \\   /    \\     | /   \\   /    \\   /    \\   | |  (   ) \s
                     ' .-,  ;  |  .-. .  |  .-. ;  |  .-. .  |  .-. ;    |  .-. | |  .-. ; |  .-. ;  | |  ' /   \s
                     | |  . |  | |  | |  | |  | |  | |  | |  |  | | |    | |  | | | |  | | | |  | |  | |,' /    \s
                     | |  | |  | |  | |  | |  | |  | |  | |  |  |/  |    | |  | | | |  | | | |  | |  | .  '.    \s
                     | |  | |  | |  | |  | |  | |  | |  | |  |  ' _.'    | |  | | | |  | | | |  | |  | | `. \\   \s
                     | |  ' |  | |  | |  | '  | |  | |  | |  |  .'.-.    | '  | | | '  | | | '  | |  | |   \\ \\  \s
                     | `-'  '  | |  | |  '  `-' /  | |  | |  '  `-' /    ' `-' ;  '  `-' / '  `-' /  | |    \\ . \s
                     | \\__.'  (___)(___)  `.__.'  (___)(___)  `.__.'      `.__.    `.__.'   `.__.'  (___ ) (___)\s
                     | |                                                                                        \s
                    (___)                                                                                       \s
                    """);
        while (exit) {
            int choice = input.getInt("""
                    1. View contacts.
                    2. Add a new contact.
                    3. Search a contact by name.
                    4. Delete an existing contact.
                    5. Exit.
                    Enter an option (1, 2, 3, 4 or 5):""");
            switch (choice) {
                case 1 -> contactFormatter();
                case 2 -> addContact();
                case 3 -> searchContact();
                case 4 -> contact.deleteContact();
                case 5 -> { exitApp();
                    ContactsGateway.writeToFile(contact);
                    exit = false;
                }
            }
        }
    }
    public void contactFormatter() {
        Map<String, Long> unformattedContacts = new HashMap<>();
        unformattedContacts = contact.getContact();
        System.out.println("""                
                  _   _                 _                  \s
                 | \\ | |               | |                 \s
                 |  \\| |_   _ _ __ ___ | |__   ___ _ __ ___\s
                 | . ` | | | | '_ ` _ \\| '_ \\ / _ \\ '__/ __|
                 | |\\  | |_| | | | | | | |_) |  __/ |  \\__ \\
                 |_| \\_|\\__,_|_| |_| |_|_.__/ \\___|_|  |___/
                """);
        System.out.println("################################################################");
        for (Map.Entry<String, Long> contact : unformattedContacts.entrySet()) {
            String phoneNumber = Long.toString(contact.getValue());
            String formattedPhoneNumber =  "(" + phoneNumber.substring( 0,3 ) + ") " + phoneNumber.substring( 3,6 ) + "-" + phoneNumber.substring( 6,10 );
            System.out.format("Contact: %-30s | Number: %s\n", contact.getKey(), formattedPhoneNumber);
        }
        System.out.println("################################################################");
    }

    public void addContact() {
        String name= "";
            System.out.println("""
                                           _.===========================._
                                        .'`  .-  - __- - - -- --__--- -.  `'.
                                    __ / ,'`     _|--|_________|--|_     `'. \\
                                  /'--| ;    _.'\\ |  '         '  | /'._    ; |
                                 //   | |_.-' .-'.'    -  -- -    '.'-. '-._| |
                                (\\)   \\"` _.-` /                     \\ `-._ `"/
                                (\\)    `-`    /      .---------.      \\    `-`
                                (\\)           |      ||1||2||3||      |
                               (\\)            |      ||4||5||6||      |
                              (\\)             |      ||7||8||9||      |
                             (\\)           ___|      ||*||0||#||      |
                             (\\)          /.--|      '---------'      |
                              (\\)        (\\)  |\\_  _  __   _   __  __/|
                             (\\)        (\\)   |                       |
                            (\\)_._._.__(\\)    |                       |
                             (\\\\\\\\\\\\\\\\)        '.___________________.'
                              '-'-'-'--'""");
            name = input.getString("What is the contact's name?");
            for (String contactName : contact.getContact().keySet()) {
                if (contactName.toLowerCase().contains(name.toLowerCase())) {
                    boolean value = input.yesNo("There is a contact with that name already, would you like to overwrite it?");
                    if (!value) entryPoint();
                }
            }
            Long phoneNumber = input.getLong("What is the contact's phone number?");
            while (Long.toString(phoneNumber).length() != 10) phoneNumber = input.getLong("Please enter a 10 digit phone number without any special characters.");
            contact.setContact(name, phoneNumber);
    }

    public void searchContact() {
        String name;
            do  {
                name = input.getString("Who are you looking for?");
            } while (contact.getIndividualContact(name) == null);
            System.out.println("""                
                  _____            _             _  \s
                 / ____|          | |           | | \s
                | |     ___  _ __ | |_ __ _  ___| |_\s
                | |    / _ \\| '_ \\| __/ _` |/ __| __|
                | |___| (_) | | | | || (_| | (__| |_\s
                 \\_____\\___/|_| |_|\\__\\__,_|\\___|\\__|
                ################################################################
                """);
            System.out.println(contact.getIndividualContact(name));
            System.out.println("################################################################");
    }

    public void exitApp() {
            System.out.println(YELLOW_BOLD_BRIGHT + """
                                   ..---..
                                 .'  _    `.
                             __..'  (o)    :
                            `..__          ;
                                 `.       /
                                   ;      `..---...___
                                 .'                   `~-. .-')
                                .                         ' _.'
                               :                           :
                               \\                           '
                                +                         J
                                 `._                   _.'
                                    `~--....___...---~'                                                                                                                                  
                                    """ + RESET);
        System.out.println("""
                   ____    U  ___ u   U  ___ u  ____    ____   __   __U _____ u\s
                U /"___|u   \\/"_ \\/    \\/"_ \\/ |  _"\\U | __")u \\ \\ / /\\| ___"|/\s
                \\| |  _ /   | | | |    | | | |/| | | |\\|  _ \\/  \\ V /  |  _|"  \s
                 | |_| |.-,_| |_| |.-,_| |_| |U| |_| |\\| |_) | U_|"|_u | |___  \s
                  \\____| \\_)-\\___/  \\_)-\\___/  |____/ u|____/    |_|   |_____| \s
                  _)(|_       \\\\         \\\\     |||_  _|| \\\\_.-,//|(_  <<   >> \s
                 (__)__)     (__)       (__)   (__)_)(__) (__)\\_) (__)(__) (__)\s   """);
    }
}

