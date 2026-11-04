package title8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ReadingCollectionsTest {

    @Test
    void testGenericSumMethodWithCustomCalculationInterface() {
        List<Reading> testList = new ArrayList<>();
        testList.add(new Reading("TestStation", "2026-11-01", 10.0, 70));
        testList.add(new Reading("TestStation", "2026-11-02", -5.5, 80));

        Calculation<Reading> tempCalc = (r) -> r.getTemp();

        double sum = Main.sum(testList, tempCalc);
        assertEquals(4.5, sum, 1e-9, "Обобщенный метод sum работает некорректно!");
    }

    @Test
    void testGenericSumWithEmptyListReturnsZero() {
        Calculation<Reading> tempCalc = (r) -> r.getTemp();
        double sum = Main.sum(new ArrayList<>(), tempCalc);
        assertEquals(0.0, sum, 1e-9, "Для пустого списка сумма обязана быть равной 0.0!");
    }
}
