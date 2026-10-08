package title2;
import java.util.Scanner;

public class Main {

    // Вывод информации об исполнителе
    public static void printHello() {
        System.out.println("=========================================");
        System.out.println("ФИО: Урбан Александра Вадимовна");
        System.out.println("Группа: БОМ31ТВР");
        System.out.println("Практическая работа №2: Задачи 58, 75, 115, 145, 175, 215");
        System.out.println("=========================================");
        System.out.println();
    }

    // --- МЕТОДЫ ЗАДАЧ ---

    public static int task058(int a, int b, int c) {
        if (a == b) return 3;
        else if (a == c) return 2;
        else return 1;
    }

    public static void task075(double pricePerKg) {
        for (int i = 1; i <= 10; i++) {
            double weight = i * 0.1;
            double cost = weight * pricePerKg;
            System.out.printf("Стоимость %.1f кг конфет: %.2f руб.%n", weight, cost);
        }
    }

    public static int task115(int n) {
        int k = 0;
        int temp = n;
        while (temp > 1) {
            temp /= 2;
            k++;
        }
        return k;
    }

    public static double[] task145(double[] masses, double[] volumes) {
        double maxDensity = -1.0;
        int maxIndex = -1;
        // В Java 0-based индексация. Элемент i (1-based) соответствует индексу i-1
        for (int i = 0; i < masses.length; i++) {
            double currentDensity = masses[i] / volumes[i];
            if (currentDensity > maxDensity) {
                maxDensity = currentDensity;
                maxIndex = i;
            }
        }
        return new double[]{maxIndex + 1, maxDensity};
    }

    public static int[] task175(int n) {
        int[] fib = new int[n];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < n; i++) {
            fib[i] = fib[i - 2] + fib[i - 1];
        }
        return fib;
    }

    public static int[] task215(int[] array) {
        return task215(array, 0, array.length - 1);
    }

    // Перегрузка метода task215 с границами поиска from/to
    public static int[] task215(int[] array, int from, int to) {
        int minDiff = Integer.MAX_VALUE;
        int index1 = -1;
        int index2 = -1;

        for (int i = from; i <= to; i++) {
            for (int j = i + 1; j <= to; j++) {
                int diff = Math.abs(array[i] - array[j]);
                if (diff < minDiff) {
                    minDiff = diff;
                    index1 = i;
                    index2 = j;
                }
            }
        }
        int num1 = index1 + 1;
        int num2 = index2 + 1;
        return (num1 < num2) ? new int[]{num1, num2} : new int[]{num2, num1};
    }

    // --- ОТДЕЛЬНЫЕ МЕТОДЫ ТЕСТИРОВАНИЯ ДЛЯ МЕНЮ ---

    private static void runTask58(Scanner sc) {
        System.out.println("--- Задача 58: Поиск отличного числа из трех ---");
        System.out.print("Введите три целых числа (два из них равны): ");
        int t58_1 = sc.nextInt();
        int t58_2 = sc.nextInt();
        int t58_3 = sc.nextInt();
        System.out.println("Порядковый номер отличного числа: " + task058(t58_1, t58_2, t58_3));
    }

    private static void runTask75(Scanner sc) {
        System.out.println("--- Задача 75: Стоимость конфет порциями ---");
        System.out.print("Введите цену 1 кг конфет: ");
        double price = sc.nextDouble();
        task075(price);
    }

    private static void runTask115(Scanner sc) {
        System.out.println("--- Задача 115: Показатель степени числа 2 ---");
        System.out.print("Введите целое N (степень двойки, например 8, 16, 64): ");
        int n115 = sc.nextInt();
        System.out.println("Показатель степени K = " + task115(n115));
    }

    private static void runTask145(Scanner sc) {
        System.out.println("--- Задача 145: Максимальная плотность деталей ---");
        System.out.print("Введите общее количество деталей N: ");
        int n145 = sc.nextInt();
        double[] masses = new double[n145];
        double[] volumes = new double[n145];
        for (int i = 0; i < n145; i++) {
            System.out.printf("Деталь №%d. Введите массу и объем: ", i + 1);
            masses[i] = sc.nextDouble();
            volumes[i] = sc.nextDouble();
        }
        double[] res145 = task145(masses, volumes);
        System.out.printf("Номер детали с макс. плотностью: %.0f (Плотность = %.4f)%n", res145[0], res145[1]);
    }

    private static void runTask175(Scanner sc) {
        System.out.println("--- Задача 175: Массив чисел Фибоначчи ---");
        System.out.print("Введите размер массива N (N > 2): ");
        int n175 = sc.nextInt();
        int[] res175 = task175(n175);
        System.out.print("Массив Фибоначчи: ");
        for (int val : res175) System.out.print(val + " ");
        System.out.println();
    }

    private static void runTask215(Scanner sc) {
        System.out.println("--- Задача 215: Ближайшие элементы массива ---");
        System.out.print("Введите размер целочисленного массива N: ");
        int n215 = sc.nextInt();
        int[] array215 = new int[n215];
        System.out.print("Введите элементы массива через пробел: ");
        for (int i = 0; i < n215; i++) array215[i] = sc.nextInt();

        int[] res215 = task215(array215);
        System.out.printf("Номера двух ближайших элементов во всем массиве: %d и %d%n", res215[0], res215[1]);

        if (n215 >= 3) {
            int[] resOverloaded = task215(array215, 0, 2);
            System.out.printf("Номера двух ближайших элементов в диапазоне 0..2: %d и %d%n", resOverloaded[0], resOverloaded[1]);
        }
    }

    // ГЛАВНЫЙ МЕТОД (ОРГАНИЗАЦИЯ МЕНЮ ВЫБОРА)
    public static void main(String[] args) {
        printHello();
        Scanner sc = new Scanner(System.in);

        System.out.println("Выберите режим работы программы:");
        System.out.println("1 - Запустить Задачу 58");
        System.out.println("2 - Запустить Задачу 75");
        System.out.println("3 - Запустить Задачу 115");
        System.out.println("4 - Запустить Задачу 145");
        System.out.println("5 - Запустить Задачу 175");
        System.out.println("6 - Запустить Задачу 215");
        System.out.println("7 - Запустить ВСЕ задачи поочередно");
        System.out.print("Ваш выбор: ");

        int choice = sc.nextInt();
        System.out.println();

        switch (choice) {
            case 1 -> runTask58(sc);
            case 2 -> runTask75(sc);
            case 3 -> runTask115(sc);
            case 4 -> runTask145(sc);
            case 5 -> runTask175(sc);
            case 6 -> runTask215(sc);
            case 7 -> {
                runTask58(sc); System.out.println();
                runTask75(sc); System.out.println();
                runTask115(sc); System.out.println();
                runTask145(sc); System.out.println();
                runTask175(sc); System.out.println();
                runTask215(sc);
            }
            default -> System.out.println("Ошибка: неверный пункт меню.");
        }

        sc.close();
    }
}
