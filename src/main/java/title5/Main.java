package title5;

public class Main {

    public static void printHello() {
        System.out.println("=========================================");
        System.out.println("ФИО: Урбан Александра Вадимовна");
        System.out.println("Группа: БОМ31ТВР");
        System.out.println("Практическая работа №5: Интерфейсы, лямбды и функциональные интерфейсы");
        System.out.println("Вариант №18: Промышленное оборудование (Machine)");
        System.out.println("=========================================");
        System.out.println();
    }

    public static void main(String[] args) {
        printHello();

        // Подготовка массива объектов разных наследников
        Machine[] factoryFloor = new Machine[]{
                new Press("Гидравлик-3000", 0.90, 45),
                new Conveyor("Главная Лента Сборки", 0.95, 1.2),
                new CNC("Фрезер-5-Осей", 0.85, 5),
                new Press("Малый Штамп", 0.80, 20),
                new CNC("Токарь-Робот", 0.90, 3)
        };

        MachineService service = new MachineService(factoryFloor);

        // Вызов общего отчета по интерфейсам (Общая постановка)
        service.processServiceOperations();
        System.out.println();

        // --- РАЗДЕЛ: ФУНКЦИОНАЛЬНЫЕ ИНТЕРФЕЙСЫ И ЛЯМБДЫ ---

        // Задание 2: Реализация Calculation двумя способами (Анонимный класс и Лямбда)
        System.out.println("--- Сравнение способов вычисления общей производительности цеха ---");

        // Способ А: Анонимный класс
        Calculation anonCalc = new Calculation() {
            @Override
            public double calculate(Machine machine) {
                return machine.throughput(); // Прямой вызов метода расчета объекта
            }
        };
        double totalAnon = service.calculateTotalThroughputCustom(anonCalc);
        System.out.printf("Сумма результатов (через анонимный класс): %.2f%n", totalAnon);

        // Способ Б: Лямбда-выражение
        Calculation lambdaCalc = (m) -> m.throughput();
        double totalLambda = service.calculateTotalThroughputCustom(lambdaCalc);
        System.out.printf("Сумма результатов (через лямбда-выражение): %.2f%n", totalLambda);
        System.out.println("Проверка совпадения сумм: " + (totalAnon == totalLambda ? "СУММЫ СОВПАЛИ" : "ОШИБКА"));
        System.out.println();

        // Задание 3: Реализация Selection лямбдой для отбора по порогу (захват effectively final)
        // Создаем локальные переменные порогов
        double thresholdLow = 1500.0;
        double thresholdHigh = 3000.0;

        // Лямбда захватывает локальную переменную 'thresholdLow'
        Selection lowFilter = (m) -> m.throughput() > thresholdLow;
        service.filterAndPrint(lowFilter, "Производительность превышает " + thresholdLow + " ед/час");

        // Лямбда захватывает локальную переменную 'thresholdHigh'
        Selection highFilter = (m) -> m.throughput() > thresholdHigh;
        service.filterAndPrint(highFilter, "Производительность превышает " + thresholdHigh + " ед/час");

        // Задание 4: Присвоение ссылки на метод printReport переменной Runnable
        System.out.println("--- Запуск отчёта через ссылку на метод и интерфейс Runnable ---");
        Runnable reportRunner = service::printReport;
        reportRunner.run(); // Прямой вызов без запуска отдельного потока
    }
}
