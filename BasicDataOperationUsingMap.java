import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * 1. Визначення класу домашньої тварини за допомогою 'record'.
 * - Вилучено модифікатор 'public' для уникнення помилки "The public type Pet must be defined in its own file".
 */
record Pet(String nickname, Double jumpLength) {
    // Тіло класу порожнє. record автоматично надає необхідні методи (equals, hashCode, toString).
}


/**
 * 2. Окремий клас-компаратор для сортування ключів Pet.
 * - Логіка сортування: nickname за зростанням, jumpLength за зменшенням.
 */
class PetComparator implements Comparator<Pet> {
    
    // Функціональний компаратор
    private static final Comparator<Pet> PET_COMPARATOR = 
        Comparator.comparing(Pet::nickname)                           // nickname за зростанням
                  .thenComparing(Pet::jumpLength, Comparator.reverseOrder()); // jumpLength за зменшенням

    @Override
    public int compare(Pet pet1, Pet pet2) {
        return PET_COMPARATOR.compare(pet1, pet2);
    }
}


public class BasicDataOperationUsingMap {

    private static Map<Pet, String> treeMap; 

    // =================================================================
    // МЕТОДИ ДЛЯ РОБОТИ З TreeMap (з вимірюванням часу)
    // =================================================================

    /**
     * Додавання пари ключ/значення з вимірюванням часу.
     */
    public static void addEntry(Pet key, String value) {
        long timeStart = System.nanoTime();
        treeMap.put(key, value);
        measureTime(timeStart, "TreeMap: Додавання нової пари");
        System.out.println("✅ TreeMap: Додано пару " + key + " -> " + value);
    }

    /**
     * Видалення за ключем з вимірюванням часу.
     */
    public static void removeByKey(Pet key) {
        long timeStart = System.nanoTime();
        String removedValue = treeMap.remove(key);
        measureTime(timeStart, "TreeMap: Видалення за ключем");
        if (removedValue != null) {
            System.out.println("❌ TreeMap: Видалено ключ: " + key + " (значення: " + removedValue + ")");
        } else {
            System.out.println("⚠️ TreeMap: Ключ " + key + " не знайдено.");
        }
    }
    
    /**
     * Видалення за значенням із TreeMap з вимірюванням часу (Stream API/removeIf).
     */
    public static void removeByValue(String value) {
        long timeStart = System.nanoTime();
        boolean removed = treeMap.entrySet().removeIf(entry -> entry.getValue().equals(value));
        measureTime(timeStart, "TreeMap: Видалення за значенням");
        if (removed) {
            System.out.println("❌ TreeMap: Видалено всі записи зі значенням: " + value);
        } else {
            System.out.println("⚠️ TreeMap: Значення '" + value + "' не знайдено.");
        }
    }

    /**
     * Функціональний пошук за значенням в Map з вимірюванням часу (Stream API).
     */
    public static void findByValueFunctional(Map<Pet, String> map, String value) {
        long timeStart = System.nanoTime();

        List<Pet> foundKeys = map.entrySet().stream()
           .filter(entry -> entry.getValue().equals(value)) 
           .map(Entry::getKey) 
           .collect(Collectors.toList());
        
        foundKeys.forEach(e -> System.out.println("Знайдено значення '" + value + "' у ключа: " + e));

        measureTime(timeStart, "Пошук за значенням в LinkedHashMap (Stream API)");
    }


    /**
     * Сортує Map за значенням (власником) за допомогою Stream API з вимірюванням часу.
     */
    public static Map<Pet, String> sortMapByValue(Map<Pet, String> originalMap) {
        long timeStart = System.nanoTime();

        Map<Pet, String> sortedMap = originalMap.entrySet().stream()
                .sorted(Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Entry::getKey,
                        Entry::getValue,
                        (e1, e2) -> e1, 
                        LinkedHashMap::new 
                ));
        
