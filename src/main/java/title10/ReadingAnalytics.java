package title10;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadingAnalytics {

    // Пункт 6 (и Пункт 1): Чтение >= 10 элементов из файла через Files.lines + Stream
    public static List<Reading> loadFromFile(Path path) throws IOException {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .map(line -> line.split(";"))
                    .filter(parts -> parts.length == 3)
                    .map(parts -> new Reading(
                            parts[0].trim(),
                            Double.parseDouble(parts[1].trim()),
                            Integer.parseInt(parts[2].trim())
                    ))
                    .toList();
        }
    }

    // Пункт 2а: Конвейер 1 — фильтр (temp < 0) + map (st) + sort
    public static List<String> getColdStationsSorted(List<Reading> readings) {
        return readings.stream()
                .filter(r -> r.getTemp() < 0)
                .map(Reading::getSt)
                .distinct()
                .sorted()
                .toList();
    }

    // Пункт 2б: Конвейер 2 — агрегация (average, sum, count)
    public static void printAggregates(List<Reading> readings) {
        double avgTemp = readings.stream()
                .mapToDouble(Reading::getTemp)
                .average()
                .orElse(0.0);

        int totalHum = readings.stream()
                .mapToInt(Reading::getHum)
                .sum();

        long freezingCount = readings.stream()
                .filter(r -> r.getTemp() < 0)
                .count();

        System.out.printf("Средняя температура: %.2f°C | Сумма влажности: %d%% | Записей ниже 0°C: %d%n",
                avgTemp, totalHum, freezingCount);
    }

    // Пункт 2в: Конвейер 3 — проверки anyMatch / allMatch
    public static void printMatches(List<Reading> readings) {
        boolean hasExtremeFrost = readings.stream().anyMatch(r -> r.getTemp() < -35.0);
        boolean allValidHumidity = readings.stream().allMatch(r -> r.getHum() >= 0 && r.getHum() <= 100);

        System.out.println("Есть ли аномальный мороз (< -35°C) [anyMatch]: " + hasExtremeFrost);
        System.out.println("У всех ли записей корректная влажность 0..100% [allMatch]: " + allValidHumidity);
    }

    // Пункт 3: groupingBy по полю варианта (st)
    public static Map<String, List<Reading>> groupByStation(List<Reading> readings) {
        return readings.stream()
                .collect(Collectors.groupingBy(Reading::getSt));
    }

    // Пункт 4: Поиск лучшего объекта (min temp) -> возврат Optional<Reading>
    public static Optional<Reading> findBest(List<Reading> readings) {
        return readings.stream()
                .min(Comparator.comparingDouble(Reading::getTemp));
    }

    // Обработка Optional по требованиям (map + orElse и orElseThrow)
    public static void demonstrateOptional(List<Reading> readings, String label) {
        System.out.println("--- Тест Optional (" + label + ") ---");
        Optional<Reading> bestOpt = findBest(readings);

        // 1. Необязательный результат: Optional.map + orElse
        String report = bestOpt
                .map(r -> String.format("Самая холодная точка: %s (%.1f°C)", r.getSt(), r.getTemp()))
                .orElse("Нет данных");
        System.out.println("1) map + orElse: " + report);

        // 2. Обязательный результат: orElseThrow
        try {
            Reading bestRequired = bestOpt
                    .orElseThrow(() -> new NoSuchElementException("Нет данных для выбора лучшего объекта"));
            System.out.println("2) orElseThrow (успех): " + bestRequired);
        } catch (NoSuchElementException e) {
            System.out.println("2) orElseThrow (исключение): " + e.getClass().getSimpleName()
                    + " -> " + e.getMessage());
        }
    }

    // Пункт 5: Задача на java.time (ZonedDateTime зоны варианта)
    public static void demonstrateZonedDateTime() {
        ZoneId khabarovskZone = ZoneId.of("Asia/Vladivostok");
        ZoneId moscowZone = ZoneId.of("Europe/Moscow");

        ZonedDateTime localObservation = ZonedDateTime.now(khabarovskZone);
        ZonedDateTime moscowTime = localObservation.withZoneSameInstant(moscowZone);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss z (VV)");
        System.out.println("Время замера (зона варианта): " + localObservation.format(fmt));
        System.out.println("То же мгновение в Москве:     " + moscowTime.format(fmt));
    }
}
