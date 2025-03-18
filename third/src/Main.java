import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    // Метод для получения подстрок
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
            for (int j = i + maxCommon.length(); j <= s1.length(); j++) {
                String sub = s1.substring(i, j);
                if (s2.contains(sub)) {
                    maxCommon = sub;
                } else {
                    break;
                }
            }
        }
        return maxCommon;
    }

    // Метод для поиска уникальных подстрок в первой строке, отсутствующих во второй
    public static Set<String> findUniqueInFirst(List<String> subs1, List<String> subs2) {
        Set<String> unique = new HashSet<>();
        for (String sub : subs1) {
            if (!subs2.contains(sub)) {
                unique.add(sub);
            }
        }
        return unique;
    }

    // Метод для поиска общих подстрок между двумя списками
    public static Set<String> findCommon(List<String> subs1, List<String> subs2) {
        Set<String> common = new HashSet<>();
        for (String sub : subs1) {
            if (subs2.contains(sub)) {
                common.add(sub);
            }
        }
        return common;
    }

    // Метод для поиска подстрок в первой строке, отсутствующих во второй и третьей
    public static Set<String> findMissingInSecondAndThird(List<String> subs1, List<String> subs2, List<String> subs3) {
        Set<String> missing = new HashSet<>();
        for (String sub : subs1) {
            if (!subs2.contains(sub) && !subs3.contains(sub)) {
                missing.add(sub);
            }
        }
        return missing;
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

        // Поиск уникальных и общих подстрок
        Set<String> unique1 = findUniqueInFirst(subs1, subs2);
        Set<String> unique2 = findUniqueInFirst(subs2, subs1);
        Set<String> common12 = findCommon(subs1, subs2);
        Set<String> unique3 = findUniqueInFirst(subs3, subs1);
        unique3.removeAll(subs2); // Убираем подстроки, которые есть в dna2
        Set<String> missingIn23 = findMissingInSecondAndThird(subs1, subs2, subs3);

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