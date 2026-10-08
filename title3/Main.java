package title3;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

// =========================================================================
// 1. КЛАСС СУЩНОСТИ (Baggage) с private-полями и валидацией в конструкторе
// =========================================================================
class Baggage {
    private String tag;      // Номер багажной бирки
    private double weight;   // Вес багажа в кг
    private String flight;   // Номер авиарейса
    private boolean fragile; // Флаг: хрупкий багаж (true/false)

    // Конструктор с обязательными проверками (Валидация)
    public Baggage(String tag, double weight, String flight, boolean fragile) {
        if (tag == null || tag.trim().isEmpty()) {
            throw new IllegalArgumentException("Ошибка: Номер бирки не может быть пустым.");
        }
        if (flight == null || flight.trim().isEmpty()) {
            throw new IllegalArgumentException("Ошибка: Номер рейса не может быть пустым.");
        }
        if (weight <= 0.0 || weight > 100.0) { // Разумные границы веса багажа
            throw new IllegalArgumentException("Ошибка: Вес багажа должен быть в диапазоне от 0 до 100 кг.");
        }
        this.tag = tag;
        this.weight = weight;
        this.flight = flight;
        this.fragile = fragile;
    }

    // Геттеры (Getters)
    public String getTag() { return tag; }
    public double getWeight() { return weight; }
    public String getFlight() { return flight; }
    public boolean isFragile() { return fragile; }

    // Переопределение метода toString()
    @Override
    public String toString() {
        return String.format("Багаж [Бирка: %s, Вес: %.2f кг, Рейс: %s, Хрупкий: %s]",
                tag, weight, flight, fragile ? "Да" : "Нет");
    }

    // Переопределение equals и hashCode для корректного поиска и удаления объектов
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Baggage baggage = (Baggage) o;
        return Objects.equals(tag, baggage.tag); // Багаж уникален по своей бирке
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }
}

// =========================================================================
// 2. КЛАСС-ХРАНИЛИЩЕ (BaggageStorage) на базе динамического списка ArrayList
// =========================================================================
class BaggageStorage {
    private final ArrayList<Baggage> list;

    public BaggageStorage() {
        this.list = new ArrayList<>();
    }

    // Метод добавления объекта
    public void add(Baggage baggage) {
        if (baggage == null) return;
        // Проверка на дубликаты бирок с использованием переопределенного equals
        if (list.contains(baggage)) {
            System.out.println("Ошибка: Багаж с биркой " + baggage.getTag() + " уже зарегистрирован в системе!");
            return;
        }
        list.add(baggage);
        System.out.println("Успешно добавлено: " + baggage.getTag());
    }

    // Метод вывода всех объектов (printAll)
    public void printAll() {
        if (list.isEmpty()) {
            System.out.println("Хранилище багажа пусто.");
            return;
        }
        System.out.println("--- Полный список зарегистрированного багажа ---");
        for (Baggage b : list) {
            System.out.println(b);
        }
    }

    // Метод поиска хрупкого багажа
    public void findFragile() {
        System.out.println("--- Результаты поиска (Хрупкий багаж) ---");
        boolean found = false;
        for (Baggage b : list) {
            if (b.isFragile()) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Хрупкий багаж в системе не обнаружен.");
        }
    }

    // Метод удаления объекта из хранилища по номеру бирки
    public void removeByTag(String tag) {
        // Создаем временный объект с нужным tag для работы встроенного метода remove через equals
        Baggage dummy = new Baggage(tag, 1.0, "DUMMY", false);
        if (list.remove(dummy)) {
            System.out.println("Багаж с биркой " + tag + " успешно удален из хранилища.");
        } else {
            System.out.println("Ошибка: Багаж с биркой " + tag + " не найден.");
        }
    }
}

// =========================================================================
// 3. ГЛАВНЫЙ КЛАСС УПРАВЛЕНИЯ С ИНТЕРАКТИВНЫМ МЕНЮ
// =========================================================================
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

        // Инициализация хранилища (микро-база данных из 4 объектов по требованию пункта 3)
        storage.add(new Baggage("SU-10245", 23.5, "SU-100", false));
        storage.add(new Baggage("LH-98712", 12.2, "LH-2541", true));  // Хрупкий
        storage.add(new Baggage("S7-00451", 31.0, "S7-1022", false));
        storage.add(new Baggage("EK-54129", 8.4, "EK-132", true));    // Хрупкий
        System.out.println("\nБазовая база данных инициализирована (4 объекта).\n");

        while (true) {
            System.out.println("=== МЕНЮ УПРАВЛЕНИЯ БАГАЖОМ ===");
            System.out.println("1 - Вывести весь багаж (printAll)");
            System.out.println("2 - Найти хрупкий багаж");
            System.out.println("3 - Добавить новый багаж вручную (с проверками)");
            System.out.println("4 - Удалить багаж по номеру бирки");
            System.out.println("5 - Выйти из программы");
            System.out.print("Выберите действие: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Очистка буфера после ввода числа
            System.out.println();

            switch (choice) {
                case 1 -> storage.printAll();
                case 2 -> storage.findFragile();
                case 3 -> {
                    try {
                        System.out.print("Введите номер бирки (например, AA-123): ");
                        String tag = sc.nextLine();
                        System.out.print("Введите вес багажа в кг: ");
                        double weight = sc.nextDouble();
                        sc.nextLine(); // Очистка буфера
                        System.out.print("Введите номер авиарейса: ");
                        String flight = sc.nextLine();
                        System.out.print("Багаж хрупкий? (true/false): ");
                        boolean fragile = sc.nextBoolean();

                        // Создание и добавление сработает, только если данные валидны
                        Baggage newBaggage = new Baggage(tag, weight, flight, fragile);
                        storage.add(newBaggage);
                    } catch (Exception e) {
                        System.out.println("Ошибка добавления: " + e.getMessage());
                        sc.nextLine(); // Сброс неверного ввода в сканере
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
