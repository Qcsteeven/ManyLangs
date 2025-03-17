import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    // Метод для получения  подстрок
    public static List<String> getSubstrings(String text) {
        List<String> result = new ArrayList<>();
        for (int len = 2; len <= 6; len++) {
            for (int i = 0; i <= text.length() - len; i++) {
                result.add(text.substring(i, i + len));
            }
        }
        return result;
    }

    // Метод для чтения файла и удаления цифр и пробелов
    public static String readFile(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path))).replaceAll("[\\d\\s]", "");
        } catch (IOException e) {
            return "";
        }
    }

    // Метод для нахождения минимальной по длине подстроки в множестве
    public static String findMin(Set<String> substrings) {
        String min = null;
        for (String s : substrings) {
            if (min == null || s.length() < min.length()) {
                min = s;
            }
        }
        return min;
    }

    // Метод для нахождения максимальной общей подпоследовательности двух строк
    public static String findMaxCommon(String s1, String s2) {
        String maxCommon = "";
        for (int i = 0; i < s1.length(); i++) {
            for (int j = i + 1; j <= s1.length(); j++) {
                String sub = s1.substring(i, j);
                if (s2.contains(sub) && sub.length() > maxCommon.length()) {
                    maxCommon = sub;
                } else {
                    break;
                }
            }
        }
        return maxCommon;
    }

    public static void main(String[] args) {
        // Чтение содержимого файлов в строки, удаляя цифры и пробелы
        String dna1 = readFile("example1.txt");
        String dna2 = readFile("example2.txt");
        String dna3 = readFile("example3.txt");

        // Получение подстрок для каждой строки
        List<String> subs1 = getSubstrings(dna1);
        List<String> subs2 = getSubstrings(dna2);
        List<String> subs3 = getSubstrings(dna3);

        // Создание множеств для хранения уникальных и общих подстрок
        Set<String> unique1 = new HashSet<>();
        Set<String> unique2 = new HashSet<>();
        Set<String> unique3 = new HashSet<>();
        Set<String> common12 = new HashSet<>();
        Set<String> missingIn23 = new HashSet<>();

        // Поиск уникальных подстрок для dna1, которые отсутствуют в dna2
        // и общих подстрок для dna1 и dna2
        for (String sub : subs1) {
            if (!subs2.contains(sub)) {
                unique1.add(sub);
            } else {
                common12.add(sub);
            }
        }

        // Поиск уникальных подстрок для dna2, которые отсутствуют в dna1
        for (String sub : subs2) {
            if (!subs1.contains(sub)) {
                unique2.add(sub);
            }
        }

        // Поиск уникальных подстрок для dna3, которые отсутствуют в dna1 и dna2
        for (String sub : subs3) {
            if (!subs1.contains(sub) && !subs2.contains(sub)) {
                unique3.add(sub);
            }
        }

        // Поиск подстрок из dna1, которые отсутствуют в dna2 и dna3
        for (String sub : subs1) {
            if (!subs2.contains(sub) && !subs3.contains(sub)) {
                missingIn23.add(sub);
            }
        }

        // Нахождение минимальной по длине подстроки в каждом множестве
        String minUnique1 = findMin(unique1);
        String minUnique2 = findMin(unique2);
        String minCommon12 = findMin(common12);
        String minMissing23 = findMin(missingIn23);
        String minUnique3 = findMin(unique3);

        // Нахождение максимальной общей подпоследовательности для dna1 и dna3
        String maxCommon13 = findMaxCommon(dna1, dna3);
        // Вычисление соотношения длины общей подпоследовательности к длине dna1
        double ratio = (double) maxCommon13.length() / dna1.length();

        // Вывод результатов
        System.out.println("Мин. уникальная для 1, отсутствующая в 2: " + minUnique1);
        System.out.println("Мин. уникальная для 2, отсутствующая в 1: " + minUnique2);
        System.out.println("Мин. общая для 1 и 2: " + minCommon12);
        System.out.println("Мин. уникальная для 1, отсутствующая в 2 и 3: " + minMissing23);
        System.out.println("Мин. уникальная для 3, отсутствующая в 1 и 2: " + minUnique3);
        System.out.println("Макс. общая подпоследовательность для 1 и 3: " + maxCommon13);
        System.out.println("Соотношение длины общей подпоследовательности к длине 1: " + ratio);
    }

}