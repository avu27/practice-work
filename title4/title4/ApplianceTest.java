package title4;

// Импортируем встроенные инструменты тестирования JUnit
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApplianceTest {

    @Test
    public void testFridgeCreationAndInheritance() {
        // Тестируем создание Холодильника и наследование полей от базового класса
        Fridge fridge = new Fridge("Bosch", 200.0, 350);

        assertEquals("Bosch", fridge.getBrand(), "Марка должна корректно наследоваться");
        assertEquals(200.0, fridge.getPower(), 0.01, "Мощность должна корректно наследоваться");
        assertEquals(350, fridge.getVolume(), "Уникальное поле объема должно инициализироваться");
    }

    @Test
    public void testOvenInstanceofLogic() {
        // Тестируем, что полиморфные ссылки правильно распознают типы через instanceof
        Appliance myOven = new Oven("Gorenje", 3000.0, 275);
        Appliance myFridge = new Fridge("Samsung", 150.0, 410);

        // Проверяем логику instanceof для духовки
        assertTrue(myOven instanceof Oven, "Объект Духовки должен распознаваться как Oven");
        assertFalse(myFridge instanceof Oven, "Объект Холодильника НЕ должен распознаваться как Oven");
    }
}