        measureTime(timeStart, "Сортування LinkedHashMap за значенням (Stream API)");
        return sortedMap;
    }


    public static void main(String[] args) {
        // Ініціалізація даних з використанням Pet
        Map<Pet, String> linkedMap = new LinkedHashMap<>();
        linkedMap.put(new Pet("Рись", 4.5), "Тарас");
        linkedMap.put(new Pet("Пума", 3.8), "Орина");
        linkedMap.put(new Pet("Орлик", 5.2), "Святослав");
        linkedMap.put(new Pet("Ловець", 4.0), "Марта");
        linkedMap.put(new Pet("Пума", 6.1), "Богдан");
        Pet keyToRemove = new Pet("Кігть", 3.5); 
        linkedMap.put(keyToRemove, "Святослав");
        linkedMap.put(new Pet("Звір", 5.7), "Назар");
        linkedMap.put(new Pet("Грація", 4.8), "Соломія");
        linkedMap.put(new Pet("Вовк", 5.0), "Орина");
        linkedMap.put(new Pet("Атак", 4.3), "Ярема");
        

        // Ініціалізація TreeMap для роботи з PetComparator
        long startInitTree = System.nanoTime();
        treeMap = new TreeMap<>(new PetComparator()); 
        treeMap.putAll(linkedMap);
        measureTime(startInitTree, "Ініціалізація TreeMap з PetComparator");

        System.out.println("=== 1. Початковий TreeMap (сортування PetComparator) ===");
        printMapFunctional(treeMap);


        // =================================================================
        // ВИКОНАННЯ ВИМОГ ЗАВДАННЯ НАД TreeMap (з аналізом швидкості)
        // =================================================================
        
        // 1. Додавання пари ключ/значення
        addEntry(new Pet("Новий", 7.0), "Петро");
        printMapFunctional(treeMap);


        // 2. Видалення за ключем
        removeByKey(keyToRemove); 
        printMapFunctional(treeMap);


        // 3. Видалення за значенням (Орина)
        String ownerToRemove = "Орина"; 
        removeByValue(ownerToRemove); 
        printMapFunctional(treeMap);


        // =================================================================
        // ВИКОНАННЯ ФУНКЦІОНАЛЬНИХ ОПЕРАЦІЙ НАД LinkedHashMap (з аналізом швидкості)
        // =================================================================
        System.out.println("=== LinkedHashMap до сортування ===");
        printMapFunctional(linkedMap);

        // Пошук за ключем 
        long startSearchKey = System.nanoTime();
        Pet searchKey = new Pet("Пума", 3.8);
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
        
        // Сортування LinkedHashMap за ключем (Stream API, PetComparator)
        System.out.println("=== Сортування LinkedHashMap за ключами (з PetComparator) ===");
        long startSortKey = System.nanoTime();
        Map<Pet, String> sortedByKey = linkedMap.entrySet().stream()
            .sorted(Entry.comparingByKey(new PetComparator())) 
            .collect(Collectors.toMap(
                Entry::getKey,
                Entry::getValue,
                (e1, e2) -> e1, 
                LinkedHashMap::new 
            ));
        measureTime(startSortKey, "Сортування LinkedHashMap за ключами");
        printMapFunctional(sortedByKey);
        
        
        // Сортування LinkedHashMap за значенням (Stream API)
        System.out.println("=== Сортування LinkedHashMap за значеннями ===");
        Map<Pet, String> sortedByValue = sortMapByValue(linkedMap);
        printMapFunctional(sortedByValue);
    }

    // Допоміжний метод друку Map (функціональний)
    private static void printMapFunctional(Map<Pet, String> map) {
        System.out.println("--- Елементів у Map: " + map.size() + " ---");
        map.forEach((key, value) -> System.out.println(key + " -> " + value));
        System.out.println();
    }

    // Допоміжний метод вимірювання часу
    private static void measureTime(long start, String operation) {
        long end = System.nanoTime();
        System.out.println("=== Аналіз швидкості: '" + operation + "': " + (end - start) + " нс ===\n");
    }
}