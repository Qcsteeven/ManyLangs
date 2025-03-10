import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        String inputFilePath1 = "example1.txt";
        String inputFilePath2 = "example2.txt";
        String inputFilePath3 = "example3.txt";

        // Обработка файлов и сохранение результатов
        String result1 = processFile(inputFilePath1);
        String result2 = processFile(inputFilePath2);
        String result3 = processFile(inputFilePath3);

        System.out.println(findMinimalUniqueSubstring1(result1, result1));
    }

    // Метод для обработки файла и возврата результата
    private static String processFile(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Удаление пробелов, переносов строк и цифр
        return content.toString().replaceAll("[\\s\\d]", "");
    }

    private static String findMinimalUniqueSubstring(String first, String second) {
        String currentMinimal = null;
        System.out.println(first.length());
        for (int length = 1; length <= first.length(); length++) {
            System.out.println(length);
            for (int start = 0; start <= first.length() - length; start++) {
                String substring = first.substring(start, start + length);
                if (!second.contains(substring)) {
                    if (currentMinimal == null || substring.length() < currentMinimal.length()) {
                        currentMinimal = substring;
                    }
                }
            }
        }
        return currentMinimal != null ? currentMinimal : "";
    }

    private static String findMinimalUniqueSubstring1(String first, String second) {
        if (first == null || second == null || first.isEmpty() || second.isEmpty()) {
            return ""; // Возвращаем пустую строку, если одна из строк пустая или null
        }
    
        int minimum = Integer.MAX_VALUE;
        String currentMinimal = "";
    
        // Перебираем все возможные длины подстрок
        for (int length = 1; length <= first.length(); length++) {
            // Перебираем все возможные начальные позиции в first
            for (int start = 0; start <= first.length() - length; start++) {
                String substring = first.substring(start, start + length);
                // Проверяем, содержится ли подстрока в second
                if (!second.contains(substring)) {
                    // Если подстрока уникальна и её длина меньше текущего минимума
                    if (substring.length() < minimum) {
                        minimum = substring.length();
                        currentMinimal = substring;
                    }
                }
            }
        }
    
        return currentMinimal;
    }
}