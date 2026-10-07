import java.util.Scanner;

public class Main {

    // 1. Метод вывода титульной информации (изменено под ваши данные)
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

    // ГЛАВНЫЙ МЕТОД
    public static void main(String[] args) {
        printHello();

        // Вывод аргументов командной строки, если они переданы
        if (args.length > 0) {
            System.out.println("Аргументы командной строки:");
            for (int i = 0; i < args.length; i++) {
                System.out.printf("  args[%d] = %s%n", i, args[i]);
            }
            System.out.println();
        }

        Scanner sc = new Scanner(System.in);

        // --- ЗАДАЧА 5 ---
        System.out.println("--- Задача 5: Расчет куба ---");
        double a5;
        if (args.length >= 1) {
            a5 = Double.parseDouble(args[0]);
            System.out.println("Длина ребра куба a (из args): " + a5);
        } else {
            System.out.print("Введите длину ребра куба a: ");
            a5 = sc.nextDouble();
        }
        double[] res5 = task05(a5);
        System.out.printf("Объем куба V = %.4f; Площадь поверхности S = %.4f%n%n", res5[0], res5[1]);


        // --- ЗАДАЧА 15 ---
        System.out.println("--- Задача 15: Параметры круга ---");
        double s15;
        if (args.length >= 2) {
            s15 = Double.parseDouble(args[1]);
            System.out.println("Площадь круга S (из args): " + s15);
        } else {
            System.out.print("Введите площадь круга S: ");
            s15 = sc.nextDouble();
        }
        double[] res15 = task15(s15);
        System.out.printf("Диаметр D = %.4f; Длина окружности L = %.4f%n%n", res15[0], res15[1]);


        // --- ЗАДАЧА 45 ---
        System.out.println("--- Задача 45: Подсчет знаков трех чисел ---");
        int n1, n2, n3;
        if (args.length >= 5) {
            n1 = Integer.parseInt(args[2]);
            n2 = Integer.parseInt(args[3]);
            n3 = Integer.parseInt(args[4]);
            System.out.printf("Числа (из args): %d, %d, %d%n", n1, n2, n3);
        } else {
            System.out.print("Введите первое целое число: ");
            n1 = sc.nextInt();
            System.out.print("Введите второе целое число: ");
            n2 = sc.nextInt();
            System.out.print("Введите третье целое число: ");
            n3 = sc.nextInt();
        }
        int[] res45 = task45(n1, n2, n3);
        System.out.printf("Количество положительных: %d; отрицательных: %d%n%n", res45[0], res45[1]);


        // --- ЗАДАЧА 64 ---
        System.out.println("--- Задача 64: Расчет функции f(x) ---");
        double x64;
        if (args.length >= 6) {
            x64 = Double.parseDouble(args[5]);
            System.out.println("Вещественное число x (из args): " + x64);
        } else {
            System.out.print("Введите вещественное число x: ");
            x64 = sc.nextDouble();
        }
        double res64 = task64(x64);
        System.out.printf("Результат функции f(x) = %.4f%n", res64);

        sc.close();
    }
}

