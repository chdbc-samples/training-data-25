import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Клас BasicDataOperationUsingMap реалізує операції з колекціями типу Map для зберігання пар "кінь – власник".
 * 
 * Порівнюється продуктивність LinkedHashMap та TreeMap.
 */
public class BasicDataOperationUsingMap {

    private final Horse KEY_TO_SEARCH_AND_DELETE = new Horse("Вітер", LocalDate.parse("27/12/2022", Horse.formatter));
    private final Horse KEY_TO_ADD = new Horse("Гнідий", LocalDate.parse("15/06/2024", Horse.formatter));

    private final String VALUE_TO_SEARCH_AND_DELETE = "Орина";
    private final String VALUE_TO_ADD = "Степан";

    private LinkedHashMap<Horse, String> linkedHashMap;
    private TreeMap<Horse, String> treeMap;

    // Компаратор для сортування за значеннями (іменами власників)
    static class OwnerValueComparator implements Comparator<Map.Entry<Horse, String>> {
        @Override
        public int compare(Map.Entry<Horse, String> e1, Map.Entry<Horse, String> e2) {
            return e1.getValue().compareTo(e2.getValue());
        }
    }

    /**
     * Клас Horse — ключ у Map
     * Сортування: nickname ↓, birthday ↓
     */
    public static class Horse implements Comparable<Horse> {
        private final String nickname;
        private final LocalDate birthday;
        static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        public Horse(String nickname, LocalDate birthday) {
            this.nickname = nickname;
            this.birthday = birthday;
        }

        public String getNickname() { return nickname; }
        public LocalDate getBirthday() { return birthday; }

