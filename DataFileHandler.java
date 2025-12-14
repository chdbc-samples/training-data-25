import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
 functional-programming

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
 functional-programming
import java.util.Arrays;
import java.util.stream.Collectors;

public class DataFileHandler {

    /**
     * Завантажує масив об'єктів Float з файлу, використовуючи Stream API.
     *
     * @param filePath Шлях до файлу з даними.
     * @return Масив об'єктів Float.
     */
 functional-programming
    public static Float[] loadArrayFromFile(String filePath) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            return fileReader.lines() // Отримуємо потік рядків з файлу
                    .map(currentLine -> currentLine.trim().replaceAll("^\\uFEFF", "")) // Очищаємо пробіли та BOM
                    .filter(currentLine -> !currentLine.isEmpty()) // Видаляємо порожні рядки
                    .map(currentLine -> Float.parseFloat(currentLine)) // Конвертуємо кожен рядок у Float
                    .toArray(Float[]::new); // Збираємо результат у масив Float[]
        } catch (IOException ioException) {
            // Замінюємо перехоплення на RuntimeException, як у прикладі вказівки
            throw new RuntimeException("Помилка читання даних з файлу: " + filePath, ioException);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Помилка конвертації даних у Float: " + filePath, e);

    public static LocalDateTime[] loadArrayFromFile(String filePath) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            return fileReader.lines()
                    .map(currentLine -> currentLine.trim().replaceAll("^\\uFEFF", ""))
                    .filter(currentLine -> !currentLine.isEmpty())
                    .map(currentLine -> LocalDateTime.parse(currentLine, timeFormatter))
                    .toArray(LocalDateTime[]::new);
        } catch (IOException ioException) {
            throw new RuntimeException("Помилка читання даних з файлу: " + filePath, ioException);
functional-programming
        }
    }

    /**
     * Зберігає масив об'єктів Float у файл, використовуючи Stream API.
     *
     * @param floatArray Масив об'єктів Float.
     * @param filePath   Шлях до файлу для збереження.
     */
    public static void writeArrayToFile(Float[] floatArray, String filePath) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath))) {
functional-programming
            // Конвертуємо масив у потік
            String content = Arrays.stream(floatArray)
                    // Конвертуємо кожен елемент Float у String, використовуючи String::valueOf
                    .map(String::valueOf)
                    // Об'єднуємо всі рядки, розділяючи системним роздільником
                    .collect(Collectors.joining(System.lineSeparator()));

            fileWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Помилка запису даних у файл: " + filePath, e);

            String content = Arrays.stream(dateTimeArray)
                    .map(LocalDateTime::toString)
                    .collect(Collectors.joining(System.lineSeparator()));
            
            fileWriter.write(content);
        } catch (IOException ioException) {
            ioException.printStackTrace();
 functional-programming
        }
    }
}