package title3;

import java.util.Scanner;

public class Main {

    public static void printHello() {
        System.out.println("=========================================");
        System.out.println("ФИО: Урбан Александра Вадимовна");
        System.out.println("Группа: БОМ31ТВР");
        System.out.println("Практическая работа №3: Классы, объекты, хранилища");
        System.out.println("Вариант №18: Сущность Baggage (Багаж)");
        System.out.println("=========================================");
        System.out.println();
    }

    public static void main(String[] args) {
        printHello();
        BaggageStorage storage = new BaggageStorage();
        Scanner sc = new Scanner(System.in);

        if (args.length > 0) {
            System.out.println("Обнаружены аргументы запуска. Режим автоматической загрузки...\n");
            int i = 0;
            while (i + 3 < args.length) {
                try {
                    String tag = args[i];
                    double weight = Double.parseDouble(args[i + 1]);
                    String flight = args[i + 2];
                    boolean fragile = Boolean.parseBoolean(args[i + 3]);

                    Baggage b = new Baggage(tag, weight, flight, fragile);
                    storage.add(b);
                } catch (Exception e) {
                    System.out.println("Ошибка разбора аргументов на индексе " + i + ": " + e.getMessage());
                }
                i += 4;
            }
            System.out.println("\nЗагрузка из аргументов завершена.");
            storage.printAll();
            System.out.println();
            storage.findFragile();
            sc.close();
            return;
        }

        storage.add(new Baggage("SU-10245", 23.5, "SU-100", false));
        storage.add(new Baggage("LH-98712", 12.2, "LH-2541", true));
        storage.add(new Baggage("S7-00451", 31.0, "S7-1022", false));
        storage.add(new Baggage("EK-54129", 8.4, "EK-132", true));
        System.out.println("\nБазовая база данных инициализирована (4 объекта).\n");

        while (true) {
            System.out.println("=== МЕНЮ УПРАВЛЕНИЯ БАГАЖОМ ===");
            System.out.println("1 - Вывести весь багаж (printAll)");
            System.out.println("2 - Найти хрупкий багаж (Критерий вар. 18)");
            System.out.println("3 - Добавить новый багаж вручную (с проверками)");
            System.out.println("4 - Удалить багаж по номеру бирки");
            System.out.println("5 - Выйти из программы");
            System.out.print("Выберите действие: ");

            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (choice) {
                case 1 -> storage.printAll();
                case 2 -> storage.findFragile();
                case 3 -> {
                    try {
                        System.out.print("Введите номер бирки: ");
                        String tag = sc.nextLine();
                        System.out.print("Введите вес багажа в кг: ");
                        double weight = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Введите номер авиарейса: ");
                        String flight = sc.nextLine();
                        System.out.print("Багаж хрупкий? (true/false): ");
                        boolean fragile = sc.nextBoolean();

                        Baggage newBaggage = new Baggage(tag, weight, flight, fragile);
                        storage.add(newBaggage);
                    } catch (Exception e) {
                        System.out.println("Ошибка добавления: " + e.getMessage());
                        sc.nextLine();
                    }
                }
                case 4 -> {
                    System.out.print("Введите номер бирки багажа для удаления: ");
                    String tagToRemove = sc.nextLine();
                    storage.removeByTag(tagToRemove);
                }
                case 5 -> {
                    System.out.println("Программа завершена. Спасибо!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Неверный пункт меню. Попробуйте еще раз.");
            }
            System.out.println();
        }
    }
}