        @Override
        public int compareTo(Horse other) {
            // Сортування за зменшенням nickname
            int cmp = other.nickname.compareTo(this.nickname);
            if (cmp != 0) return cmp;

            // Якщо nickname однакові → за зменшенням birthday
            return other.birthday.compareTo(this.birthday);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Horse)) return false;
            Horse h = (Horse) obj;
            return Objects.equals(nickname, h.nickname) && Objects.equals(birthday, h.birthday);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nickname, birthday);
        }

        @Override
        public String toString() {
            return "Horse{nickname='" + nickname + "', birthday='" + birthday.format(formatter) + "'}";
        }
    }

    public BasicDataOperationUsingMap(LinkedHashMap<Horse, String> linkedHashMap, TreeMap<Horse, String> treeMap) {
        this.linkedHashMap = linkedHashMap;
        this.treeMap = treeMap;
    }

    public void executeDataOperations() {
        System.out.println("========= Операції з LinkedHashMap =========");
        System.out.println("Початковий розмір LinkedHashMap: " + linkedHashMap.size());

        findByKeyInLinkedHashMap();
        findByValueInLinkedHashMap();

        printMap(linkedHashMap, "LinkedHashMap до сортування");
        sortLinkedHashMap();
        printMap(linkedHashMap, "LinkedHashMap після сортування");

        addEntryToLinkedHashMap();
        removeByKeyFromLinkedHashMap();
        removeByValueFromLinkedHashMap();
        System.out.println("Кінцевий розмір LinkedHashMap: " + linkedHashMap.size());


        System.out.println("\n\n========= Операції з TreeMap =========");
        System.out.println("Початковий розмір TreeMap: " + treeMap.size());

        findByKeyInTreeMap();
        findByValueInTreeMap();

        printMap(treeMap, "TreeMap (вже відсортована за ключами)");

        addEntryToTreeMap();
        removeByKeyFromTreeMap();
        removeByValueFromTreeMap();

        System.out.println("Кінцевий розмір TreeMap: " + treeMap.size());
    }

    // ======== LinkedHashMap methods ========
    private void printMap(Map<Horse, String> map, String title) {
        System.out.println("\n=== " + title + " ===");
        long start = System.nanoTime();
        for (Map.Entry<Horse, String> e : map.entrySet()) {
            System.out.println("  " + e.getKey() + " -> " + e.getValue());
        }
        displayTime(start, "виведення " + title);
    }

    private void sortLinkedHashMap() {
        long start = System.nanoTime();
        List<Map.Entry<Horse, String>> entries = new ArrayList<>(linkedHashMap.entrySet());
        entries.sort(Map.Entry.comparingByKey()); // за природним порядком Horse
        LinkedHashMap<Horse, String> sorted = new LinkedHashMap<>();
        for (Map.Entry<Horse, String> e : entries) sorted.put(e.getKey(), e.getValue());
        linkedHashMap = sorted;
        displayTime(start, "сортування LinkedHashMap за ключами");
    }

    private void findByKeyInLinkedHashMap() {
        long start = System.nanoTime();
        boolean found = linkedHashMap.containsKey(KEY_TO_SEARCH_AND_DELETE);
        displayTime(start, "пошук за ключем в LinkedHashMap");
        if (found)
            System.out.println("Елемент " + KEY_TO_SEARCH_AND_DELETE + " знайдено. Власник: " + linkedHashMap.get(KEY_TO_SEARCH_AND_DELETE));
        else
            System.out.println("Елемент " + KEY_TO_SEARCH_AND_DELETE + " не знайдено.");
    }

    private void findByValueInLinkedHashMap() {
        long start = System.nanoTime();
        List<Map.Entry<Horse, String>> entries = new ArrayList<>(linkedHashMap.entrySet());
        entries.sort(new OwnerValueComparator());

        // створюємо штучний запис для пошуку без Map.entry()
        Map.Entry<Horse, String> searchEntry = new Map.Entry<Horse, String>() {
            public Horse getKey() { return null; }
            public String getValue() { return VALUE_TO_SEARCH_AND_DELETE; }
            public String setValue(String value) { return null; }
        };

        int pos = Collections.binarySearch(entries, searchEntry, new OwnerValueComparator());
        displayTime(start, "бінарний пошук за значенням в LinkedHashMap");

        if (pos >= 0)
            System.out.println("Власника '" + VALUE_TO_SEARCH_AND_DELETE + "' знайдено: " + entries.get(pos).getKey());
        else
            System.out.println("Власник '" + VALUE_TO_SEARCH_AND_DELETE + "' не знайдений.");
    }


    private void addEntryToLinkedHashMap() {
        long start = System.nanoTime();
        linkedHashMap.put(KEY_TO_ADD, VALUE_TO_ADD);
        displayTime(start, "додавання запису до LinkedHashMap");
        System.out.println("Додано: " + KEY_TO_ADD + " -> " + VALUE_TO_ADD);
    }

    private void removeByKeyFromLinkedHashMap() {
        long start = System.nanoTime();
        String removed = linkedHashMap.remove(KEY_TO_SEARCH_AND_DELETE);
        displayTime(start, "видалення за ключем з LinkedHashMap");
        if (removed != null)
            System.out.println("Видалено " + KEY_TO_SEARCH_AND_DELETE + " -> " + removed);
        else
            System.out.println("Ключ не знайдено.");
    }

    private void removeByValueFromLinkedHashMap() {
        long start = System.nanoTime();
        List<Horse> toRemove = new ArrayList<>();
        for (Map.Entry<Horse, String> e : linkedHashMap.entrySet())
            if (e.getValue().equals(VALUE_TO_SEARCH_AND_DELETE)) toRemove.add(e.getKey());
        toRemove.forEach(linkedHashMap::remove);
        displayTime(start, "видалення за значенням з LinkedHashMap");
        System.out.println("Видалено " + toRemove.size() + " запис(ів) з власником '" + VALUE_TO_SEARCH_AND_DELETE + "'");
    }

    // ======== TreeMap methods ========
    private void findByKeyInTreeMap() {
        long start = System.nanoTime();
        boolean found = treeMap.containsKey(KEY_TO_SEARCH_AND_DELETE);
        displayTime(start, "пошук за ключем в TreeMap");
        if (found)
            System.out.println("Елемент " + KEY_TO_SEARCH_AND_DELETE + " знайдено. Власник: " + treeMap.get(KEY_TO_SEARCH_AND_DELETE));
        else
            System.out.println("Елемент " + KEY_TO_SEARCH_AND_DELETE + " не знайдено.");
    }

    private void findByValueInTreeMap() {
        long start = System.nanoTime();
        List<Map.Entry<Horse, String>> entries = new ArrayList<>(treeMap.entrySet());
        entries.sort(new OwnerValueComparator());
    
        // створюємо штучний запис для пошуку без Map.entry()
        Map.Entry<Horse, String> searchEntry = new Map.Entry<Horse, String>() {
            public Horse getKey() { return null; }
            public String getValue() { return VALUE_TO_SEARCH_AND_DELETE; }
            public String setValue(String value) { return null; }
        };
    
        int pos = Collections.binarySearch(entries, searchEntry, new OwnerValueComparator());
        displayTime(start, "бінарний пошук за значенням в TreeMap");
    
        if (pos >= 0)
            System.out.println("Власника '" + VALUE_TO_SEARCH_AND_DELETE + "' знайдено: " + entries.get(pos).getKey());
        else
            System.out.println("Власник '" + VALUE_TO_SEARCH_AND_DELETE + "' не знайдений.");
    }


    private void addEntryToTreeMap() {
        long start = System.nanoTime();
        treeMap.put(KEY_TO_ADD, VALUE_TO_ADD);
        displayTime(start, "додавання запису до TreeMap");
        System.out.println("Додано: " + KEY_TO_ADD + " -> " + VALUE_TO_ADD);
    }

    private void removeByKeyFromTreeMap() {
        long start = System.nanoTime();
        String removed = treeMap.remove(KEY_TO_SEARCH_AND_DELETE);
        displayTime(start, "видалення за ключем з TreeMap");
        if (removed != null)
            System.out.println("Видалено " + KEY_TO_SEARCH_AND_DELETE + " -> " + removed);
        else
            System.out.println("Ключ не знайдено.");
    }

    private void removeByValueFromTreeMap() {
        long start = System.nanoTime();
        List<Horse> toRemove = new ArrayList<>();
        for (Map.Entry<Horse, String> e : treeMap.entrySet())
            if (e.getValue().equals(VALUE_TO_SEARCH_AND_DELETE)) toRemove.add(e.getKey());
        toRemove.forEach(treeMap::remove);
        displayTime(start, "видалення за значенням з TreeMap");
        System.out.println("Видалено " + toRemove.size() + " запис(ів) з власником '" + VALUE_TO_SEARCH_AND_DELETE + "'");
    }

    // ======== Допоміжний метод для часу ========
    private static void displayTime(long start, String message) {
        long duration = System.nanoTime() - start;
        System.out.printf("========= Тривалість операції '%s': %d нс =========%n", message, duration);
    }

    // ======== MAIN ========
    public static void main(String[] args) {
        DateTimeFormatter f = Horse.formatter;

        LinkedHashMap<Horse, String> linked = new LinkedHashMap<>();
        linked.put(new Horse("Буян", LocalDate.parse("03/12/2020", f)), "Єва");
        linked.put(new Horse("Вітер", LocalDate.parse("27/12/2022", f)), "Зінаїда");
        linked.put(new Horse("Зірка", LocalDate.parse("10/01/2019", f)), "Матвій");
        linked.put(new Horse("Стріла", LocalDate.parse("23/01/2021", f)), "Орина");
        linked.put(new Horse("Гром", LocalDate.parse("04/05/2025", f)), "Андрій");
        linked.put(new Horse("Луна", LocalDate.parse("13/07/2017", f)), "Андрій");
        linked.put(new Horse("Барсик", LocalDate.parse("17/11/2021", f)), "Ярослав");
        linked.put(new Horse("Араб", LocalDate.parse("09/09/2019", f)), "Орина");
        linked.put(new Horse("Луна", LocalDate.parse("31/08/2021", f)), "Стефанія");
        linked.put(new Horse("Барсик", LocalDate.parse("07/03/2023", f)), "Тимофій");

        TreeMap<Horse, String> tree = new TreeMap<>(linked);

        BasicDataOperationUsingMap operations = new BasicDataOperationUsingMap(linked, tree);
        operations.executeDataOperations();
    }
}