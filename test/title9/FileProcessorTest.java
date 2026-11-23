package title9;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileProcessorTest {

    @TempDir
    Path tempDir;

    @Test
    void testCopyFileSuccessAndNotFound() throws IOException {
        Path src = tempDir.resolve("source.txt");
        Path dst = tempDir.resolve("copied.txt");
        Files.writeString(src, "Тестовый текст", StandardCharsets.UTF_8);

        FileProcessor.copy(src, dst);
        assertTrue(Files.exists(dst));
        assertEquals("Тестовый текст", Files.readString(dst, StandardCharsets.UTF_8));

        Path missing = tempDir.resolve("missing.txt");
        assertThrows(IOException.class, () -> FileProcessor.copy(missing, dst));
    }

    @Test
    void testFilterPathsVariant18() throws IOException {
        Path input = tempDir.resolve("paths.txt");
        Path output = tempDir.resolve("output.txt");

        List<String> lines = List.of(
                "/var/log/nginx/",
                "/etc/hosts",
                "/home/user/docs/",
                "/usr/bin/java"
        );
        Files.write(input, lines, StandardCharsets.UTF_8);

        FileProcessor.filterPaths(input, output);

        List<String> result = Files.readAllLines(output, StandardCharsets.UTF_8);
        assertEquals(2, result.size());
        assertEquals("/var/log/nginx/", result.get(0));
        assertEquals("/home/user/docs/", result.get(1));
    }

    @Test
    void testListAndWalkTxtFiles() throws IOException {
        Files.writeString(tempDir.resolve("one.txt"), "1", StandardCharsets.UTF_8);
        Files.writeString(tempDir.resolve("image.png"), "2", StandardCharsets.UTF_8);

        // Создаём вложенную подпапку и файл внутри неё
        Path subDir = Files.createDirectory(tempDir.resolve("sub"));
        Files.writeString(subDir.resolve("nested.txt"), "3", StandardCharsets.UTF_8);

        // Нерекурсивный обход видит только 1 файл в корне
        assertEquals(1, FileProcessor.listTxtFiles(tempDir).size());

        // Рекурсивный Files.walk видит оба файла (в корне и в подпапке)
        assertEquals(2, FileProcessor.walkTxtFiles(tempDir).size());
    }
}
