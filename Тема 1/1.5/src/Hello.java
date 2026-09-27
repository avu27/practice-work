import java.util.Scanner;
public class Hello {
    public static void main(String[] args) {
        String fio = "Урбан Александра Вадимовна";
        String group = "БОМ31ТВР";
        int variant = 18;

        System.out.println("ФИО: " + fio);
        System.out.println("Группа: " + group);
        System.out.println("Вариант: " + variant);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите длину ребра куба a: ");
        double edge = scanner.nextDouble();

        task1(edge);

        scanner.close();
    }

    public static void task1(double a) {
        System.out.println("Задача 1:");
        System.out.println("Исходные данные: ребро куба a = " + a);

        if (a <= 0) {
            System.out.println("Ошибка: Длина ребра куба должна быть строго больше 0!");
            return;
        }

        double volume = Math.pow(a, 3);
        double surfaceArea = 6 * Math.pow(a, 2);

        System.out.printf("Объем куба V = %.2f\n", volume);
        System.out.printf("Площадь поверхности S = %.2f\n", surfaceArea);
    }

}




