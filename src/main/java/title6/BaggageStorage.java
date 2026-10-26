package title6;

import java.util.ArrayList;
import java.util.List;

public class BaggageStorage {
    private final List<Baggage> storage = new ArrayList<>();

    public void add(Baggage b) {
        storage.add(b);
    }

    public void printAll() {
        if (storage.isEmpty()) {
            System.out.println("Хранилище багажа пусто.");
            return;
        }
        System.out.println("--- Список зарегистрированного багажа ---");
        for (Baggage b : storage) {
            System.out.println("  " + b);
        }
    }
}
