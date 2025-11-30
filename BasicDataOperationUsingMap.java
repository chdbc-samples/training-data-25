import java.util.*;
import java.util.Map.Entry;

class Lynx implements Comparable<Lynx> {
    String nickname;
    Double jumpLength;

    public Lynx(String nickname, Double jumpLength) {
        this.nickname = nickname;
        this.jumpLength = jumpLength;
    }

    @Override
    public int compareTo(Lynx other) {
        // Сортування: nickname за зменшенням, jumpLength за зростанням
        int cmp = other.nickname.compareTo(this.nickname); // спадання
        if (cmp == 0) {
            cmp = this.jumpLength.compareTo(other.jumpLength); // зростання
        }
        return cmp;
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

    @Override
    public String toString() {
        return "Lynx{nickname='" + nickname + "', jumpLength=" + jumpLength + "}";
    }
}

public class BasicDataOperationUsingMap {

    public static void main(String[] args) {
        // Ініціалізація даних
        Map<Lynx, String> linkedMap = new LinkedHashMap<>();
        linkedMap.put(new Lynx("Рись", 4.5), "Тарас");
        linkedMap.put(new Lynx("Пума", 3.8), "Орина");
        linkedMap.put(new Lynx("Орлик", 5.2), "Святослав");
        linkedMap.put(new Lynx("Ловець", 4.0), "Марта");
        linkedMap.put(new Lynx("Пума", 6.1), "Богдан");
        linkedMap.put(new Lynx("Кігть", 3.5), "Святослав");
        linkedMap.put(new Lynx("Звір", 5.7), "Назар");
        linkedMap.put(new Lynx("Грація", 4.8), "Соломія");
        linkedMap.put(new Lynx("Вовк", 5.0), "Орина");
        linkedMap.put(new Lynx("Атак", 4.3), "Ярема");

        Map<Lynx, String> treeMap = new TreeMap<>(linkedMap);

        // Вивід колекції
        System.out.println("=== LinkedHashMap до сортування ===");
        printMap(linkedMap);

        System.out.println("=== TreeMap (автосортування) ===");
        printMap(treeMap);

        // Пошук за ключем
        Lynx searchKey = new Lynx("Пума", 3.8);
        measureTime("Пошук за ключем в LinkedHashMap", () -> {
            String owner = linkedMap.get(searchKey);
            System.out.println("Власник ключа " + searchKey + ": " + owner);
        });

        // Пошук за значенням
        String searchValue = "Святослав";
        measureTime("Пошук за значенням в LinkedHashMap", () -> {
            for (Entry<Lynx, String> e : linkedMap.entrySet()) {
                if (e.getValue().equals(searchValue)) {
                    System.out.println("Знайдено значення '" + searchValue + "' у ключа: " + e.getKey());
                }
            }
        });



        // Видалення за ключем
        measureTime("Видалення за ключем з LinkedHashMap", () -> linkedMap.remove(searchKey));

        // Видалення за значенням
        measureTime("Видалення за значенням з LinkedHashMap", () -> {
            linkedMap.entrySet().removeIf(entry -> entry.getValue().equals(searchValue));
        });

        // Сортування LinkedHashMap за ключами (для порівняння)
        measureTime("Сортування LinkedHashMap за ключами", () -> {
            Map<Lynx, String> sorted = new TreeMap<>(linkedMap);
            printMap(sorted);
        });
    }

    private static void printMap(Map<Lynx, String> map) {
        for (Entry<Lynx, String> e : map.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
        System.out.println();
    }

    private static void measureTime(String operation, Runnable task) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        System.out.println("=== Час операції '" + operation + "': " + (end - start) + " нс ===\n");
    }
}
