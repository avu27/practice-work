package title5;

@FunctionalInterface
public interface Selection {
    // Принимает базовый класс варианта, возвращает boolean
    boolean test(Machine machine);
}
