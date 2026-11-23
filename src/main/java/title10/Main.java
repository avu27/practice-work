package title10;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Path filePath = Paths.get("data", "v18_stream", "readings.txt");

        try {
            // Пункт 1 и 6: Загрузка через Files.lines + Stream
            List<Reading> readings = ReadingAnalytics.loadFromFile(filePath);
            System.out.println("=== 1 & 6. Загружено записей из файла: " + readings.size() + " ===");

            // Пункт 2: Три конвейера Stream
            System.out.println("\n=== 2. Три конвейера Stream API ===");
            System.out.println("Станции с temp < 0 (отсортированы): "
                    + ReadingAnalytics.getColdStationsSorted(readings));
            ReadingAnalytics.printAggregates(readings);
            ReadingAnalytics.printMatches(readings);

            // Пункт 3: groupingBy
            System.out.println("\n=== 3. Группировка по станциям (groupingBy) ===");
            Map<String, List<Reading>> grouped = ReadingAnalytics.groupByStation(readings);
            grouped.forEach((station, list) ->
                    System.out.println(" - " + station + ": " + list.size() + " замеров -> " + list));

            // Пункт 4: Optional (на непустом и на пустом списке)
            System.out.println("\n=== 4. Работа с Optional (findBest: min temp) ===");
            ReadingAnalytics.demonstrateOptional(readings, "исходный список");
            ReadingAnalytics.demonstrateOptional(List.of(), "пустой список");

            // Пункт 5: java.time (ZonedDateTime)
            System.out.println("\n=== 5. Работа с ZonedDateTime ===");
            ReadingAnalytics.demonstrateZonedDateTime();

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}
