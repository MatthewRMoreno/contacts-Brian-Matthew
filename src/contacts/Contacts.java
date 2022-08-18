package contacts;

import utils.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    public Map.Entry<String, Long> getIndividualContact(String name) {
        for (Map.Entry<String, Long> key : contact.entrySet()) {
            if (contact.containsKey(name)) {
                return key;
            } else {
                System.err.println("Contact " + name + " does not exist.");
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
        String index = input.getString(prompt);
        Long phoneNumber = contact.get(index);
        System.out.println(phoneNumber);
        while (true) {
            if (contact.containsKey(index)) {
                contact.remove(index, phoneNumber);
                return;
            } else {
                System.err.println("Contact " + index + " does not exist.");
                index = input.getString(prompt);
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
