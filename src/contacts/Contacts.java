package contacts;

import java.util.HashMap;
import java.util.Map;
public class Contacts {
    private final Map<String, Integer> contact = new HashMap<>();

    public void setContact (String item, int amount) {
        contact.put(item, amount);
    }
    public Map<String, Integer> getItemAmount () {
        return contact;
    }

}
