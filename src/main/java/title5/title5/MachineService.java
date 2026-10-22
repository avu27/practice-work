package title5.title5;

public class MachineService {
    private final Machine[] storage;

    public MachineService(Machine[] storage) {
        this.storage = storage;
    }

    // 4. Метод, агрегирующий расчёт и печатающий полиморфные отчёты по интерфейсам-ролям
    public void processServiceOperations() {
        System.out.println("--- ПОЛИМОРФНЫЙ ОТЧЁТ СЕРВИСНОЙ СЛУЖБЫ ПО ИНТЕРФЕЙСАМ ---");
        for (Machine m : storage) {
            System.out.println(m);
            // Проверяем роль Запускаемого
            if (m instanceof Startable startable) {
                System.out.print("   [Действие] ");
                startable.start();
            }
            // Проверяем роль Обслуживаемого
            if (m instanceof Maintainable maintainable) {
                System.out.print("   [Действие] ");
                maintainable.performMaintenance();
                maintainable.checkSafetyProtocols(); // Вызов default-метода
            }
            System.out.println("------------------------------------------------------------------");
        }
    }

    // Вычисление суммы результатов через функциональный интерфейс Calculation
    public double calculateTotalThroughputCustom(Calculation calc) {
        double total = 0;
        for (Machine m : storage) {
            total += calc.calculate(m);
        }
        return total;
    }

    // Отбор и печать объектов по критерию Selection
    public void filterAndPrint(Selection selection, String criteriaDescription) {
        System.out.println("--- Отбор оборудования по критерию: " + criteriaDescription + " ---");
        boolean found = false;
        for (Machine m : storage) {
            if (selection.test(m)) {
                System.out.printf("   > Подходит: %s (Фактическая производительность: %.2f)%n",
                        m.getName(), m.throughput());
                found = true;
            }
        }
        if (!found) {
            System.out.println("   Нет оборудования, удовлетворяющего заданному критерию.");
        }
        System.out.println();
    }

    // Метод вывода стандартного отчета для передачи по ссылке Runnable
    public void printReport() {
        System.out.println("========== СТАНДАРТНЫЙ СВОДНЫЙ ОТЧЁТ СЛУЖБЫ МОНИТОРИНГА ==========");
        double totalThroughput = 0;
        for (Machine m : storage) {
            double current = m.throughput();
            System.out.printf("Станок: %-15s | Текущая выработка: %.2f ед/час%n", m.getName(), current);
            totalThroughput += current;
        }
        System.out.printf("ОБЩАЯ СУММАРНАЯ ПРОИЗВОДИТЕЛЬНОСТЬ ЦЕХА: %.2f ед/час%n", totalThroughput);
        System.out.println("==================================================================");
    }
}
