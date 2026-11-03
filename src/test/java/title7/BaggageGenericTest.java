package title7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class BaggageGenericTest {

    @Test
    void testStackUncheckedExceptionOnEmptyPop() {
        // Проверка выбрасывания unchecked-исключения из пустой структуры
        Stack<Integer> numStack = new Stack<>();
        assertThrows(IllegalStateException.class, () -> {
            numStack.pop();
        }, "Вызов pop() из пустого стека обязан сгенерировать IllegalStateException!");
    }

    @Test
    void testAirportBracketValidatorLogic() {
        // Проверка корректности работы обобщенного алгоритма валидации скобок
        String testLineOk = "([Аэропорт Хабаровск])";
        String testLineErr = "([Аэропорт Владивосток})";

        assertTrue(AirportUtils.checkBrackets(testLineOk).getFirst(), "Правильная строка должна вернуть true");
        assertFalse(AirportUtils.checkBrackets(testLineErr).getFirst(), "Ошибочная строка должна вернуть false");
    }

    @Test
    void testPecsConsumerSuperPopAll() {
        // Проверка работоспособности правила PECS уайлдкарда ? super T
        Stack<String> stringStack = new Stack<>();
        stringStack.push("Элемент");

        List<Object> objectList = new ArrayList<>();
        stringStack.popAll(objectList); // Должно скомпилироваться и выполниться без ошибок

        assertEquals(1, objectList.size(), "Элемент должен успешно перекочевать в список Object");
        assertEquals("Элемент", objectList.get(0));
    }
}
