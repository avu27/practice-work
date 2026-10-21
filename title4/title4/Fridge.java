package title4;

// 2. Первый наследник
public class Fridge extends Appliance {
    private final int volume; // Объем камеры в литрах

    public Fridge(String brand, double power, int volume) {
        super(brand, power); // Вызов super в конструкторе
        this.volume = volume;
    }

    public int getVolume() { return volume; }

    @Override
    public void powerOn() {
        System.out.printf("Холодильник %s загудел компрессором. Начинается охлаждение %d литров объема.%n",
                getBrand(), volume);
    }
}
