package title5;

public class CNC extends Machine implements Startable, Maintainable {
    private final int numberOfAxes; // Количество осей обработки (например, 3-осевой, 5-осевой)

    public CNC(String name, double efficiencyFactor, int numberOfAxes) {
        super(name, efficiencyFactor);
        this.numberOfAxes = numberOfAxes;
    }

    @Override
    public double throughput() {
        // Формула: базовая константа 50 деталей * число осей * коэффициент эффективности
        return 50.0 * numberOfAxes * getEfficiencyFactor();
    }

    @Override
    public void start() {
        System.out.println("ЧПУ-станок " + getName() + " загрузил управляющую управляющую программу. Шпиндель раскручен.");
    }

    @Override
    public void performMaintenance() {
        System.out.println("Обслуживание ЧПУ-станка " + getName() + ": Очищены направляющие от стружки, обновлена смазка, откалиброваны оси.");
    }
}
