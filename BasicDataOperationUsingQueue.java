import java.util.*;

public class BasicDataOperationUsingQueue {
    private short shortValueToSearch;
    private short[] shortArray;
    private Queue<Short> shortQueue;

    BasicDataOperationUsingQueue(short shortValueToSearch, short[] shortArray) {
        this.shortValueToSearch = shortValueToSearch;
        this.shortArray = shortArray;
        this.shortQueue = new PriorityQueue<>();
        for (short s : shortArray) shortQueue.add(s);
    }

    public void runDataProcessing() {
        findInQueue();
        locateMinMaxInQueue();
        performQueueOperations();

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
        System.out.println(pos >= 0 ? "Елемент знайдено у масиві: " + pos : "Елемент відсутній у масиві.");
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

    private void findInQueue() {
        long start = System.nanoTime();
        boolean found = shortQueue.contains(shortValueToSearch);
        PerformanceTracker.displayOperationTime(start, "пошук елемента в Queue short");
        System.out.println(found ? "Елемент знайдено у Queue" : "Елемент відсутній у Queue");
    }

    private void locateMinMaxInQueue() {
        if (shortQueue.isEmpty()) return;
        long start = System.nanoTime();
        short min = Collections.min(shortQueue);
        short max = Collections.max(shortQueue);
        PerformanceTracker.displayOperationTime(start, "визначення min і max у Queue short");
        System.out.println("Min: " + min + " | Max: " + max);
    }

    private void performQueueOperations() {
        if (shortQueue.isEmpty()) return;
        short peek = shortQueue.peek();
        System.out.println("Перший елемент (peek): " + peek);
        shortQueue.poll();
        System.out.println("Після poll новий перший: " + shortQueue.peek());
    }
}
