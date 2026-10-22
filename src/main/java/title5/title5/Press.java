package title5.title5;

public class Press extends Machine implements Startable, Maintainable {
    private final int strokesPerMinute; // Количество ударов пресса в минуту

    public Press(String name, double efficiencyFactor, int strokesPerMinute) {
        super(name, efficiencyFactor);
        this.strokesPerMinute = strokesPerMinute;
    }

    @Override
    public double throughput() {
        // Формула: удары в минуту * 60 минут * коэффициент эффективности
        return strokesPerMinute * 60 * getEfficiencyFactor();
    }

    @Override
    public void start() {
        System.out.println("Пресс " + getName() + " запустил гидравлический насос. Готов к штамповке.");
    }

    @Override
    public void performMaintenance() {
        System.out.println("Обслуживание пресса " + getName() + ": Долит уровень гидравлического масла, проверены пресс-формы.");
    }
}
