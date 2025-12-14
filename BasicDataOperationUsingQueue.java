import java.util.Queue;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.stream.IntStream;

public class BasicDataOperationUsingQueue {
    private float floatValueToSearch;
    private Float[] floatArray;
    private Queue<Float> floatQueue;

    BasicDataOperationUsingQueue(float floatValueToSearch, Float[] floatArray) {
        this.floatValueToSearch = floatValueToSearch;
        this.floatArray = floatArray;
        // Створення PriorityQueue з елементів масиву
        this.floatQueue = new PriorityQueue<>(Arrays.asList(floatArray));
    }

    public void runDataProcessing() {
        System.out.println("========= АНАЛІЗ QUEUE (PriorityQueue) =========");
        findInQueue();
        locateMinMaxInQueue();
        performQueueOperations(); // Ці операції (peek/poll) залишаються імперативними

        System.out.println("========= ДО СОРТУВАННЯ МАСИВУ =========");
        findInArray();
        locateMinMaxInArray();

        performArraySorting();

        System.out.println("========= ПІСЛЯ СОРТУВАННЯ МАСИВУ =========");
        findInArray();
        locateMinMaxInArray();

        DataFileHandler.writeArrayToFile(floatArray, BasicDataOperation.PATH_TO_DATA_FILE + ".sorted");
    }

    /**
     * Упорядковує масив елементів float, використовуючи Stream API.
     */
    private void performArraySorting() {
        long timeStart = System.nanoTime();
 record
        // 2.4.1 Сортування масиву
        this.floatArray = Arrays.stream(this.floatArray)
                                  .sorted()
                                  .toArray(Float[]::new);
        PerformanceTracker.displayOperationTime(timeStart, "упорядкування масиву float");


        dateTimeArray = Arrays.stream(dateTimeArray)
                              .sorted()
                              .toArray(LocalDateTime[]::new);

        PerformanceTracker.displayOperationTime(timeStart, "упорядкування масиву дати i часу");
 record
    }

    /**
     * Здійснює пошук елемента в масиві float, використовуючи Stream API.
     */
    private void findInArray() {
        long timeStart = System.nanoTime();
 record
        // 2.4.2 Пошук конкретного значення в масиві
        int position = IntStream.range(0, this.floatArray.length)
                                .filter(i -> floatValueToSearch == this.floatArray[i])
                                .findFirst()
                                .orElse(-1);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в масивi float");

        
        int position = Arrays.stream(dateTimeArray)
                .map(Arrays.asList(dateTimeArray)::indexOf)
                .filter(i -> dateTimeValueToSearch.equals(dateTimeArray[i]))
                .findFirst()
                .orElse(-1);
      
        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в масивi дати i часу");
 record

        if (position >= 0) {
            System.out.println("Елемент '" + floatValueToSearch + "' знайдено в масивi за позицією: " + position);
        } else {
            System.out.println("Елемент '" + floatValueToSearch + "' відсутній в масиві.");
        }
    }

    /**
     * Визначає найменше і найбільше значення в масиві, використовуючи Stream API.
     */
    private void locateMinMaxInArray() {
        if (floatArray == null || floatArray.length == 0) {
            System.out.println("Масив є пустим або не ініціалізованим.");
            return;
        }

        long timeStart = System.nanoTime();

 record
        // 2.4.3 Пошук мінімального і максимального значення в масиві (заміна ручного циклу)
        Float min = Arrays.stream(this.floatArray)
                           .min(Float::compareTo)
                           .orElse(null);

        Float max = Arrays.stream(this.floatArray)
                           .max(Float::compareTo)
                           .orElse(null);

        // Використовуємо Stream API для пошуку мінімуму та максимуму
        LocalDateTime minValue = Arrays.stream(dateTimeArray)
                .min(LocalDateTime::compareTo)
                .orElse(null);
        
        LocalDateTime maxValue = Arrays.stream(dateTimeArray)
                .max(LocalDateTime::compareTo)
                .orElse(null);
 record

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмального i максимального значення в масивi");

        System.out.println("Найменше значення в масивi: " + min);
        System.out.println("Найбільше значення в масивi: " + max);
    }

    /**
     * Пошук конкретного значення в черзі, використовуючи Stream API.
     */
    private void findInQueue() {
        long timeStart = System.nanoTime();
        
        // 2.5.3 Пошук конкретного значення в черзі (заміна .contains)
        boolean elementExists = this.floatQueue.stream()
            .anyMatch(f -> f.equals(floatValueToSearch)); // Використання anyMatch

 record
        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в PriorityQueue float");

        boolean elementExists = dateTimeQueue.stream()
            .anyMatch(dateTime -> dateTime.equals(dateTimeValueToSearch));

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в Queue дати i часу");
 record

        if (elementExists) {
            System.out.println("Елемент '" + floatValueToSearch + "' знайдено в PriorityQueue");
        } else {
            System.out.println("Елемент '" + floatValueToSearch + "' відсутній в PriorityQueue.");
        }
    }

    /**
     * Пошук мінімального і максимального значення в черзі, використовуючи Stream API.
     */
    private void locateMinMaxInQueue() {
        if (floatQueue == null || floatQueue.isEmpty()) {
            System.out.println("Черга є пустою або не ініціалізованою.");
            return;
        }

        long timeStart = System.nanoTime();

 record
        // 2.5.3 Пошук мінімального та максимального значення в черзі
        Float minValue = floatQueue.stream()
                .min(Float::compareTo)
                .orElse(null);
        
        Float maxValue = floatQueue.stream()
                .max(Float::compareTo)

        // Використовуємо Stream API для пошуку мінімуму та максимуму
        LocalDateTime minValue = dateTimeQueue.stream()
                .min(LocalDateTime::compareTo)
                .orElse(null);
        
        LocalDateTime maxValue = dateTimeQueue.stream()
                .max(LocalDateTime::compareTo)
 record
                .orElse(null);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмального i максимального значення в PriorityQueue");

        System.out.println("Найменше значення в PriorityQueue: " + minValue);
        System.out.println("Найбільше значення в PriorityQueue: " + maxValue);
    }

    /**
     * Операції peek/poll залишаються імперативними, оскільки вони змінюють стан черги
     * і не мають прямого функціонального аналога, що повертає чергу.
     */
    private void performQueueOperations() {
        if (floatQueue == null || floatQueue.isEmpty()) {
            System.out.println("Черга є пустою або не ініціалізованою.");
            return;
        }

        Float headElement = floatQueue.peek();
        System.out.println("Головний елемент черги (peek): " + headElement);

        headElement = floatQueue.poll();
        System.out.println("Видалений елемент черги (poll): " + headElement);

        headElement = floatQueue.peek();
        System.out.println("Новий головний елемент черги: " + headElement);
    }
}