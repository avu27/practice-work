package title7;

import java.util.Collection;

/**
 * Обобщенная структура данных типа Стек.
 * Реализована на базе связных динамических узлов.
 */
public class Stack<T> {

    // Внутренний приватный статический узел структуры (инкапсуляция)
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<T> top = null;
    private int size = 0;

    public void push(T element) {
        top = new Node<>(element, top);
        size++;
    }

    public T pop() {
        // Бросаем Unchecked-исключение при извлечении из структуры без элементов
        if (isEmpty()) {
            throw new IllegalStateException("Критическая ошибка! Попытка вызова pop() из пустого стека.");
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    /**
     * Метод выталкивания элементов по правилу PECS (Consumer Super — Пункт 5).
     * Стек выступает производителем, а коллекция-приемник — потребителем (Consumer).
     */
    public void popAll(Collection<? super T> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }
}
