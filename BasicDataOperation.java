import java.util.*;

public class BasicDataOperation {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Потрібно передати значення для пошуку, наприклад: java BasicDataOperation 891655.4");
            return;
        }

        float searchValue = Float.parseFloat(args[0]);

        float[] array = DataFileHandler.readDataToArray();

        System.out.println("=== Операції з масивом ===");
        BasicDataOperationUsingList.findMinAndMaxInArray(array);
        BasicDataOperationUsingList.searchInArray(array, searchValue);

        System.out.println("\n=== Операції з LinkedList ===");
        BasicDataOperationUsingList.runLinkedListDemo(array, searchValue);

        System.out.println("\n=== Операції з HashSet ===");
        BasicDataOperationUsingSet.runHashSetDemo(array, searchValue);

        System.out.println("\n=== Операції з PriorityQueue ===");
        BasicDataOperationUsingQueue.runPriorityQueueDemo(array);
    }
}
