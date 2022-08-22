package contacts;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionsHelper {
    static Map<String, Long> map = new HashMap<>();

    public static ArrayList<String> makeArrayListFromHashMap(Map<String, Long> map) {
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            list.add(entry.getKey() + " : " + entry.getValue());
        }
        return list;
    }

    public static Map<String, Long> HashMapSorter(Map<String, Long> unsortedMap) {
        return unsortedMap.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getKey().length()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));
    }
}
