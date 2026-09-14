import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите три целых числа через пробел или Enter:");
        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int n3 = scanner.nextInt();

        // Вызов метода задачи
        task3(n1, n2, n3);

        scanner.close();
    }

    /**
     * Подсчет количества положительных и отрицательных чисел.
     */
    public static void task3(int a, int b, int c) {
        System.out.println("\n--- Выполнение Задачи 3 ---");

        int positiveCount = 0;
        int negativeCount = 0;

        // Проверяем первое число
        if (a > 0) {
            positiveCount++;
        } else if (a < 0) {
            negativeCount++;
        }

        // Проверяем второе число
        if (b > 0) {
            positiveCount++;
        } else if (b < 0) {
            negativeCount++;
        }

        // Проверяем третье число
        if (c > 0) {
            positiveCount++;
        } else if (c < 0) {
            negativeCount++;
        }

        // Вывод результатов
        System.out.println("Количество положительных чисел: " + positiveCount);
        System.out.println("Количество отрицательных чисел: " + negativeCount);
    }
}
