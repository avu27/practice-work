package title5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import title5.title5.*;

class MachineTest {

    @Test
    void testCncThroughputNormalCase() {
        // 1. Нормальный случай: 5 осей, эффективность 0.90
        // Базовая формула: 50 * оси * коэффициент -> 50 * 5 * 0.9 = 225
        CNC cnc = new CNC("Фрезер-Тест", 0.90, 5);
        assertEquals(225.0, cnc.throughput(), 1e-9, "Расчёт нормальной выработки ЧПУ неверен");
    }

    @Test
    void testConveyorThroughputBoundaryCase() {
        // 2. Граничный/ошибочный случай: скорость конвейера равна 0 (стоит на месте)
        // Результат должен быть строго 0.0
        Conveyor conveyor = new Conveyor("Стоящая Лента", 0.95, 0.0);
        assertEquals(0.0, conveyor.throughput(), 1e-9, "У стоящего конвейера производительность должна быть 0");
    }

    @Test
    void testLambdaCalculationLogic() {
        // 3. Тест работы ключевой логики лямбда-выражений и интерфейса Calculation
        Machine[] testFloor = new Machine[] {
                new CNC("Станок-1", 1.0, 2) // 50 * 2 * 1.0 = 100
        };
        MachineService service = new MachineService(testFloor);
        Calculation lambdaCalc = (m) -> m.throughput();

        double total = service.calculateTotalThroughputCustom(lambdaCalc);
        assertEquals(100.0, total, 1e-9, "Суммирование через лямбду работает некорректно");
    }
}
