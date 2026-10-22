package title5;

public class Conveyor extends Machine implements Startable {
    private final double beltSpeedMps; // Скорость ленты в метрах в секунду

    public Conveyor(String name, double efficiencyFactor, double beltSpeedMps) {
        super(name, efficiencyFactor);
        this.beltSpeedMps = beltSpeedMps;
    }

    @Override
    public double throughput() {
        // Формула: скорость ленты * 3600 секунд * коэффициент эффективности (перемещение метров груза в час)
        return beltSpeedMps * 3600 * getEfficiencyFactor();
    }

    @Override
    public void start() {
        System.out.println("Конвейерная линия " + getName() + " привела в движение приводной барабан. Лента движется.");
    }
}
