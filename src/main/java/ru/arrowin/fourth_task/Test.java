package ru.arrowin.fourth_task;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {

    //хранилище нескольких тестов, которые пришли мне в голову
    public static Map<Integer[], Integer> tests = new HashMap<>();

    /**
     * Класс для проверки тестов. Мне сказали, что не обязательно использовать консоль для создания интерфейса,
     * поэтому решил разить задачи так, чтобы их можно было просматривать отдельно.
     */
    public static void main(String[] args) {
        fillTests();
        Solution solution = new Solution();
        for (Map.Entry<Integer[], Integer> entry : tests.entrySet()) {
            int[] intArray = Arrays.stream(entry.getKey()).mapToInt(Integer::intValue).toArray();
            solution.getAnswer(intArray, entry.getValue());
            System.out.println();
        }
    }

    /**
     * Функция, которая записывает тесты.
     */
    private static void fillTests() {
        tests.put(new Integer[]{17, 12, 10, 4, 9, 8}, 2);
        tests.put(new Integer[]{10, 0, 1, 9, 1, 1}, 2);
        tests.put(new Integer[]{1, 1, 1, 1, 1, 1}, 6);
        tests.put(new Integer[]{10, 11, 9, 3, 3, 3}, 2);
        tests.put(new Integer[]{10, 11, 9, 3, 3, 3}, 3);
        tests.put(new Integer[]{1, 1, 1, 1, 13, 1}, 4);
        tests.put(new Integer[]{4, 4, 4, 4, 4}, 2);

    }

}
