package ru.arrowin.first_task;


/**
 * Класс для проверки тестов. Мне сказали, что не обязательно использовать консоль для создания интерфейса,
 * поэтому решил разить задачи так, чтобы их можно было просматривать отдельно.
 */
public class Test {
    private static final int MIN = -10; //минимальное число в массиве чисел
    private static final int MAX = 10; //максимальное число в массиве чисел
    private static final int SIZE = 15; //Размер массива. Не делайте его отрицательным.

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.getAnswer(MIN, MAX, SIZE);
    }
}
