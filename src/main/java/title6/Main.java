package title6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main {
    // Пункт 6: Режим debug. Если true — выводим подробный stack trace ошибок на экран
    private static final boolean DEBUG_MODE = true;
    private static final String LOG_FILE_PATH = "log.txt";

    /**
     * Пункт 5: Запись успешных операций в файл log.txt (UTF-8, Append).
     */
    private static void writeLog(String logMessage) {
        try {
            String entry = logMessage + System.lineSeparator();
            Files.writeString(
                    Paths.get(LOG_FILE_PATH),
                    entry,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.err.println("🚨 Ошибка записи лога в файл: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.nanoTime() > 0 ? System.in : new java.io.ByteArrayInputStream(new byte[0]));
        BaggageStorage storage = new BaggageStorage();

        System.out.println("=================================================");
        System.out.println("ПРАКТИЧЕСКАЯ РАБОТА №6 | ВАРИАНТ №18 (БАГАЖ)");
        System.out.println("Выполнила: Урбан А.В. группа БОМ31ТВР");
        System.out.println("=================================================");

        while (true) {
            System.out.println("\n--- ИНТЕРАКТИВНОЕ МЕНЮ СЛУЖБЫ КОНТРОЛЯ ---");
            System.out.println("1 - checkIn  (Регистрация нового багажа с лимитом веса)");
            System.out.println("2 - parseTag (Валидация числового кода бирки багажа)");
            System.out.println("3 - printAll (Просмотр текущей базы зарегистрированного багажа)");
            System.out.println("4 - exit     (Выход из программы)");
            System.out.print("Выберите действие: ");

            String input = scanner.nextLine().trim();

            if (input.equals("4") || input.equalsIgnoreCase("exit")) {
                System.out.println("Завершение работы службы контроля. До свидания!");
                break;
            }

            try {
                switch (input) {
                    case "1": // Выполнение операции checkIn
                        System.out.print("Введите номер буквенно-цифровой бирки: ");
                        String tag = scanner.nextLine().trim();
                        System.out.print("Введите фактический вес сумки (кг): ");
                        double weight = Double.parseDouble(scanner.nextLine().trim());
                        System.out.print("Введите установленный лимит авиакомпании (кг): ");
                        double limit = Double.parseDouble(scanner.nextLine().trim());

                        Baggage baggage = new Baggage(tag, weight, "SU-1702");

                        // Вызов метода, бросающего проверяемое исключение при перевесе
                        baggage.checkIn(limit);

                        // Если исключение не сработало — операция успешна, сохраняем в базу и пишем в лог
                        storage.add(baggage);
                        writeLog(String.format("[УСПЕХ REGR] Бирка: %s, Вес: %.1f кг, Норма: %.1f кг", tag, weight, limit));
                        break;

                    case "2": // Выполнение операции parseTag
                        System.out.print("Введите текстовый тег для проверки (только цифры): ");
                        String tagStr = scanner.nextLine();

                        // Метод выбросит IllegalArgumentException при ошибке формата текста
                        int parsedCode = Baggage.parseTag(tagStr);
                        System.out.printf("   [УСПЕХ] Тег прошёл валидацию! Десятичный код: %d%n", parsedCode);

                        writeLog(String.format("[УСПЕХ PARSE] Проверен тег '%s' -> Код: %d", tagStr, parsedCode));
                        break;

                    case "3":
                        storage.printAll();
                        break;

                    default:
                        System.out.println("🚨 Неверный ввод! Пожалуйста, выберите пункт от 1 до 4.");
                }

            } catch (ExcessBaggageException e) {
                // Перехват проверяемого исключения перевеса (Программа продолжает работу)
                System.out.println("❌ ОТКАЗ В РЕГИСТРАЦИИ: " + e.getMessage());
                if (DEBUG_MODE) { e.printStackTrace(); }

            } catch (IllegalArgumentException e) {
                // Перехват ошибок неверного формата парсинга чисел и кодов бирок
                System.out.println("❌ ОШИБКА ВВОДА/ФОРМАТА: " + e.getMessage());
                if (e.getCause() != null) {
                    System.out.println("   -> Первопричина системы (Cause): " + e.getCause());
                }
                if (DEBUG_MODE) { e.printStackTrace(); }

            } catch (Exception e) {
                // Глобальный перехват непредвиденных сбоев для обеспечения устойчивости программы
                System.out.println("❌ КРИТИЧЕСКИЙ СИСТЕМНЫЙ СБОЙ: " + e.getMessage());
                if (DEBUG_MODE) { e.printStackTrace(); }
            }
        }
        scanner.close();
    }
}

