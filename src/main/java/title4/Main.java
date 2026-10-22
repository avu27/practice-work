package title4;

public class Main {

    public static void printHello() {
        System.out.println("=========================================");
        System.out.println("ФИО: Урбан Александра Вадимовна");
        System.out.println("Группа: БОМ31ТВР");
        System.out.println("Практическая работа №4: Наследование и полиморфизм");
        System.out.println("Вариант №18: Бытовая техника (Appliance)");
        System.out.println("=========================================");
        System.out.println();
    }

    // 4. Отдельный метод обработки конкретного наследника через instanceof с Pattern Matching
    public static void processOvenSpecialFeatures(Appliance appliance) {
        if (appliance instanceof Oven oven) {
            // Благодаря Pattern Matching Java сама создала переменную 'oven' нужного типа
            System.out.print("   [Спец. обработка] ");
            oven.selfClean();
        }
    }

    public static void main(String[] args) {
        printHello();

        // 3. Массив базового типа из >= 5 объектов разных наследников
        Appliance[] devices = new Appliance[]{
                new Fridge("Bosch", 200.0, 350),
                new Washer("LG", 1800.0, 1200),
                new Oven("Gorenje", 3000.0, 275),
                new Fridge("Samsung", 150.0, 410),
                new Oven("Electrolux", 2500.0, 300)
        };

        System.out.println("--- Выполнение полиморфного цикла ---");
        // Цикл с полиморфными вызовами методов info() и powerOn()
        for (Appliance device : devices) {
            device.info();    // Вызов общего метода базового класса
            device.powerOn(); // Полиморфный вызов переопределенного метода поведения

            // 4. Обработка специфичного типа
            processOvenSpecialFeatures(device);
            System.out.println("------------------------------------------------------------------");
        }
    }
}
