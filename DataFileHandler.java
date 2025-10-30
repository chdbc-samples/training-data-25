import java.io.*;
import java.util.*;

public class DataFileHandler {

    public static final String PATH_TO_DATA_FILE = "list/float.data";

    public static float[] readDataToArray() {
        List<Float> tempList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_DATA_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim(); // приберемо пробіли
                if (line.isEmpty()) continue; // пропускаємо порожні рядки
                try {
                    float value = Float.parseFloat(line);
                    tempList.add(value);
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Пропущено рядок: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        float[] array = new float[tempList.size()];
        for (int i = 0; i < tempList.size(); i++) {
            array[i] = tempList.get(i);
        }
        return array;
    }

    public static void writeArrayToFile(float[] array, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (float value : array) {
                bw.write(Float.toString(value));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
