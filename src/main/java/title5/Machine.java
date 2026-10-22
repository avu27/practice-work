package title5;

// 1. Абстрактный базовый класс
public abstract class Machine {
    private final String name;
    private final double efficiencyFactor; // Общий коэффициент эффективности (например, 0.85)

    public Machine(String name, double efficiencyFactor) {
        this.name = name;
        this.efficiencyFactor = efficiencyFactor;
    }

    public String getName() {
        return name;
    }

    public double getEfficiencyFactor() {
        return efficiencyFactor;
    }

    // Абстрактный метод расчёта производительности
    public abstract double throughput();

    @Override
    public String toString() {
        return String.format("Оборудование: %s '%s' [Эффективность: %.2f]",
                getClass().getSimpleName(), name, efficiencyFactor);
    }
}
