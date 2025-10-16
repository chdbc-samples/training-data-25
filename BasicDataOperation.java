import java.util.*;

public class BasicDataOperation {
 main
    static final String PATH_TO_DATA_FILE = "list/LocalDateTime.data";

    LocalDateTime dateTimeValueToSearch;
    LocalDateTime[] dateTimeArray;

    private static final String SEPARATOR = "\n" + "=".repeat(80) + "\n";
    private static final String USAGE_MESSAGE = "Використання: java BasicDataOperation <пошукове-значення> \n" +
"Приклад:\n" +
"  java BasicDataOperation \"2025-01-02T20:42:25Z\"";
main

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
