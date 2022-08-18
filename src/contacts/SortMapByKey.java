package contacts;

import java.util.HashMap;
import java.util.Map;

public class SortMapByKey {
    static Map<String, Integer> map = new HashMap<>();

    public static void sort(Map<String, Integer> sorted)
    {
        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }


}
