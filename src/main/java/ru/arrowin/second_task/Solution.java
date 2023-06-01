package ru.arrowin.second_task;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static ru.arrowin.first_task.Solution.createRandomArray;

/**
 * Условие задания:
 * Найти в массиве наиболее часто встречающееся число (числа, если таких несколько),
 * вывести на экран исходные данные и результаты.
 */

/* ПОЯСНЕНИЕ. Для запуска теста достаточно запустить main в классе Test.
 *            Для создания массива использовалась функция createRandomArray() из решения первой задачи (FirstTask).
 *            Данную задачу я решал либо на HackerRang, либо на LeetCode, только там был массив букв.
 *            Если бы нужно было найти одно число, а не несколько, то я бы использовал stream().findFirst();
 */

/**
 * Выделил отдельный класс, в котором весь функционал решения второго задания.
 */
public class Solution {
    /**
     * Мапа для хранения количество повторяющих цифр
     */
    private static final Map<Integer, Integer> count = new HashMap<>();

    /**
     * Функция для группировки решения. Используется в main для вывода информации в консоль.
     *
     * @param min  минимальное число в массиве чисел
     * @param max  максимальное число в массиве чисел
     * @param size размер массива
     */
    public void getAnswer(int min, int max, int size) {
        int[] randomArr = createRandomArray(min, max, size);
        countNums(randomArr);
        System.out.println("Исходный массив:");
        System.out.println(Arrays.toString(randomArr));
        printMaxRepeatedNums();
    }

    /**
     * Метод для подсчета повторений всех чисел. Записывает количество повторений в Мапу count.
     *
     * @param arr массив, в котором необходимо найти наиболее часто встречающееся число.
     */
    private void countNums(int[] arr) {
        for (int num : arr) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
    }

    /**
     * Прохожу по значениям в мапе для поиска наиболее часто встречающего числа.
     *
     * @return количество повторений наиболее часто встречающегося числа
     */
    private int getMaxRepeatedNum() {
        int maxCount = 0;
        for (Integer counts : count.values()) {
            if (counts > maxCount) {
                maxCount = counts;
            }
        }
        return maxCount;
    }

    /**
     * Функция для вывода в консоль всех наиболее часто встречающихся чисел с количеством их повторений
     */
    public void printMaxRepeatedNums() {
        int maxCount = getMaxRepeatedNum();
        System.out.println("Наиболее часто встречающееся число(а):");
        if(count.isEmpty()){
            System.out.println("Первоначальный массив пуст, поэтому ничего не встречается. Печалька");
        }
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() == maxCount) {
                System.out.println(entry.getKey() + " (встречается " + maxCount + " раз(а))");
            }
        }
    }

}
