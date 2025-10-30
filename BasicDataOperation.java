import java.util.*;

public class BasicDataOperation {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Будь ласка, введіть число для пошуку як аргумент командного рядка.");
            return;
        }

        double searchValue;
        try {
            searchValue = Double.parseDouble(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Помилка: аргумент має бути числом.");
            return;
        }

        // Генерация данных
        double[] dataArray = generateData();
        LinkedList<Double> linkedList = new LinkedList<>();
        HashSet<Double> hashSet = new HashSet<>();
        PriorityQueue<Double> priorityQueue = new PriorityQueue<>();

        for (double value : dataArray) {
            linkedList.add(value);
            hashSet.add(value);
            priorityQueue.add(value);
        }

        // Проверка пропущенных значений
        checkMissingValues(dataArray);

        System.out.println("\n=== Операції з масивом ===");
        processArray(dataArray, searchValue);

        System.out.println("\n=== Операції з LinkedList ===");
        processLinkedList(linkedList, searchValue);

        System.out.println("\n=== Операції з HashSet ===");
        processHashSet(hashSet, searchValue);

        System.out.println("\n=== Операції з PriorityQueue ===");
        processPriorityQueue(priorityQueue, searchValue);
    }

    private static double[] generateData() {
        return new double[]{
            891655.4, -987395.5, 974064.4, -86090.73, 123.45,
            -567.89, 3333.33, -8888.88, 777.77, -999.99
        };
    }

    private static void checkMissingValues(double[] data) {
        double[] expected = {891655.4, -987395.5, 974064.4, -86090.73};
        for (double expectedValue : expected) {
            boolean found = false;
            for (double actualValue : data) {
                if (Math.abs(actualValue - expectedValue) < 0.001) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("⚠️ Пропущено рядок: " + expectedValue);
            }
        }
    }

    private static void processArray(double[] array, double searchValue) {
        // Поиск минимума и максимума
        double min = array[0];
        double max = array[0];
        boolean found = false;

        for (double value : array) {
            if (value < min) min = value;
            if (value > max) max = value;
            if (Math.abs(value - searchValue) < 0.001) {
                found = true;
            }
        }

        System.out.println("Мінімум у масиві: " + min);
        System.out.println("Максимум у масиві: " + max);
        System.out.println("Значення " + searchValue + (found ? " знайдено у масиві." : " не знайдено у масиві."));

        // СОРТИРОВКА массива
        System.out.println("\n--- Сортування масиву ---");
        double[] sortedArray = array.clone();
        Arrays.sort(sortedArray);
        System.out.println("Відсортований масив: " + Arrays.toString(sortedArray));
    }

    private static void processLinkedList(LinkedList<Double> list, double searchValue) {
        // Поиск минимума и максимума
        double min = Collections.min(list);
        double max = Collections.max(list);
        boolean found = list.contains(searchValue);

        System.out.println("Мінімум у LinkedList: " + min);
        System.out.println("Максимум у LinkedList: " + max);
        System.out.println("Значення " + searchValue + (found ? " знайдено у LinkedList." : " не знайдено у LinkedList."));

        // СОРТИРОВКА LinkedList
        System.out.println("\n--- Сортування LinkedList ---");
        LinkedList<Double> sortedList = new LinkedList<>(list);
        Collections.sort(sortedList);
        System.out.println("Відсортований LinkedList: " + sortedList);
    }

    private static void processHashSet(HashSet<Double> set, double searchValue) {
        // Поиск минимума и максимума
        double min = Collections.min(set);
        double max = Collections.max(set);
        boolean found = set.contains(searchValue);

        System.out.println("Мінімум у HashSet: " + min);
        System.out.println("Максимум у HashSet: " + max);
        System.out.println("Значення " + searchValue + (found ? " знайдено у HashSet." : " не знайдено у HashSet."));

        // СОРТИРОВКА HashSet (через List)
        System.out.println("\n--- Сортування HashSet ---");
        List<Double> sortedList = new ArrayList<>(set);
        Collections.sort(sortedList);
        System.out.println("Відсортований HashSet: " + sortedList);
    }

    private static void processPriorityQueue(PriorityQueue<Double> queue, double searchValue) {
        // PriorityQueue уже частично отсортирован
        if (queue.isEmpty()) {
            System.out.println("Черга пріоритетів порожня.");
            return;
        }

        // Поиск минимума (первый элемент)
        double min = queue.peek();
        
        // Поиск максимума требует обхода всех элементов
        double max = min;
        boolean found = false;
        for (Double value : queue) {
            if (value > max) max = value;
            if (Math.abs(value - searchValue) < 0.001) {
                found = true;
            }
        }

        System.out.println("Мінімум у PriorityQueue: " + min);
        System.out.println("Максимум у PriorityQueue: " + max);
        System.out.println("Значення " + searchValue + (found ? " знайдено у PriorityQueue." : " не знайдено у PriorityQueue."));

        // СОРТИРОВКА PriorityQueue (извлечение в отсортированном порядке)
        System.out.println("\n--- Сортування PriorityQueue ---");
        PriorityQueue<Double> tempQueue = new PriorityQueue<>(queue);
        List<Double> sortedList = new ArrayList<>();
        while (!tempQueue.isEmpty()) {
            sortedList.add(tempQueue.poll());
        }
        System.out.println("Відсортований PriorityQueue: " + sortedList);
    }
}