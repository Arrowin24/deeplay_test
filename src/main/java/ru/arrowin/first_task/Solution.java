package ru.arrowin.first_task;

import java.util.Arrays;
/*
 * Условие задания:
 * Заполнить массив случайными целыми числами. Вывести массив на экран. Переупорядочить
 * в этом массиве элементы следующим образом: сначала по не убыванию нечетные числа,
 * потом нули, потом прочие числа по не возрастанию. Вывести массив на экран.
 */

/* ПОЯСНЕНИЕ. Для запуска теста достаточно запустить main в классе Test. Если нужно изменить параметры массива
 *            случайных чисел, то можно изменить MIN, MAX, SIZE в этом же классе.
 *            Первоначально я сделал решение с использованием ArrayList, но потом подумал, что для данной задачи
 *            можно обойтись только массивами. Также не стал реализовывать стандартную функцию Array.sort(), так как
 *            посчитал это избыточным для данного задания.
 */

/**
 * Выделил отдельный класс, в котором весь функционал решения первого задания.
 */
public class Solution {
    /**
     * Функция для группировки решения. Используется в main для вывода информации в консоль.
     *
     * @param min  минимальное число в массиве чисел
     * @param max  максимальное число в массиве чисел
     * @param size размер массива
     */
    public void getAnswer(int min, int max, int size) {
        int[] randomArr = createRandomArray(min, max, size);
        System.out.println("Исходный массив:");
        System.out.println(Arrays.toString(randomArr));
        sortArray(randomArr);
        System.out.println("Сортированный массив:");
        System.out.println(Arrays.toString(randomArr));
    }

    /**
     * Функция для создания массива случайных целыми чисел.
     * Используется в дальнейших решениях.
     *
     * @param min  минимальное число в массиве чисел
     * @param max  максимальное число в массиве чисел
     * @param size размер массива
     * @return массив случайных целых чисел
     */
    public static int[] createRandomArray(int min, int max, int size) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (max + 1 - min)) + min;
        }
        return arr;
    }

    /**
     * Функция для сортировки массива по следующему правилу:
     * сначала по не убыванию нечетные числа,
     * потом нули, потом прочие числа по не возрастанию.
     *
     * @param arr массив, подлежащий сортировки
     */
    private void sortArray(int[] arr) {
        int[] odds = new int[arr.length];       //массив для хранения нечетных чисел
        int[] evens = new int[arr.length];      //массив для хранения четных чисел
        int countZeros = 0;                     //счетчик нулей
        int countOdds = 0;                      //счетчик для массива нечетных чисел
        int countEvens = 0;                     //счетчик для массива четных чисел

        for (int num : arr) {                   //проходим по всему массиву и либо считаем нули,
            if (num == 0) {                     //либо заносим числа в массивы для хранения
                countZeros++;
                continue;
            }
            if (num % 2 != 0) {
                odds[countOdds++] = num;
            } else {
                evens[countEvens++] = num;
            }
        }

        Arrays.sort(odds, 0, countOdds);    //сортируем по возрастанию массив нечетных чисел
        Arrays.sort(evens, 0, countEvens);  //сортируем по возрастанию массив четных чисел
        int index = 0;                               //индекс для перезаписывания числа в первоначальный массив
        while (index < countOdds) {                  //перезаписываем нечетные цифры
            arr[index] = odds[index++];
        }
        while (index < countOdds + countZeros) {     //заменяем середину на нули
            arr[index++] = 0;
        }
        countEvens--;
        while (countEvens >= 0) {                   //добавляем в обратном порядке четные цифры
            arr[index++] = evens[countEvens--];
        }
    }

}