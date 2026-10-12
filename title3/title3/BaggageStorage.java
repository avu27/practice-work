package title3;

import java.util.ArrayList;

public class BaggageStorage {
    private final ArrayList<Baggage> list;

    public BaggageStorage() {
        this.list = new ArrayList<>();
    }

    public void add(Baggage baggage) {
        if (baggage == null) return;
        if (list.contains(baggage)) {
            System.out.println("Ошибка: Багаж с биркой " + baggage.getTag() + " уже зарегистрирован!");
            return;
        }
        list.add(baggage);
        System.out.println("Успешно добавлено: " + baggage.getTag());
    }

    public void printAll() {
        if (list.isEmpty()) {
            System.out.println("Хранилище багажа пусто.");
            return;
        }
        System.out.println("--- Полный список зарегистрированного багажа ---");
        for (Baggage b : list) System.out.println(b);
    }

    public void findFragile() {
        System.out.println("--- Результаты поиска (Хрупкий багаж) ---");
        boolean found = false;
        for (Baggage b : list) {
            if (b.isFragile()) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("Хрупкий багаж в системе не обнаружен.");
    }

    public void removeByTag(String tag) {
        Baggage dummy = new Baggage(tag, 1.0, "DUMMY", false);
        if (list.remove(dummy)) {
            System.out.println("Багаж с биркой " + tag + " успешно удален.");
        } else {
            System.out.println("Ошибка: Багаж с биркой " + tag + " не найден.");
        }
    }
}