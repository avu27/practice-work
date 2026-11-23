package title9;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path dir = Paths.get("data", "v18");
        Path input = dir.resolve("paths.txt");
        Path copyFile = dir.resolve("paths_copy.txt");
        Path output = dir.resolve("output.txt");

        try {
            // 1. Копирование
            FileProcessor.copy(input, copyFile);
            System.out.println("1. Файл скопирован в: " + copyFile.getFileName());

            // 2. Статистика
            System.out.print("2. ");
            FileProcessor.printStatistics(input);

            // 3. Фильтрация в output.txt
            System.out.print("3. ");
            FileProcessor.filterPaths(input, output);

            // 4. Нерекурсивный список .txt файлов
            System.out.println("4. Найденные .txt файлы (нерекурсивно):");
            List<Path> files = FileProcessor.listTxtFiles(dir);
            for (Path f : files) {
                System.out.println(" - " + f.getFileName());
            }

            // 5. Рекурсивный обход (Files.walk)
            System.out.println("5. Рекурсивный обход (Files.walk):");
            for (Path f : FileProcessor.walkTxtFiles(dir)) {
                System.out.println(" - " + f);
            }

        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }
}
