package title9;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileProcessor {

    // 1. Утилита копирования файла с проверкой существования
    public static void copy(Path src, Path dst) throws IOException {
        if (!Files.exists(src)) {
            throw new IOException("Исходный файл не найден: " + src);
        }
        if (dst.getParent() != null) {
            Files.createDirectories(dst.getParent());
        }
        Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
    }

    // 2. Подсчёт статистики: строки / слова / символы
    public static void printStatistics(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        int lineCount = lines.size();
        int wordCount = 0;
        int charCount = 0;

        for (String line : lines) {
            charCount += line.length();
            String trimmed = line.trim();
            if (!trimmed.isEmpty()) {
                wordCount += trimmed.split("\\s+").length;
            }
        }

        System.out.printf("Статистика '%s': строк = %d, слов = %d, символов = %d%n",
                file.getFileName(), lineCount, wordCount, charCount);
    }

    // 3. Фильтрация строк (путь оканчивается на '/') -> запись в output.txt
    public static void filterPaths(Path src, Path dst) throws IOException {
        List<String> lines = Files.readAllLines(src, StandardCharsets.UTF_8);
        List<String> filtered = new ArrayList<>();

        for (String line : lines) {
            if (line.trim().endsWith("/")) {
                filtered.add(line);
            }
        }

        Files.write(dst, filtered, StandardCharsets.UTF_8);
        System.out.println("Записано строк в output.txt: " + filtered.size());
    }

    // 4а. Нерекурсивный обход каталога: список файлов с расширением .txt
    public static List<Path> listTxtFiles(Path dir) throws IOException {
        List<Path> result = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) {
                    result.add(entry);
                }
            }
        }
        return result;
    }

    // 4б. Дополнительное задание: рекурсивный обход через Files.walk
    public static List<Path> walkTxtFiles(Path dir) throws IOException {
        try (Stream<Path> stream = Files.walk(dir)) {
            return stream
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".txt"))
                    .toList();
        }
    }
}
