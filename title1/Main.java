package title1;
import java.util.Scanner;

public class Main {

    // 1. Метод вывода титульной информации
    public static void printHello() {
        System.out.println("=========================================");
        System.out.println("ФИО: Урбан Александра Вадимовна");
        System.out.println("Группа: БОМ31ТВР");
        System.out.println("Вариант задания: Задачи 5, 15, 45, 64");
        System.out.println("=========================================");
        System.out.println();
    }

    // ЗАДАЧА 5: Расчёт куба (Возвращает массив [V, S])
    public static double[] task05(double a) {
        double v = Math.pow(a, 3);
        double s = 6 * Math.pow(a, 2);
        return new double[]{v, s};
    }

    // ЗАДАЧА 15: Расчёт круга по площади (Возвращает массив [D, L])
    public static double[] task15(double s) {
        double d = Math.sqrt((4 * s) / Math.PI);
        double l = Math.PI * d;
        return new double[]{d, l};
    }

    // ЗАДАЧА 45: Подсчет положительных и отрицательных (Возвращает массив [pos, neg])
    public static int[] task45(int n1, int n2, int n3) {
        int posCount = 0;
        int negCount = 0;
        int[] numbers = {n1, n2, n3};
        for (int num : numbers) {
            if (num > 0) posCount++;
            else if (num < 0) negCount++;
        }
        return new int[]{posCount, negCount};
    }

    // ЗАДАЧА 64: Вычисление кусочно-заданной функции f(x)
    public static double task64(double x) {
        if (x > 0) {
            return 2 * Math.sin(x);
        } else {
            return 6 - x;
        }
    }

    // --- ОТДЕЛЬНЫЕ МЕТОДЫ ТЕСТИРОВАНИЯ ДЛЯ МЕНЮ ---

    private static void runTask5(Scanner sc) {
        System.out.println("--- Задача 5: Расчет куба ---");
        System.out.print("Введите длину ребра куба a: ");
        double a5 = sc.nextDouble();
        double[] res5 = task05(a5);
        System.out.printf("Объем куба V = %.4f; Площадь поверхности S = %.4f%n", res5[0], res5[1]);
    }

    private static void runTask15(Scanner sc) {
        System.out.println("--- Задача 15: Параметры круга ---");
        System.out.print("Введите площадь круга S: ");
        double s15 = sc.nextDouble();
        double[] res15 = task15(s15);
        System.out.printf("Диаметр D = %.4f; Длина окружности L = %.4f%n", res15[0], res15[1]);
    }

    private static void runTask45(Scanner sc) {
        System.out.println("--- Задача 45: Подсчет знаков трех чисел ---");
        System.out.print("Введите первое целое число: ");
        int n1 = sc.nextInt();
        System.out.print("Введите второе целое число: ");
        int n2 = sc.nextInt();
        System.out.print("Введите третье целое число: ");
        int n3 = sc.nextInt();
        int[] res45 = task45(n1, n2, n3);
        System.out.printf("Количество положительных: %d; отрицательных: %d%n", res45[0], res45[1]);
    }

    private static void runTask64(Scanner sc) {
        System.out.println("--- Задача 64: Расчет функции f(x) ---");
        System.out.print("Введите вещественное число x: ");
        double x64 = sc.nextDouble();
        double res64 = task64(x64);
        System.out.printf("Результат функции f(x) = %.4f%n", res64);
    }

    // ГЛАВНЫЙ МЕТОД С ИНТЕРАКТИВНЫМ МЕНЮ
    public static void main(String[] args) {
        printHello();
        Scanner sc = new Scanner(System.in);

        System.out.println("Выберите задачу для запуска:");
        System.out.println("1 - Запустить Задачу 5 (Куб)");
        System.out.println("2 - Запустить Задачу 15 (Круг)");
        System.out.println("3 - Запустить Задачу 45 (Знаки чисел)");
        System.out.println("4 - Запустить Задачу 64 (Кусочная функция)");
        System.out.println("5 - Запустить ВСЕ задачи последовательно");
        System.out.print("Ваш выбор: ");

        int choice = sc.nextInt();
        System.out.println();

        switch (choice) {
            case 1 -> runTask5(sc);
            case 2 -> runTask15(sc);
            case 3 -> runTask45(sc);
            case 4 -> runTask64(sc);
            case 5 -> {
                runTask5(sc); System.out.println();
                runTask15(sc); System.out.println();
                runTask45(sc); System.out.println();
                runTask64(sc);
            }
            default -> System.out.println("Ошибка: неверный пункт меню.");
        }

        sc.close();
    }
}
