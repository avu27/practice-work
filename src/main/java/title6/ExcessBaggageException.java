package title6;

/**
 * Выбрасывается при нарушении: вес багажа превышает установленную норму.
 */
public class ExcessBaggageException extends Exception {
    public ExcessBaggageException(String message) {
        super(message);
    }
}

