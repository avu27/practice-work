package title5.title5;

public interface Maintainable {
    void performMaintenance();

    // 5. default-метод в одном из интерфейсов
    default void checkSafetyProtocols() {
        System.out.println("   [Безопасность] Протоколы защиты и датчики аварийной остановки проверены. Нарушений нет.");
    }
}
