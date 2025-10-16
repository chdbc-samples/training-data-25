import java.util.*;

public class BasicDataOperationUsingQueue {

    public static void runPriorityQueueDemo(float[] array) {
        PriorityQueue<Float> queue = new PriorityQueue<>();
        for (float value : array) {
            queue.add(value);
        }

        System.out.println("Мінімум у PriorityQueue: " + queue.peek());

        PriorityQueue<Float> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
        maxQueue.addAll(queue);
        System.out.println("Максимум у PriorityQueue: " + maxQueue.peek());
    }
}
