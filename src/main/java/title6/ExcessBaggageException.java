package title6;

/**
 * Проверяемое (checked) исключение варианта №18.
 * Выбрасывается при нарушении инварианта: вес багажа превышает установленную норму.
 */
public class ExcessBaggageException extends Exception {
    public ExcessBaggageException(String message) {
        super(message);
    }
}

