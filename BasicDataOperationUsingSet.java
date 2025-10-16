import java.util.*;

public class BasicDataOperationUsingSet {

    public static void runHashSetDemo(float[] array, float searchValue) {
        HashSet<Float> set = new HashSet<>();
        for (float value : array) {
            set.add(value);
        }

        float min = Float.MAX_VALUE;
        float max = Float.MIN_VALUE;
        for (float value : set) {
            if (value < min) min = value;
            if (value > max) max = value;
        }

        System.out.println("Мінімум у HashSet: " + min);
        System.out.println("Максимум у HashSet: " + max);

        boolean found = set.contains(searchValue);
        System.out.println("Значення " + searchValue + (found ? " знайдено" : " не знайдено") + " у HashSet.");
    }
}
