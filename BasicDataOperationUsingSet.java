import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * Клас BasicDataOperationUsingSet реалізує операції з множиною HashSet для float,
 * використовуючи функціональне програмування (Stream API).
 */
public class BasicDataOperationUsingSet {
    private float floatValueToSearch;
    private Float[] floatArray;
    private Set<Float> floatSet;

    BasicDataOperationUsingSet(float floatValueToSearch, Float[] floatArray) {
        this.floatValueToSearch = floatValueToSearch;
        this.floatArray = floatArray;
        this.floatSet = new HashSet<>(Arrays.asList(floatArray));
    }

    public void executeDataAnalysis() {
        System.out.println("========= АНАЛІЗ SET (HashSet) =========");
        // спочатку аналізуємо множину
        findInSet();
        locateMinMaxInSet();
        analyzeArrayAndSet();

        System.out.println("========= ДО СОРТУВАННЯ МАСИВУ =========");
        // потім масив
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

        // 2.4.1 Сортування масиву
        this.floatArray = Arrays.stream(this.floatArray)
                                  .sorted()
                                  .toArray(Float[]::new);

        PerformanceTracker.displayOperationTime(timeStart, "упорядкування масиву float");
    }

    /**
     * Здійснює пошук елемента в масиві float, використовуючи Stream API.
     */
    private void findInArray() {
        long timeStart = System.nanoTime();

        // Функціональний пошук позиції елемента (заміна Arrays.binarySearch)
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
    private void locateMinMaxInArray() {
        if (floatArray == null || floatArray.length == 0) {
            System.out.println("Масив є пустим або не ініціалізованим.");
            return;
        }

        long timeStart = System.nanoTime();

        // 2.4.3 Пошук мінімального і максимального значення в масиві (заміна ручного циклу)
        Float min = Arrays.stream(this.floatArray)
                           .min(Float::compareTo)
                           .orElse(null);

        Float max = Arrays.stream(this.floatArray)
                           .max(Float::compareTo)
                           .orElse(null);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмального i максимального значення в масивi");

        System.out.println("Найменше значення в масивi: " + min);
        System.out.println("Найбільше значення в масивi: " + max);
    }

    /**
     * Пошук конкретного значення в множині, використовуючи Stream API.
     */
    private void findInSet() {
        long timeStart = System.nanoTime();

        // 2.5.2 Пошук конкретного значення (заміна .contains)
        boolean elementExists = this.floatSet.stream()
            .anyMatch(f -> f.equals(floatValueToSearch));

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в HashSet float");

        if (elementExists) {
            System.out.println("Елемент '" + floatValueToSearch + "' знайдено в HashSet");
        } else {
            System.out.println("Елемент '" + floatValueToSearch + "' відсутній в HashSet.");
        }
    }

    /**
     * Пошук мінімального і максимального значення в множині, використовуючи Stream API.
     */
    private void locateMinMaxInSet() {
        if (floatSet == null || floatSet.isEmpty()) {
            System.out.println("HashSet є пустим або не ініціалізованим.");
            return;
        }

        long timeStart = System.nanoTime();

        // 2.5.2 Пошук мінімального та максимального значення (заміна Collections.min/max)
        Float minValue = floatSet.stream()
                .min(Float::compareTo)
                .orElse(null);

        Float maxValue = floatSet.stream()
                .max(Float::compareTo)
                .orElse(null);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмального i максимального значення в HashSet");

        System.out.println("Найменше значення в HashSet: " + minValue);
        System.out.println("Найбільше значення в HashSet: " + maxValue);
    }

    /**
     * Порівняння масиву та множини, використовуючи Stream API.
     */
    private void analyzeArrayAndSet() {
        System.out.println("Кiлькiсть елементiв в масивi: " + floatArray.length);
        System.out.println("Кiлькiсть елементiв в HashSet: " + floatSet.size());

        // 2.5.2 Порівняння масиву та множини (заміна циклу for)
        boolean allElementsPresent = Arrays.stream(floatArray)
                .allMatch(floatSet::contains);

        if (allElementsPresent) {
            System.out.println("Всi елементи масиву наявні в HashSet.");
        } else {
            System.out.println("Не всi елементи масиву наявні в HashSet.");
        }
    }
}