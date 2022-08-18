package contacts;

import utils.Input;

import java.util.HashMap;
import java.util.Map;
public class Contacts {
    private final Map<String, Integer> contact = new HashMap<>();
    Input input = new Input();
    public void setContact (String item, int amount) {
        contact.put(item, amount);
    }
    public Map<String, Integer> getItemAmount () {
        return contact;
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
}
