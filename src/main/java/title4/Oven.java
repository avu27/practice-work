package title4;

// 2. Третий наследник
public class Oven extends Appliance {
    private final int maxTemp; // Максимальная температура нагрева

    public Oven(String brand, double power, int maxTemp) {
        super(brand, power);
        this.maxTemp = maxTemp;
    }

    public int getMaxTemp() { return maxTemp; }

    @Override
    public void powerOn() {
        System.out.printf("Духовой шкаф %s включил нагревательные элементы (ТЭНы). Макс. нагрев: %d°C.%n",
                getBrand(), maxTemp);
    }

    // Специальный уникальный метод духовки для демонстрации instanceof
    public void selfClean() {
        System.out.printf("Духовка %s запустила режим высокотемпературной пиролитической самоочистки!%n", getBrand());
    }
}
