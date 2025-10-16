import java.util.*;

public class BasicDataOperationUsingList {

    public static void findMinAndMaxInArray(float[] array) {
        float min = array[0];
        float max = array[0];
        for (float value : array) {
            if (value < min) min = value;
            if (value > max) max = value;
        }
        System.out.println("Мінімум у масиві: " + min);
        System.out.println("Максимум у масиві: " + max);
    }

    public static void searchInArray(float[] array, float searchValue) {
        boolean found = false;
        for (float value : array) {
            if (value == searchValue) {
                found = true;
                break;
            }
        }
        System.out.println("Значення " + searchValue + (found ? " знайдено" : " не знайдено") + " у масиві.");
    }

    public static void runLinkedListDemo(float[] array, float searchValue) {
        LinkedList<Float> list = new LinkedList<>();
        for (float value : array) {
            list.add(value);
        }

        float min = list.get(0);
        float max = list.get(0);
        for (float value : list) {
            if (value < min) min = value;
            if (value > max) max = value;
        }
        System.out.println("Мінімум у LinkedList: " + min);
        System.out.println("Максимум у LinkedList: " + max);

        boolean found = list.contains(searchValue);
        System.out.println("Значення " + searchValue + (found ? " знайдено" : " не знайдено") + " у LinkedList.");
    }
}
