 functional-programming
import java.util.*;
import java.util.Map.Entry;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
 functional-programming
import java.util.stream.Collectors;

/**
 * Клас Lynx: об'єкт-ключ для Map. 
 * Реалізує сортування за: nickname за зменшенням, jumpLength за зростанням.
 */
class Lynx implements Comparable<Lynx> {
    String nickname;
    Double jumpLength;

 functional-programming
    public Lynx(String nickname, Double jumpLength) {
        this.nickname = nickname;
        this.jumpLength = jumpLength;
    }

    // Геттери необхідні для використання функціонального Comparator.comparing()
    public String getNickname() { return nickname; }
    public Double getJumpLength() { return jumpLength; }

    /**
     * Внутрішній клас Pet для зберігання інформації про домашню тварину.
     * 
     * Реалізує Comparable<Pet> для визначення природного порядку сортування.
     * Природний порядок: спочатку за кличкою (nickname) за зростанням, потім за видом (species) за спаданням.
     */
    public static class Pet implements Comparable<Pet> {
        private final String nickname;
        private final String species;

        public Pet(String nickname) {
            this.nickname = nickname;
            this.species = null;
        }
functional-programming

    @Override
    public int compareTo(Lynx other) {
        // 3.5: Заміна імперативного порівняння на функціональний Comparator
        return Comparator
                .comparing(Lynx::getNickname, Comparator.reverseOrder()) // nickname за зменшенням
                .thenComparing(Lynx::getJumpLength)                       // jumpLength за зростанням
                .compare(this, other);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Lynx)) return false;
        Lynx other = (Lynx) obj;
        return Objects.equals(nickname, other.nickname) && Objects.equals(jumpLength, other.jumpLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, jumpLength);
    }

 functional-programming
    @Override
    public String toString() {
        return "Lynx{nickname='" + nickname + "', jumpLength=" + jumpLength + "}";


    // ===== Методи для Hashtable =====

    /**
     * Виводить вміст Hashtable без сортування.
     * Hashtable не гарантує жодного порядку елементів.
     */
    private void printHashtable() {
        System.out.println("\n=== Пари ключ-значення в Hashtable ===");
        long timeStart = System.nanoTime();

        hashtable.entrySet().forEach(entry ->
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue())
        );

        PerformanceTracker.displayOperationTime(timeStart, "виведення пари ключ-значення в Hashtable");
    }

    /**
     * Сортує Hashtable за ключами.
     * Використовує Stream API з природним порядком Pet (Pet.compareTo()).
     * Перезаписує hashtable відсортованими даними.
     */
    private void sortHashtable() {
        long timeStart = System.nanoTime();

        // Використовуємо Stream API для сортування та створення нової Hashtable
        hashtable = hashtable.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        Hashtable::new
                ));

        PerformanceTracker.displayOperationTime(timeStart, "сортування Hashtable за ключами");
functional-programming
    }
}

public class BasicDataOperationUsingMap {

    private static Map<Lynx, String> treeMap;

    // =================================================================
    // МЕТОДИ ДЛЯ РОБОТИ З TreeMap
    // =================================================================

    /**
 functional-programming
     * Додавання пари ключ/значення.
     */
    public static void addEntry(Lynx key, String value) {
        treeMap.put(key, value);
        System.out.println("✅ TreeMap: Додано пару " + key + " -> " + value);

     * Здійснює пошук елемента за значенням in Hashtable.
     * Використовує Stream API для фільтрації та пошуку.
     */
    void findByValueInHashtable() {
        long timeStart = System.nanoTime();

        // Використовуємо Stream API для пошуку за значенням
        Map.Entry<Pet, String> foundEntry = hashtable.entrySet().stream()
                .filter(entry -> VALUE_TO_SEARCH_AND_DELETE.equals(entry.getValue()))
                .findFirst()
                .orElse(null);

        PerformanceTracker.displayOperationTime(timeStart, "пошук за значенням в Hashtable");

        if (foundEntry != null) {
            System.out.println("Власника '" + VALUE_TO_SEARCH_AND_DELETE + "' знайдено. Pet: " + foundEntry.getKey());
        } else {
            System.out.println("Власник '" + VALUE_TO_SEARCH_AND_DELETE + "' відсутній в Hashtable.");
        }
functional-programming
    }

    /**
     * Видалення за ключем.
     */
    public static void removeByKey(Lynx key) {
        String removedValue = treeMap.remove(key);
        if (removedValue != null) {
functional-programming
            System.out.println("❌ TreeMap: Видалено ключ: " + key + " (значення: " + removedValue + ")");

            System.out.println("Видалено запис з ключем '" + KEY_TO_SEARCH_AND_DELETE + "'. Власник був: " + removedValue);
        } else {
            System.out.println("Ключ '" + KEY_TO_SEARCH_AND_DELETE + "' не знайдено для видалення.");
        }
    }

    /**
     * Видаляє записи з Hashtable за значенням.
     */
    void removeByValueFromHashtable() {
        long timeStart = System.nanoTime();

        // Використовуємо Stream API для пошуку ключів для видалення
        List<Pet> keysToRemove = hashtable.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getValue().equals(VALUE_TO_SEARCH_AND_DELETE))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        
        keysToRemove.forEach(hashtable::remove);

        PerformanceTracker.displayOperationTime(timeStart, "видалення за значенням з Hashtable");

        System.out.println("Видалено " + keysToRemove.size() + " записів з власником '" + VALUE_TO_SEARCH_AND_DELETE + "'");
    }

