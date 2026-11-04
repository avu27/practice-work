package title8;

/**
 * Универсальный функциональный интерфейс (Пункт 3 Стандартных интерфейсов).
 */
@FunctionalInterface
public interface Calculation<T> {
    double calculate(T value);
}
