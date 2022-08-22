package contacts;

import utils.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contacts {
    private static final Map<String, Long> contact = new HashMap<>();
    Input input = new Input();

    public void setContact (String item, long amount) {
        contact.put(item, amount);
    }
    public Map<String, Long> getContact() {
        sortContacts();
        return contact;
    }

    public String getIndividualContact(String name) {
        for (Map.Entry<String, Long> key : contact.entrySet()) {
            if (key.getKey().toLowerCase().contains(name.toLowerCase())) {
                String phoneNumber = Long.toString(key.getValue());
                String formattedPhoneNumber =  "(" + phoneNumber.substring( 0,3 ) + ") " + phoneNumber.substring( 3,6 ) + "-" + phoneNumber.substring( 6,10 );
                return String.format("Contact: %-30s | Number: %s\n", key.getKey(), formattedPhoneNumber);
            }
        }
        return null;
    }

    public void sortContacts() {
        CollectionsHelper.HashMapSorter(contact);
    }
    public void deleteContact() {
        deleteContact("Who would you like to remove?");
    }
    public void deleteContact(String prompt){
        while (true) {
            String index = input.getString(prompt);
            Long phoneNumber = contact.get(index);
            if (contact.containsKey(index)) {
                contact.remove(index, phoneNumber);
                return;
            } else {
                System.err.println("Contact " + index + " does not exist.");
            }
        }
    }
    public static Contacts createFromString (String contactString) {
        Contacts contact = new Contacts();
        String [] parts = contactString.split(":");
        contact.setContact(parts[0].trim(), Long.parseLong(parts[1].trim()));
        return contact;
    }
    public static ArrayList<String> convertContactsToArrayList () {
        ArrayList<String> contactList = new ArrayList<>();
        contactList = CollectionsHelper.makeArrayListFromHashMap(contact);
        return contactList;
    }
}