    // ===== Методи для TreeMap =====

    /**
     * Виводить вміст TreeMap.
     * TreeMap автоматично відсортована за ключами (Pet nickname за зростанням, species за спаданням).
     */
    private void printTreeMap() {
        System.out.println("\n=== Пари ключ-значення в TreeMap ===");

        long timeStart = System.nanoTime();
        treeMap.forEach((key, value) ->
            System.out.println("  " + key + " -> " + value)
        );

        PerformanceTracker.displayOperationTime(timeStart, "виведення пар ключ-значення в TreeMap");
    }

    /**
     * Здійснює пошук елемента за ключем в TreeMap.
     * Використовує Pet.compareTo() для навігації по дереву.
     */
    void findByKeyInTreeMap() {
        long timeStart = System.nanoTime();

        boolean found = treeMap.containsKey(KEY_TO_SEARCH_AND_DELETE);

        PerformanceTracker.displayOperationTime(timeStart, "пошук за ключем в TreeMap");

        if (found) {
            String value = treeMap.get(KEY_TO_SEARCH_AND_DELETE);
            System.out.println("Елемент з ключем '" + KEY_TO_SEARCH_AND_DELETE + "' знайдено. Власник: " + value);
functional-programming
        } else {
            System.out.println("⚠️ TreeMap: Ключ " + key + " не знайдено.");
        }
    }
    
