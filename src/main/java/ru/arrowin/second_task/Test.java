package ru.arrowin.second_task;

/**
 * Класс для проверки тестов. Мне сказали, что не обязательно использовать консоль для создания интерфейса,
 * поэтому решил разделить задачи так, чтобы их можно было просматривать отдельно и тестировать.
 */
public class Test {
    private static final int MIN = -10; //минимальное число в массиве чисел
    private static final int MAX = 10; //максимальное число в массиве чисел
    private static final int SIZE = 15; //Размер массива. Не делайте его отрицательным.

    /**
     * Метод запуска данного решения
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.getAnswer(MIN, MAX, SIZE);
    }
}
