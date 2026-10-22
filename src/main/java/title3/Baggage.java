package title3;

import java.util.Objects;

public class Baggage {
    private String tag;
    private double weight;
    private String flight;
    private boolean fragile;

    public Baggage(String tag, double weight, String flight, boolean fragile) {
        if (tag == null || tag.trim().isEmpty()) {
            throw new IllegalArgumentException("Ошибка: Номер бирки не может быть пустым.");
        }
        if (flight == null || flight.trim().isEmpty()) {
            throw new IllegalArgumentException("Ошибка: Номер рейса не может быть пустым.");
        }
        if (weight <= 0.0 || weight > 100.0) {
            throw new IllegalArgumentException("Ошибка: Вес багажа должен быть от 0 до 100 кг.");
        }
        this.tag = tag;
        this.weight = weight;
        this.flight = flight;
        this.fragile = fragile;
    }

    public String getTag() { return tag; }
    public double getWeight() { return weight; }
    public String getFlight() { return flight; }
    public boolean isFragile() { return fragile; }

    @Override
    public String toString() {
        return String.format("Багаж [Бирка: %s, Вес: %.2f кг, Рейс: %s, Хрупкий: %s]",
                tag, weight, flight, fragile ? "Да" : "Нет");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Baggage baggage = (Baggage) o;
        return Objects.equals(tag, baggage.tag);
    }

    @Override
    public int hashCode() { return Objects.hash(tag); }
}