    /**
 functional-programming
     * Видалення за значенням із TreeMap. 
     * Використовує Predicate (лямбда-вираз) removeIf.
     */
    public static void removeByValue(String value) {
        boolean removed = treeMap.entrySet().removeIf(entry -> entry.getValue().equals(value));
        if (removed) {
            System.out.println("❌ TreeMap: Видалено всі записи зі значенням: " + value);

     * Здійснює пошук елемента за значенням в TreeMap.
     * Використовує Stream API для фільтрації та пошуку.
     */
    void findByValueInTreeMap() {
        long timeStart = System.nanoTime();

        // Використовуємо Stream API для пошуку за значенням
        Map.Entry<Pet, String> foundEntry = treeMap.entrySet().stream()
                .filter(entry -> VALUE_TO_SEARCH_AND_DELETE.equals(entry.getValue()))
                .findFirst()
                .orElse(null);

        PerformanceTracker.displayOperationTime(timeStart, "пошук за значенням в TreeMap");

        if (foundEntry != null) {
            System.out.println("Власника '" + VALUE_TO_SEARCH_AND_DELETE + "' знайдено. Pet: " + foundEntry.getKey());
functional-programming
        } else {
            System.out.println("⚠️ TreeMap: Значення '" + value + "' не знайдено.");
        }
    }

    /**
     * Функціональний пошук за значенням в Map.
     * 3.8.2: Заміна імперативного пошуку на Stream API.
     */
    public static void findByValueFunctional(Map<Lynx, String> map, String value) {
        long timeStart = System.nanoTime();

        map.entrySet().stream()
           .filter(entry -> entry.getValue().equals(value)) // Фільтруємо за значенням
           .forEach(e -> System.out.println("Знайдено значення '" + value + "' у ключа: " + e.getKey())); // Друк

        measureTime(timeStart, "Пошук за значенням в LinkedHashMap (Stream API)");
    }


    /**
     * Сортує Map за значенням (власником) за допомогою Stream API.
     * 3.8.3: Заміна імперативного сортування на Stream API.
     */
    public static Map<Lynx, String> sortMapByValue(Map<Lynx, String> originalMap) {
        long timeStart = System.nanoTime();

 functional-programming
        Map<Lynx, String> sortedMap = originalMap.entrySet().stream()
                // Сортуємо Entry за значенням (String), використовуючи функціональний компаратор
                .sorted(Entry.comparingByValue())
                // Збираємо результат у LinkedHashMap для збереження порядку сортування
                .collect(Collectors.toMap(
                        Entry::getKey,
                        Entry::getValue,
                        (e1, e2) -> e1, 
                        LinkedHashMap::new 
                ));
        
        measureTime(timeStart, "Сортування LinkedHashMap за значенням (Stream API)");
        return sortedMap;

        // Використовуємо Stream API для пошуку ключів для видалення
        List<Pet> keysToRemove = treeMap.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getValue().equals(VALUE_TO_SEARCH_AND_DELETE))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        
        keysToRemove.forEach(treeMap::remove);

        PerformanceTracker.displayOperationTime(timeStart, "видалення за значенням з TreeMap");

        System.out.println("Видалено " + keysToRemove.size() + " записів з власником '" + VALUE_TO_SEARCH_AND_DELETE + "'");
functional-programming
    }


    public static void main(String[] args) {
        // Ініціалізація даних
        Map<Lynx, String> linkedMap = new LinkedHashMap<>();
        linkedMap.put(new Lynx("Рись", 4.5), "Тарас");
        linkedMap.put(new Lynx("Пума", 3.8), "Орина");
        linkedMap.put(new Lynx("Орлик", 5.2), "Святослав");
        linkedMap.put(new Lynx("Ловець", 4.0), "Марта");
        linkedMap.put(new Lynx("Пума", 6.1), "Богдан");
        Lynx keyToRemove = new Lynx("Кігть", 3.5); // Використовуємо для тесту видалення
        linkedMap.put(keyToRemove, "Святослав");
        linkedMap.put(new Lynx("Звір", 5.7), "Назар");
        linkedMap.put(new Lynx("Грація", 4.8), "Соломія");
        linkedMap.put(new Lynx("Вовк", 5.0), "Орина");
        linkedMap.put(new Lynx("Атак", 4.3), "Ярема");

        // Ініціалізація TreeMap для роботи (автоматичне сортування за ключем Lynx::compareTo)
        treeMap = new TreeMap<>(linkedMap);

        System.out.println("=== 1. Початковий TreeMap (автосортування) ===");
        printMapFunctional(treeMap);


        // =================================================================
        // ВИКОНАННЯ ВИМОГ ЗАВДАННЯ НАД TreeMap
        // =================================================================
        
        // 1. Додавання пари ключ/значення
        long startAdd = System.nanoTime();
        addEntry(new Lynx("Новий", 7.0), "Петро");
        measureTime(startAdd, "TreeMap: Додавання нової пари");
        printMapFunctional(treeMap);


        // 2. Видалення за ключем
        long startRemoveKey = System.nanoTime();
        removeByKey(keyToRemove); // Видаляємо "Кігть", 3.5
        measureTime(startRemoveKey, "TreeMap: Видалення за ключем");
        printMapFunctional(treeMap);


        // 3. Видалення за значенням (Орина)
        String ownerToRemove = "Орина"; 
        long startRemoveValue = System.nanoTime();
        removeByValue(ownerToRemove); // Видаляємо всі записи, де власник "Орина"
        measureTime(startRemoveValue, "TreeMap: Видалення за значенням");
        printMapFunctional(treeMap);


        // =================================================================
        // ВИКОНАННЯ ФУНКЦІОНАЛЬНИХ ОПЕРАЦІЙ НАД LinkedHashMap
        // =================================================================
        System.out.println("=== LinkedHashMap до сортування ===");
        printMapFunctional(linkedMap);

        // Пошук за ключем (залишається get() для швидкості)
        long startSearchKey = System.nanoTime();
        Lynx searchKey = new Lynx("Пума", 3.8);
        String owner = linkedMap.get(searchKey);
        System.out.println("Власник ключа " + searchKey + ": " + owner);
        measureTime(startSearchKey, "Пошук за ключем в LinkedHashMap");

        // Пошук за значенням (Stream API)
        String searchValue = "Святослав";
        findByValueFunctional(linkedMap, searchValue);
        
        // Видалення за ключем у LinkedHashMap
        long startRemoveKeyL = System.nanoTime();
        linkedMap.remove(searchKey); 
        measureTime(startRemoveKeyL, "Видалення за ключем з LinkedHashMap");

        // Видалення за значенням у LinkedHashMap (removeIf)
        long startRemoveValueL = System.nanoTime();
        linkedMap.entrySet().removeIf(entry -> entry.getValue().equals(searchValue));
        measureTime(startRemoveValueL, "Видалення за значенням з LinkedHashMap");
        
        // Сортування LinkedHashMap за ключем (Stream API)
        System.out.println("=== Сортування LinkedHashMap за ключами ===");
        long startSortKey = System.nanoTime();
        Map<Lynx, String> sortedByKey = linkedMap.entrySet().stream()
            .sorted(Entry.comparingByKey())
            .collect(Collectors.toMap(
                Entry::getKey,
                Entry::getValue,
                (e1, e2) -> e1, 
                LinkedHashMap::new 
            ));
        measureTime(startSortKey, "Сортування LinkedHashMap за ключами");
        printMapFunctional(sortedByKey);
    }

    // Допоміжний метод друку Map (функціональний)
    private static void printMapFunctional(Map<Lynx, String> map) {
        map.forEach((key, value) -> System.out.println(key + " -> " + value));
        System.out.println();
    }

    // Допоміжний метод вимірювання часу
    private static void measureTime(long start, String operation) {
        long end = System.nanoTime();
        System.out.println("=== Час операції '" + operation + "': " + (end - start) + " нс ===\n");
    }
}