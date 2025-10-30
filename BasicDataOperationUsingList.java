import java.util.*;

public class BasicDataOperationUsingList {
    private short shortValueToSearch;
    private short[] shortArray;
    private List<Short> shortList;

    BasicDataOperationUsingList(short shortValueToSearch, short[] shortArray) {
        this.shortValueToSearch = shortValueToSearch;
        this.shortArray = shortArray;
        this.shortList = new ArrayList<>();
        for (short s : shortArray) shortList.add(s);
    }

    public void executeDataOperations() {
        findInList();
        locateMinMaxInList();

        sortList();
        findInList();
        locateMinMaxInList();

        findInArray();
        locateMinMaxInArray();

        performArraySorting();
        findInArray();
        locateMinMaxInArray();

        DataFileHandler.writeArrayToFile(shortArray, BasicDataOperation.PATH_TO_DATA_FILE + ".sorted");
    }

    void performArraySorting() {
        long start = System.nanoTime();
        Arrays.sort(shortArray);
        PerformanceTracker.displayOperationTime(start, "упорядкування масиву short");
    }

    void findInArray() {
        long start = System.nanoTime();
        int pos = Arrays.binarySearch(shortArray, shortValueToSearch);
        PerformanceTracker.displayOperationTime(start, "пошук елемента в масиві short");
        if (pos >= 0)
            System.out.println("Елемент '" + shortValueToSearch + "' знайдено на позиції: " + pos);
        else
            System.out.println("Елемент '" + shortValueToSearch + "' відсутній у масиві.");
    }

    void locateMinMaxInArray() {
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

    void findInList() {
        long start = System.nanoTime();
        int pos = Collections.binarySearch(shortList, shortValueToSearch);
        PerformanceTracker.displayOperationTime(start, "пошук елемента в ArrayList short");
        if (pos >= 0)
            System.out.println("Елемент '" + shortValueToSearch + "' знайдено у списку на позиції: " + pos);
        else
            System.out.println("Елемент '" + shortValueToSearch + "' відсутній у списку.");
    }

    void locateMinMaxInList() {
        if (shortList.isEmpty()) return;
        long start = System.nanoTime();
        short min = Collections.min(shortList);
        short max = Collections.max(shortList);
        PerformanceTracker.displayOperationTime(start, "визначення min і max у ArrayList short");
        System.out.println("Min: " + min + " | Max: " + max);
    }

    void sortList() {
        long start = System.nanoTime();
        Collections.sort(shortList);
        PerformanceTracker.displayOperationTime(start, "упорядкування ArrayList short");
    }
}
