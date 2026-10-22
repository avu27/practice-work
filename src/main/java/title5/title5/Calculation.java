package title5.title5;

@FunctionalInterface
public interface Calculation {
    // Принимает базовый класс варианта, возвращает double
    double calculate(Machine machine);
}
