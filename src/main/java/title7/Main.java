package title7;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("ПРАКТИЧЕСКАЯ РАБОТА №7 | ВАРИАНТ №18 (GENERICS)");
        System.out.println("Выполнила: Урбан А.В. группа БОМ31ТВР");
        System.out.println("=================================================");

        // Проверка Обобщенного алгоритма на первом типе-параметре: Character
        System.out.println("\n[ТЕСТ 1] Верификация скобочных блоков в авиационных логах:");
        String validLog = "Борт SU-1702 [Статус: Вылет (Хабаровск) -> Прибытие (Владивосток)] - Ок.";
        String invalidLog = "Борт SU-2101 [Статус: Задержка рейса (Погода) })";

        Pair<Boolean, String> logRes1 = AirportUtils.checkBrackets(validLog);
        System.out.println(" Результат 1: " + logRes1.getSecond());

        Pair<Boolean, String> logRes2 = AirportUtils.checkBrackets(invalidLog);
        System.out.println(" Результат 2: " + logRes2.getSecond());

        // Проверка структуры на втором типе-параметре: String (Пункт 4)
        System.out.println("\n[ТЕСТ 2] Проверка обобщенного Stack с типом параметров <String>:");
        Stack<String> flightBaggage = new Stack<>();
        flightBaggage.push("Контейнер_Багажа_А");
        flightBaggage.push("Контейнер_Багажа_Б");
        System.out.println(" Извлечено из стека: " + flightBaggage.pop());
        System.out.println(" Извлечено из стека: " + flightBaggage.pop());

        // Проверка логики PECS метода popAll (? super T)
        System.out.println("\n[ТЕСТ 3] Перенос данных по правилу PECS (popAll):");
        Stack<String> localCargo = new Stack<>();
        localCargo.push("Спецгруз_1");
        localCargo.push("Спецгруз_2");

        List<Object> centralWarehouse = new ArrayList<>(); // Объект-супертип для String
        localCargo.popAll(centralWarehouse); // Метод принимает коллекцию благодаря <? super T>!
        System.out.println(" Элементы вытолкнуты на центральный склад: " + centralWarehouse);

        // Проверка срабатывания Unchecked-исключения из пустой структуры
        System.out.println("\n[ТЕСТ 4] Проверка вызова исключения из пустого стека:");
        try {
            flightBaggage.pop();
        } catch (IllegalStateException e) {
            System.out.println(" Перехвачено ожидаемое исключение: " + e.getMessage());
        }
    }
}
