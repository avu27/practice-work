package title6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class BaggageExceptionTest {

    @Test
    void testCheckInThrowsExcessBaggageException() {
        // 1. Тест проверяемого (checked) исключения перевеса (Вариант №18)
        // Создаем багаж весом 35 кг при норме лимита 23 кг
        Baggage heavyBaggage = new Baggage("TA-999", 35.0, "SU-100");

        // Проверяем, что метод checkIn бросает именно ExcessBaggageException
        assertThrows(ExcessBaggageException.class, () -> {
            heavyBaggage.checkIn(23.0);
        }, "Метод checkIn обязан бросить ExcessBaggageException при перевесе!");
    }

    @Test
    void testParseTagThrowsIllegalArgumentExceptionWithCause() {
        // 2. Тест обработки неверного формата строки (Пункт 3)
        String invalidTag = "ABC-строковые-буквы";

        // Проверяем, что метод parseTag бросает именно IllegalArgumentException
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Baggage.parseTag(invalidTag);
        }, "Метод parseTag обязан бросить IllegalArgumentException при неверном формате!");

        // Проверяем, что внутри лежит первопричина (cause) — NumberFormatException
        assertEquals(NumberFormatException.class, exception.getCause().getClass(),
                "Внутри исключения должна лежать первопричина NumberFormatException!");
    }

    @Test
    void testSuccessfulCheckInWithoutExceptions() {
        // 3. Тест нормального сценария без генерации ошибок
        Baggage normalBaggage = new Baggage("OK-200", 12.5, "SU-100");
        try {
            normalBaggage.checkIn(23.0); // Перевеса нет, метод должен отработать штатно
        } catch (ExcessBaggageException e) {
            fail("Исключение не должно было бросаться при нормальном весе багажа!");
        }
    }
}
