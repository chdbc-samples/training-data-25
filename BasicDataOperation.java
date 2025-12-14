import java.util.Arrays;

/**
 * Загальний клас BasicDataOperation координує роботу різних структур даних для float.
 * 
 * <p>Основні можливості:</p>
 * <ul>
 *   <li>Координація операцій з різними типами колекцій</li>  
 *   <li>Порівняльний аналіз продуктивності структур даних</li>
 *   <li>Централізоване управління обробкою даних</li>
 * </ul>
 */
public class BasicDataOperation {
    static final String PATH_TO_DATA_FILE = "list/float.data";

    Float floatValueToSearch;
    Float[] floatArray;

    private static final String SEPARATOR = "\n" + "=".repeat(80) + "\n";
    private static final String USAGE_MESSAGE = "Використання: java BasicDataOperation <число float> \n" +
            "Приклад:\n" +
            "  java BasicDataOperation 891655.4";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(USAGE_MESSAGE);
            return;
        }

        BasicDataOperation coordinator = new BasicDataOperation();
        coordinator.executeOperations(args);
    }

    private void executeOperations(String[] args) {
        System.out.println(SEPARATOR);
        System.out.println("🚀 РОЗПОЧАТО АНАЛІЗ ДАНИХ float 🚀");
        System.out.println("Пошуковий параметр: " + args[0]);
        System.out.println(SEPARATOR);

        // Перевірка аргументу та конвертація в float
        try {
            floatValueToSearch = Float.parseFloat(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Помилка: Введене значення має бути числом float (наприклад: 891655.4)");
            return;
        }

        // Завантаження даних
        floatArray = DataFileHandler.loadArrayFromFile(PATH_TO_DATA_FILE);

        runAllOperations();

        System.out.println(SEPARATOR);
        System.out.println("✅ АНАЛІЗ ЗАВЕРШЕНО ✅");
        System.out.println(SEPARATOR);
    }

    private void runListOperations() {
        System.out.println("📋 ОБРОБКА ДАНИХ З ВИКОРИСТАННЯМ LIST");
        System.out.println("-".repeat(50));

        try {
            BasicDataOperationUsingList listProcessor = new BasicDataOperationUsingList(floatValueToSearch, floatArray);
            listProcessor.executeDataOperations();
        } catch (Exception e) {
            System.out.println("❌ Помилка при роботі з List: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void runQueueOperations() {
        System.out.println("🔄 ОБРОБКА ДАНИХ З ВИКОРИСТАННЯМ QUEUE");
        System.out.println("-".repeat(50));

        try {
            BasicDataOperationUsingQueue queueProcessor = new BasicDataOperationUsingQueue(floatValueToSearch, floatArray);
            queueProcessor.runDataProcessing();
        } catch (Exception e) {
            System.out.println("❌ Помилка при роботі з Queue: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void runSetOperations() {
        System.out.println("🔍 ОБРОБКА ДАНИХ З ВИКОРИСТАННЯМ SET");
        System.out.println("-".repeat(50));

        try {
            BasicDataOperationUsingSet setProcessor = new BasicDataOperationUsingSet(floatValueToSearch, floatArray);
            setProcessor.executeDataAnalysis();
        } catch (Exception e) {
            System.out.println("❌ Помилка при роботі з Set: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void runAllOperations() {
        System.out.println("🎯 КОМПЛЕКСНИЙ АНАЛІЗ ВСІХ СТРУКТУР ДАНИХ");
        System.out.println("=".repeat(60));

        runListOperations();
        System.out.println("\n" + "~".repeat(60) + "\n");

        runQueueOperations();
        System.out.println("\n" + "~".repeat(60) + "\n");

        runSetOperations();
    }
}
