package title4;

// 1. Базовый класс (не final)
public class Appliance {
    private final String brand;
    private final double power; // Мощность в Ваттах

    public Appliance(String brand, double power) {
        this.brand = brand;
        this.power = power;
    }

    public String getBrand() { return brand; }
    public double getPower() { return power; }

    // 5. Метод info() сделан final (запрещен к переопределению наследниками)
    public final void info() {
        System.out.printf("Прибор: %s | Марка: %s | Мощность: %.1f Вт%n",
                getClass().getSimpleName(), brand, power);
    }

    // Метод поведения для переопределения
    public void powerOn() {
        System.out.println("Прибор включен в розетку.");
    }
}
