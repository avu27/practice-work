package title7;

public class AirportUtils {

    /**
     * Алгоритм верификации скобочных последовательностей через Stack различных символов.
     * Служит для проверки синтаксиса вложенных диспетчерских команд логов Аэропорта.
     */
    public static Pair<Boolean, String> checkBrackets(String expression) {
        Stack<Character> stack = new Stack<>(); // Обобщенная структура Stack<Character> (Пункт 2 и 4)

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return Pair.of(false, "Лишний закрывающий символ '" + ch + "' в позиции " + i);
                }
                char open = stack.pop();
                if ((ch == ')' && open != '(')
                        || (ch == ']' && open != '[')
                        || (ch == '}' && open != '{')) {
                    return Pair.of(false, "Конфликт вложенности: скобка '" + open + "' закрыта символом '" + ch + "'");
                }
            }
        }

        if (!stack.isEmpty()) {
            return Pair.of(false, "Обнаружены незакрытые скобочные последовательности!");
        }

        return Pair.of(true, "Синтаксический анализ лога завершен успешно. Ошибок не обнаружено.");
    }
}

