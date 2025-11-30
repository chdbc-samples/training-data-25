import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Клас BasicDataOperationUsingMap реалізує операції з колекціями типу Map для зберігання пар ключ-значення.
 */
public class BasicDataOperationUsingMap {
    private final Lynx KEY_TO_SEARCH_AND_DELETE = new Lynx("Пума", 3.8);
    private final Lynx KEY_TO_ADD = new Lynx("Стрибун", 4.6);

    private final String VALUE_TO_SEARCH_AND_DELETE = "Святослав";
    private final String VALUE_TO_ADD = "Лада";

    private Hashtable<Lynx, String> hashtable;
    private TreeMap<Lynx, String> treeMap;

    /**
     * Компаратор для сортування Map.Entry за значеннями String.
     */
    static class OwnerValueComparator implements Comparator<Map.Entry<Lynx, String>> {
        @Override
        public int compare(Map.Entry<Lynx, String> e1, Map.Entry<Lynx, String> e2) {
            String v1 = e1.getValue();
            String v2 = e2.getValue();
            if (v1 == null && v2 == null) return 0;
            if (v1 == null) return -1;
            if (v2 == null) return 1;
            return v1.compareTo(v2);
        }
    }

    /**
     * Клас Lynx для домашньої тварини.
     * Сортування: nickname за спаданням, jumpLength за зростанням
     */
    public static class Lynx implements Comparable<Lynx> {
        private final String nickname;
        private final Double jumpLength;

        public Lynx(String nickname, Double jumpLength) {
            this.nickname = nickname;
            this.jumpLength = jumpLength;
        }

        public String getNickname() { return nickname; }
        public Double getJumpLength() { return jumpLength; }

        @Override
        public int compareTo(Lynx other) {
            if (other == null) return 1;
            // nickname за спаданням
            int cmp = other.nickname.compareTo(this.nickname);
            if (cmp != 0) return cmp;
            // jumpLength за зростанням
            return this.jumpLength.compareTo(other.jumpLength);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Lynx other = (Lynx) obj;
            boolean nickEq = nickname != null ? nickname.equals(other.nickname) : other.nickname == null;
            boolean jumpEq = jumpLength != null ? jumpLength.equals(other.jumpLength) : other.jumpLength == null;
            return nickEq && jumpEq;
        }

        @Override
        public int hashCode() {
            int result = nickname != null ? nickname.hashCode() : 0;
            result = 31 * result + (jumpLength != null ? jumpLength.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Lynx{nickname='" + nickname + "', jumpLength=" + jumpLength + ", hashCode=" + hashCode() + "}";
        }
    }

    BasicDataOperationUsingMap(Hashtable<Lynx, String> hashtable, TreeMap<Lynx, String> treeMap) {
        this.hashtable = hashtable;
        this.treeMap = treeMap;
    }

    public void executeDataOperations() {
        // Hashtable
        System.out.println("========= Операції з Hashtable =========");
        System.out.println("Початковий розмір Hashtable: " + hashtable.size());
        
        findByKeyInHashtable();
        findByValueInHashtable();

        printHashtable();
        sortHashtable();
        printHashtable();

        findByKeyInHashtable();
        findByValueInHashtable();

        addEntryToHashtable();
        removeByKeyFromHashtable();
        removeByValueFromHashtable();
        
        System.out.println("Кінцевий розмір Hashtable: " + hashtable.size());

        // TreeMap
        System.out.println("\n========= Операції з TreeMap =========");
        System.out.println("Початковий розмір TreeMap: " + treeMap.size());
        
        findByKeyInTreeMap();
        findByValueInTreeMap();

        printTreeMap();

        addEntryToTreeMap();
        removeByKeyFromTreeMap();
        removeByValueFromTreeMap();

        System.out.println("Кінцевий розмір TreeMap: " + treeMap.size());
    }

