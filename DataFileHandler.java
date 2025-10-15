import java.io.*;
import java.util.*;

public class DataFileHandler {

    public static short[] loadArrayFromFile(String filePath) {
        short[] tempArray = new short[1000];
        int index = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                currentLine = currentLine.trim().replaceAll("^\\uFEFF", "");
                if (!currentLine.isEmpty()) {
                    short parsedValue = Short.parseShort(currentLine);
                    tempArray[index++] = parsedValue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Arrays.copyOf(tempArray, index);
    }

    public static void writeArrayToFile(short[] array, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (short value : array) {
                writer.write(Short.toString(value));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
