import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Клас BasicDataOperationUsingList реалізує операції з колекціями типу LinkedList для даних float,
 * використовуючи функціональне програмування (Stream API).
 * * <p>Методи класу:</p>
 * <ul>
 * <li>{@link #executeDataOperations()} - Виконує комплекс операцій з даними.</li>
 * <li>{@link #performArraySorting()} - Упорядковує масив елементів float (Stream API).</li>
 * <li>{@link #findInArray()} - Здійснює пошук елемента в масиві float (Stream API).</li>
 * <li>{@link #locateMinMaxInArray()} - Визначає найменше і найбільше значення в масиві (Stream API).</li>
 * <li>{@link #sortList()} - Сортує колекцію List з float (Stream API).</li>
 * <li>{@link #findInList()} - Пошук конкретного значення в списку (Stream API).</li>
 * <li>{@link #locateMinMaxInList()} - Пошук мінімального і максимального значення в списку (Stream API).</li>
 * </ul>
 */
public class BasicDataOperationUsingList {
    private float floatValueToSearch;
    private Float[] floatArray;
    private List<Float> floatList;

    BasicDataOperationUsingList(float floatValueToSearch, Float[] floatArray) {
        this.floatValueToSearch = floatValueToSearch;
        this.floatArray = floatArray;
        // Використання Arrays.asList(floatArray) тут є правильним, оскільки Float[] є масивом об'єктів.
        this.floatList = new LinkedList<>(Arrays.asList(floatArray)); 
    }

    public void executeDataOperations() {
        // працюємо зі списком
        System.out.println("========= ДО СОРТУВАННЯ LIST =========");
        findInList();
        locateMinMaxInList();

        sortList();

        System.out.println("========= ПІСЛЯ СОРТУВАННЯ LIST =========");
        findInList();
        locateMinMaxInList();

        // працюємо з масивом
        System.out.println("========= ДО СОРТУВАННЯ МАСИВУ =========");
        findInArray();
        locateMinMaxInArray();

        performArraySorting();

        System.out.println("========= ПІСЛЯ СОРТУВАННЯ МАСИВУ =========");
        findInArray();
        locateMinMaxInArray();

        // зберігаємо відсортований масив
        DataFileHandler.writeArrayToFile(floatArray, BasicDataOperation.PATH_TO_DATA_FILE + ".sorted");
    }

    /**
     * Упорядковує масив елементів float, використовуючи Stream API.
     */
    void performArraySorting() {
        long timeStart = System.nanoTime();

        // 2.4.1 Сортування масиву
        this.floatArray = Arrays.stream(this.floatArray)
                                  .sorted()
                                  .toArray(Float[]::new);

        PerformanceTracker.displayOperationTime(timeStart, "упорядкування масиву float");
    }

    /**
     * Здійснює пошук елемента в масиві float, використовуючи Stream API.
     */
    void findInArray() {
        long timeStart = System.nanoTime();

        // 2.4.2 Пошук конкретного значення в масиві
        // Використовуємо IntStream для ітерації по індексах і пошуку позиції елемента.
        int position = IntStream.range(0, this.floatArray.length)
                                .filter(i -> floatValueToSearch == this.floatArray[i])
                                .findFirst()
                                .orElse(-1);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в масивi float");

        if (position >= 0) {
            System.out.println("Елемент '" + floatValueToSearch + "' знайдено в масивi за позицією: " + position);
        } else {
            System.out.println("Елемент '" + floatValueToSearch + "' відсутній в масиві.");
        }
    }

    /**
     * Визначає найменше і найбільше значення в масиві, використовуючи Stream API.
     */
    void locateMinMaxInArray() {
        if (floatArray == null || floatArray.length == 0) {
            System.out.println("Масив є пустим або не ініціалізованим.");
            return;
        }

        long timeStart = System.nanoTime();

        // 2.4.3 Пошук мінімального значення
        Float min = Arrays.stream(this.floatArray)
                           .min(Float::compareTo)
                           .orElse(null);

        // 2.4.3 Пошук максимального значення
        Float max = Arrays.stream(this.floatArray)
                           .max(Float::compareTo)
                           .orElse(null);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмального i максимального значення в масивi");

        System.out.println("Найменше значення в масивi: " + min);
        System.out.println("Найбільше значення в масивi: " + max);
    }

    /**
     * Пошук конкретного значення в списку, використовуючи Stream API.
     */
    void findInList() {
        long timeStart = System.nanoTime();

        // 2.5.1 Пошук значення в списку
        // Використовуємо Stream для пошуку позиції елемента.
        int position = IntStream.range(0, this.floatList.size())
                                .filter(i -> floatValueToSearch == this.floatList.get(i))
                                .findFirst()
                                .orElse(-1);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в List float");

        if (position >= 0) {
            System.out.println("Елемент '" + floatValueToSearch + "' знайдено в LinkedList за позицією: " + position);
        } else {
            System.out.println("Елемент '" + floatValueToSearch + "' відсутній в LinkedList.");
        }
    }

    /**
     * Пошук мінімального і максимального значення в списку, використовуючи Stream API.
     */
    void locateMinMaxInList() {
        if (floatList == null || floatList.isEmpty()) {
            System.out.println("Колекція LinkedList є пустою або не ініціалізованою.");
            return;
        }

        long timeStart = System.nanoTime();

        // Пошук мінімального значення
        Float min = this.floatList.stream()
                                  .min(Float::compareTo)
                                  .orElse(null);

        // Пошук максимального значення
        Float max = this.floatList.stream()
                                  .max(Float::compareTo)
                                  .orElse(null);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмального i максимального значення в List");

        System.out.println("Найменше значення в List: " + min);
        System.out.println("Найбільше значення в List: " + max);
    }

    /**
     * Сортує колекцію List з float, використовуючи Stream API.
     */
    void sortList() {
        long timeStart = System.nanoTime();

        // 2.5.1 Сортування List (для LinkedList, ArrayList)
        this.floatList = this.floatList.stream()
                                       .sorted()
                                       .collect(Collectors.toCollection(LinkedList::new));
                                       
        PerformanceTracker.displayOperationTime(timeStart, "упорядкування LinkedList float");
    }
}