    // ===== Методи для Hashtable =====
    private void printHashtable() {
        System.out.println("\n=== Пари ключ-значення в Hashtable ===");
        for (Map.Entry<Lynx, String> entry : hashtable.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    private void sortHashtable() {
        List<Lynx> sortedKeys = new ArrayList<>(hashtable.keySet());
        Collections.sort(sortedKeys);

        Hashtable<Lynx, String> sortedHashtable = new Hashtable<>();
        for (Lynx key : sortedKeys) {
            sortedHashtable.put(key, hashtable.get(key));
        }
        hashtable = sortedHashtable;
    }

    void findByKeyInHashtable() {
        boolean found = hashtable.containsKey(KEY_TO_SEARCH_AND_DELETE);
        if (found) {
            System.out.println("Елемент з ключем '" + KEY_TO_SEARCH_AND_DELETE + "' знайдено. Власник: " + hashtable.get(KEY_TO_SEARCH_AND_DELETE));
        } else {
            System.out.println("Елемент з ключем '" + KEY_TO_SEARCH_AND_DELETE + "' відсутній в Hashtable.");
        }
    }

    void findByValueInHashtable() {
        List<Map.Entry<Lynx, String>> entries = new ArrayList<>(hashtable.entrySet());
        Collections.sort(entries, new OwnerValueComparator());

        Map.Entry<Lynx, String> searchEntry = new Map.Entry<>() {
            public Lynx getKey() { return null; }
            public String getValue() { return VALUE_TO_SEARCH_AND_DELETE; }
            public String setValue(String value) { return null; }
        };

        int pos = Collections.binarySearch(entries, searchEntry, new OwnerValueComparator());
        if (pos >= 0) {
            System.out.println("Власника '" + VALUE_TO_SEARCH_AND_DELETE + "' знайдено. Lynx: " + entries.get(pos).getKey());
        } else {
            System.out.println("Власник '" + VALUE_TO_SEARCH_AND_DELETE + "' відсутній в Hashtable.");
        }
    }

    void addEntryToHashtable() { hashtable.put(KEY_TO_ADD, VALUE_TO_ADD); }
    void removeByKeyFromHashtable() { hashtable.remove(KEY_TO_SEARCH_AND_DELETE); }
    void removeByValueFromHashtable() {
        List<Lynx> keysToRemove = new ArrayList<>();
        for (Map.Entry<Lynx, String> e : hashtable.entrySet()) {
            if (VALUE_TO_SEARCH_AND_DELETE.equals(e.getValue())) keysToRemove.add(e.getKey());
        }
        for (Lynx k : keysToRemove) hashtable.remove(k);
    }

    // ===== Методи для TreeMap =====
    private void printTreeMap() {
        System.out.println("\n=== Пари ключ-значення в TreeMap ===");
        for (Map.Entry<Lynx, String> entry : treeMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    void findByKeyInTreeMap() {
        boolean found = treeMap.containsKey(KEY_TO_SEARCH_AND_DELETE);
        if (found) {
            System.out.println("Елемент з ключем '" + KEY_TO_SEARCH_AND_DELETE + "' знайдено. Власник: " + treeMap.get(KEY_TO_SEARCH_AND_DELETE));
        } else {
            System.out.println("Елемент з ключем '" + KEY_TO_SEARCH_AND_DELETE + "' відсутній в TreeMap.");
        }
    }

    void findByValueInTreeMap() {
        List<Map.Entry<Lynx, String>> entries = new ArrayList<>(treeMap.entrySet());
        Collections.sort(entries, new OwnerValueComparator());

        Map.Entry<Lynx, String> searchEntry = new Map.Entry<>() {
            public Lynx getKey() { return null; }
            public String getValue() { return VALUE_TO_SEARCH_AND_DELETE; }
            public String setValue(String value) { return null; }
        };

        int pos = Collections.binarySearch(entries, searchEntry, new OwnerValueComparator());
        if (pos >= 0) {
            System.out.println("Власника '" + VALUE_TO_SEARCH_AND_DELETE + "' знайдено. Lynx: " + entries.get(pos).getKey());
        } else {
            System.out.println("Власник '" + VALUE_TO_SEARCH_AND_DELETE + "' відсутній в TreeMap.");
        }
    }

    void addEntryToTreeMap() { treeMap.put(KEY_TO_ADD, VALUE_TO_ADD); }
    void removeByKeyFromTreeMap() { treeMap.remove(KEY_TO_SEARCH_AND_DELETE); }
    void removeByValueFromTreeMap() {
        List<Lynx> keysToRemove = new ArrayList<>();
        for (Map.Entry<Lynx, String> e : treeMap.entrySet()) {
            if (VALUE_TO_SEARCH_AND_DELETE.equals(e.getValue())) keysToRemove.add(e.getKey());
        }
        for (Lynx k : keysToRemove) treeMap.remove(k);
    }

    public static void main(String[] args) {
        Hashtable<Lynx, String> hashtable = new Hashtable<>();
        hashtable.put(new Lynx("Рись", 4.5), "Тарас");
        hashtable.put(new Lynx("Пума", 3.8), "Орина");
        hashtable.put(new Lynx("Орлик", 5.2), "Святослав");
        hashtable.put(new Lynx("Ловець", 4.0), "Марта");
        hashtable.put(new Lynx("Пума", 6.1), "Богдан");
        hashtable.put(new Lynx("Кігть", 3.5), "Святослав");
        hashtable.put(new Lynx("Звір", 5.7), "Назар");
        hashtable.put(new Lynx("Грація", 4.8), "Соломія");
        hashtable.put(new Lynx("Вовк", 5.0), "Орина");
        hashtable.put(new Lynx("Атак", 4.3), "Ярема");

        TreeMap<Lynx, String> treeMap = new TreeMap<>();
        treeMap.putAll(hashtable);

        BasicDataOperationUsingMap operations = new BasicDataOperationUsingMap(hashtable, treeMap);
        operations.executeDataOperations();
    }
}
