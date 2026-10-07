import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // 6. Дополнительно выводим args, если программа запущена с аргументами
        if (args.length > 0) {
            System.out.println("Аргументы запуска: " + Arrays.toString(args));
        }

        double x;

        // 2. Реализация ввода как через аргументы, так и с клавиатуры
        if (args.length > 0) {
            try {
                x = Double.parseDouble(args[0]);
                System.out.println("Исходное значение x (из аргументов): " + x);
                // 3. Вызов отдельного метода для решения задачи
                task64(x);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: переданный аргумент не является вещественным числом.");
            }
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите вещественное число x: ");
            if (scanner.hasNextDouble()) {
                x = scanner.nextDouble();
                // 3. Вызов отдельного метода для решения задачи
                task64(x);
            } else {
                System.out.println("Ошибка: введено не вещественное число.");
            }
        }
    }

    /**
     * 3. Отдельный метод для решения задачи №64.
     * 4. Используются подходящие типы данных (double).
     */
    public static void task64(double x) {
        double f;

        // Вычисление кусочно-заданной функции f(x)
        if (x > 0) {
            f = 2 * Math.sin(x);
        } else {
            f = 6 - x;
        }

        System.out.printf("Результат функции f(x) = %.4f\n", f);
    }
}
