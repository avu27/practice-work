package title8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    // Обобщенный метод подсчета суммы числового поля
    public static <T> double sum(List<T> values, Calculation<T> calculation) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (T val : values) {
            total += calculation.calculate(val);
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("ПРАКТИЧЕСКАЯ РАБОТА №8 | ВАРИАНТ №18 (READINGS)");
        System.out.println("Выполнила: Урбан А.В. группа БОМ31ТВР");
        System.out.println("=================================================");

        List<Reading> list = new ArrayList<>();
        String filename = "readings.txt";

        // 1. Чтение файла с обработкой битых строк
        System.out.println("\n[ЗАДАНИЕ 1-2] Чтение файла данных метеостанций:");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                lineNum++;
                String[] parts = line.split(";");
                if (parts.length != 4) {
                    System.out.println("⚠️ Пропуск битой строки №" + lineNum + ": '" + line + "'");
                    continue;
                }
                try {
                    String station = parts[0].trim();
                    String date = parts[1].trim();
                    double temp = Double.parseDouble(parts[2].trim());
                    int humidity = Integer.parseInt(parts[3].trim());
                    list.add(new Reading(station, date, temp, humidity));
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Пропуск битой строки №" + lineNum + " (ошибка формата чисел): '" + line + "'");
                }
            }
        } catch (IOException e) {
            System.err.println("Критическая ошибка чтения файла: " + e.getMessage());
            return;
        }

        // 2. Группировка по метеостанциям
        System.out.println("\n[ЗАДАНИЕ 3] Группировка Map<String, List<Reading>> по станциям:");
        Map<String, List<Reading>> map = new HashMap<>();
        for (Reading r : list) {
            if (!map.containsKey(r.getStation())) {
                map.put(r.getStation(), new ArrayList<>());
            }
            map.get(r.getStation()).add(r);
        }
        for (Map.Entry<String, List<Reading>> entry : map.entrySet()) {
            System.out.println(" Станция '" + entry.getKey() + "' имеет записей: " + entry.getValue().size());
        }

        // 3. Множество уникальных дат
        System.out.println("\n[ЗАДАНИЕ 4] Построение Set уникальных дат:");
        Set<String> uniqueDates = new HashSet<>();
        for (Reading r : list) {
            uniqueDates.add(r.getDate());
        }
        System.out.println(" Всего прочитано показаний: " + list.size());
        System.out.println(" Уникальных дат в архиве: " + uniqueDates.size());

        // 4. Построение топ-3 по температуре по убыванию
        System.out.println("\n[ЗАДАНИЕ 5] Вывод Топ-3 метеопоказаний по максимальной температуре:");
        List<Reading> sortedList = new ArrayList<>(list);

        // Реализация Comparator лямбдой со ссылкой на метод
        Comparator<Reading> tempComparator = Comparator.comparingDouble(Reading::getTemp).reversed();
        Collections.sort(sortedList, tempComparator);

        for (int i = 0; i < Math.min(3, sortedList.size()); i++) {
            System.out.println("  Топ-" + (i + 1) + ": " + sortedList.get(i));
        }

        // 5. Работа со стандартными функциональными интерфейсами
        System.out.println("\n=================================================");
        System.out.println("РАЗДЕЛ: СТАНДАРТНЫЕ ФУНКЦИОНАЛЬНЫЕ ИНТЕРФЕЙСЫ");
        System.out.println("=================================================");

        // А) Predicate для отбора объектов (Порог 1: > 5.0°С, Порог 2: > 20.0°С для пустого ответа)
        System.out.println("\n[ИНТЕРФЕЙС 1] Применение Predicate<Reading> (Порог > 5.0°C):");
        Predicate<Reading> warmPredicate = (r) -> r.getTemp() > 5.0;
        List<Reading> warmFiltered = new ArrayList<>();
        for (Reading r : list) {
            if (warmPredicate.test(r)) {
                System.out.println("  Отобран: " + r);
                warmFiltered.add(r);
            }
        }

        System.out.println("\nПрименение Predicate<Reading> (Порог > 20.0°C — должен дать пустой список):");
        Predicate<Reading> hotPredicate = (r) -> r.getTemp() > 20.0;
        int hotCount = 0;
        for (Reading r : list) {
            if (hotPredicate.test(r)) {
                hotCount++;
            }
        }
        System.out.println("  Количество записей с температурой > 20.0°C: " + hotCount);

        // Б) Function и Consumer через ссылку на метод System.out::println
        System.out.println("\n[ИНТЕРФЕЙС 2] Применение Function<Reading, String> и Consumer<String>:");
        Function<Reading, String> readingFormatter = (r) ->
                String.format("Отчёт по станции [%s] -> Зафиксированная температура: %.1f°C", r.getStation(), r.getTemp());

        Consumer<String> printer = System.out::println; // Ссылка на метод

        for (Reading r : warmFiltered) {
            String formattedReport = readingFormatter.apply(r); // Вызов apply
            printer.accept(formattedReport);                    // Вызов accept
        }

        // В) Универсальный интерфейс Calculation<T> и обобщенный метод sum
        System.out.println("\n[ИНТЕРФЕЙС 3] Вычисление сумм через лямбду Calculation<Reading>:");
        Calculation<Reading> tempExtractor = (r) -> r.getTemp();

        double totalSum = sum(list, tempExtractor);
        double filteredSum = sum(warmFiltered, tempExtractor);
        double emptySum = sum(new ArrayList<Reading>(), tempExtractor);

        System.out.printf("  Сумма температур всего исходного списка: %.2f°C%n", totalSum);
        System.out.printf("  Сумма температур отобранного (теплого) списка: %.2f°C%n", filteredSum);
        System.out.printf("  Сумма для пустого списка: %.2f°C%n", emptySum);
    }
}
