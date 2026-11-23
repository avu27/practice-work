package title10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class ReadingAnalyticsTest {

    private final List<Reading> sampleData = List.of(
            new Reading("Хабаровск", -12.5, 78),
            new Reading("Якутск", -38.2, 69),
            new Reading("Сочи", 11.4, 55),
            new Reading("Хабаровск", -9.8, 74)
    );

    // Тест 1: Проверяет ТОЛЬКО фильтрацию (temp < 0) и сортировку названий станций
    @Test
    void shouldFilterColdStationsAndSortAlphabetically() {
        List<String> result = ReadingAnalytics.getColdStationsSorted(sampleData);

        assertEquals(List.of("Хабаровск", "Якутск"), result);
    }

    // Тест 2: Проверяет ТОЛЬКО группировку groupingBy по станции
    @Test
    void shouldGroupReadingsByStationName() {
        Map<String, List<Reading>> grouped = ReadingAnalytics.groupByStation(sampleData);

        assertEquals(2, grouped.get("Хабаровск").size());
        assertEquals(1, grouped.get("Якутск").size());
    }

    // Тест 3: Проверяет ТОЛЬКО поиск самой холодной точки (min temp) на непустом списке
    @Test
    void shouldFindColdestReadingWhenListIsNotEmpty() {
        Optional<Reading> best = ReadingAnalytics.findBest(sampleData);

        assertTrue(best.isPresent());
        assertEquals("Якутск", best.orElseThrow().getSt());
    }

    // Тест 4: Проверяет ТОЛЬКО выброс исключения orElseThrow на пустом списке
    @Test
    void shouldThrowExceptionWhenFindingBestInEmptyList() {
        Optional<Reading> emptyResult = ReadingAnalytics.findBest(List.of());

        assertThrows(NoSuchElementException.class, () ->
                emptyResult.orElseThrow(() -> new NoSuchElementException("Нет данных"))
        );
    }
}
