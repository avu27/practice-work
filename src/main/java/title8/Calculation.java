package title8;

/**
 * Универсальный функциональный интерфейс.
 */
@FunctionalInterface
public interface Calculation<T> {
    double calculate(T value);
}
