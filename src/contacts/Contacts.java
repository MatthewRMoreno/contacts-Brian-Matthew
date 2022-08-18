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
    public Map<String, Long> getIndividualContact(String name) {
            if (contact.containsKey(name)) {
                return contact;
            } else {
                System.err.println("Contact " + name + " does not exist.");
            }
        return null;
    }
    public void sortContacts() {
        CollectionsHelper.HashMapSorter(contact);
    }
    public void deleteContact() {
        deleteContact("Please enter a name");
    }
    public void deleteContact(String prompt){
        String name = input.getString(prompt);
        while (true) {
            try {
                contact.remove(prompt);
                return;
            } catch (RuntimeException re) {
                System.err.println("Contact " + name + " does not exist.");
                name = input.getString(prompt);
            }
        }
    }
    public static Contacts createFromString (String contactString) {
        Contacts contact = new Contacts();
        String [] parts = contactString.split(":");
        contact.setContact(parts[0].trim(), Integer.parseInt(parts[1].trim()));
        return contact;
    }
    public static ArrayList<String> convertContactsToArrayList () {
        ArrayList<String> contactList = new ArrayList<>();
        contactList = CollectionsHelper.makeArrayListFromHashMap(contact);
        return contactList;
    }
}
