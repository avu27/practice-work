package title6;

public class Baggage {
    private final String tag;
    private final double weight;
    private final String flight;

    public Baggage(String tag, double weight, String flight) {
        this.tag = tag;
        this.weight = weight;
        this.flight = flight;
    }

    /**
     * Бизнес-метод регистрации багажа (Пункт 2).
     * Проверяет инвариант: weight <= limit. При нарушении бросает checked-исключение.
     */
    public void checkIn(double limit) throws ExcessBaggageException {
        if (this.weight > limit) {
            throw new ExcessBaggageException(
                    String.format("Превышен лимит веса! Багаж: %.1f кг, Норма: %.1f кг.", this.weight, limit)
            );
        }
        System.out.printf("   [УСПЕХ] Багаж успешно зарегистрирован на рейс %s.%n", flight);
    }

    /**
     * Метод разбора числового кода из тега багажа (Пункт 3).
     * При неверном текстовом формате бросает IllegalArgumentException со вложенной причиной.
     */
    public static int parseTag(String tagStr) {
        if (tagStr == null || tagStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Номер тега не может быть пустым!");
        }
        try {
            return Integer.parseInt(tagStr.trim());
        } catch (NumberFormatException e) {
            // Оборачиваем ошибку формата в IllegalArgumentException и передаем причину (cause)
            throw new IllegalArgumentException("Ошибка разбора! Неверный формат тега: '" + tagStr + "'", e);
        }
    }

    @Override
    public String toString() {
        return "Багаж{" + "бирка='" + tag + '\'' + ", вес=" + weight + ", рейс='" + flight + '\'' + '}';
    }
}
