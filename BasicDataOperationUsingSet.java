import java.util.*;

public class BasicDataOperationUsingSet {
    private short shortValueToSearch;
    private short[] shortArray;
    private Set<Short> shortSet;

    BasicDataOperationUsingSet(short shortValueToSearch, short[] shortArray) {
        this.shortValueToSearch = shortValueToSearch;
        this.shortArray = shortArray;
        this.shortSet = new LinkedHashSet<>();
        for (short s : shortArray) shortSet.add(s);
    }

    public void executeDataAnalysis() {
        findInSet();
        locateMinMaxInSet();
        analyzeArrayAndSet();

        findInArray();
        locateMinMaxInArray();
        performArraySorting();
        findInArray();
        locateMinMaxInArray();

        DataFileHandler.writeArrayToFile(shortArray, BasicDataOperation.PATH_TO_DATA_FILE + ".sorted");
    }

    private void performArraySorting() {
        long start = System.nanoTime();
        Arrays.sort(shortArray);
        PerformanceTracker.displayOperationTime(start, "упорядкування масиву short");
    }

    private void findInArray() {
        long start = System.nanoTime();
        int pos = Arrays.binarySearch(shortArray, shortValueToSearch);
        PerformanceTracker.displayOperationTime(start, "пошук елемента в масиві short");
        System.out.println(pos >= 0 ? "Елемент знайдено у масиві на позиції: " + pos : "Елемент відсутній у масиві.");
    }

    private void locateMinMaxInArray() {
        if (shortArray.length == 0) return;
        long start = System.nanoTime();
        short min = shortArray[0], max = shortArray[0];
        for (short s : shortArray) {
            if (s < min) min = s;
            if (s > max) max = s;
        }
        PerformanceTracker.displayOperationTime(start, "визначення min і max у масиві short");
        System.out.println("Min: " + min + " | Max: " + max);
    }

    private void findInSet() {
        long start = System.nanoTime();
        boolean found = shortSet.contains(shortValueToSearch);
        PerformanceTracker.displayOperationTime(start, "пошук елемента в LinkedHashSet short");
        System.out.println(found ? "Елемент знайдено у Set." : "Елемент відсутній у Set.");
    }

    private void locateMinMaxInSet() {
        if (shortSet.isEmpty()) return;
        long start = System.nanoTime();
        short min = Collections.min(shortSet);
        short max = Collections.max(shortSet);
        PerformanceTracker.displayOperationTime(start, "визначення min і max у LinkedHashSet short");
        System.out.println("Min: " + min + " | Max: " + max);
    }

    private void analyzeArrayAndSet() {
        System.out.println("Елементів у масиві: " + shortArray.length);
        System.out.println("Елементів у Set: " + shortSet.size());
    }
}
