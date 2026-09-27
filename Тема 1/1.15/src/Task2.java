import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите площадь круга S: ");
        double area = scanner.nextDouble();

        task2(area);

        scanner.close();
    }

    public static void task2(double s) {
        System.out.println("Задача 2");

        if (s <= 0) {
            System.out.println("Ошибка: Площадь круга должна быть строго больше 0!");
            return;
        }

        double d = Math.sqrt((4 * s) / Math.PI); // Диаметр D
        double l = Math.PI * d;                  // Длина окружности L

        System.out.printf("Диаметр круга D = %.2f\n", d);
        System.out.printf("Длина окружности L = %.2f\n", l);
    }
}
