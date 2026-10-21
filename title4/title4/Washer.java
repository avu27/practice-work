package title4;

// 2. Второй наследник
public class Washer extends Appliance {
    private final int rpm; // Скорость отжима

    public Washer(String brand, double power, int rpm) {
        super(brand, power);
        this.rpm = rpm;
    }

    public int getRpm() { return rpm; }

    @Override
    public void powerOn() {
        System.out.printf("Стиральная машина %s заблокировала люк и начала стирку. Отжим настроен на %d об/мин.%n",
                getBrand(), rpm);
    }
}
