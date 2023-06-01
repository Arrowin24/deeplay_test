package ru.arrowin.fourth_task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Написать программу распределения данного набора целых чисел (возможны
 * повторяющиеся числа) на заданное число групп так, чтобы были равными суммы
 * чисел, входящих в каждую группу. Если это сделать невозможно, то программа
 * определяет этот факт. Вывести на экран исходные данные и результаты. Задачу
 * необходимо решить точно: не эвристический, а точный алгоритм. Допустимо
 * использовать полный перебор всех вариантов распределения чисел по группам
 */

/* ПОЯСНЕНИЕ. Для запуска теста достаточно запустить main в классе Test.
 *            Решение работает, однако саму рекурсивную функцию придумал НЕ Я.
 *
 */
public class Solution {
    /**
     * Функция для группировки решения и вывода ответа в консоль.
     * Внутри проверяю возможность разделения массива.
     *
     * @param arr      набор целых чисел
     * @param numGroup заданное число групп
     */
    public void getAnswer(int[] arr, int numGroup) {
        System.out.println("Начальный набор " + Arrays.toString(arr));
        System.out.println("Нужно разделить на " + numGroup + " групп");
        if (!canSplit(arr, numGroup)) {                 //проверка суммы чисел
            System.out.println("НЕВОЗМОЖНО!!!!!");
            return;
        }
        List<List<Integer>> groups = new ArrayList<>();
        for (int i = 0; i < numGroup; i++) {
            groups.add(new ArrayList<>());
        }
        if (!split(arr, groups, 0)) {       //деления групп
            System.out.println("НЕВОЗМОЖНО!!!!!");
            return;
        }
       if(!isGroupsSizeMatch(groups)){              //проверка равномерного распределения чисел по группам
            System.out.println("НЕВОЗМОЖНО!!!!!");
            return;
        }
        System.out.println("Получилось разделить на следующие группы: ");
        for (int i = 0; i < groups.size(); i++) {
            System.out.println("Группа " + (i + 1) + ": " + groups.get(i));
        }
    }

    /**
     * Проверяю сумму чисел на возможность разделения на группы
     *
     * @param arr      набор целых чисел
     * @param numGroup заданное число групп
     * @return может ли сумма набора чисел делиться на число групп или нет
     */
    private boolean canSplit(int[] arr, int numGroup) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum % numGroup == 0;
    }

    /**
     * Костыль для сравнения размерности групп
     */
    private boolean isGroupsSizeMatch(List<List<Integer>> groups) {
        return groups.get(0).size()==groups.get(groups.size()-1).size();
    }


    /**
     * Рекурсивно разделяю массив на группы с одинаковой суммой элементов
     *
     * @param arr    набор целых чисел
     * @param groups список для хранения групп
     * @param index  начальный индекс перебора чисел
     * @return true если такое разделение можно сделать.
     */
    private boolean split(int[] arr, List<List<Integer>> groups, int index) {
        if (index == arr.length) {                  //когда прошли весь массив
            int sum = 0;                            //проверяем сумму в каждом подмассиве
            for (List<Integer> group : groups) {
                int groupSum = 0;
                for (Integer num : group) {
                    groupSum += num;
                }
                if (sum == 0) {                 //если это первая итерация, то тогда задаем первую сумму
                    sum = groupSum;
                }
                if (sum != groupSum) {          //если в последующих итерациях суммы не равны, то возвращаем false
                    return false;
                }
            }
            return true;                    //так как везде суммы равны, то возвращаем true
        }
        for (List<Integer> group : groups) { //пока индекс меньше длины массива добавляю элемент под индексом в каждую
            group.add(arr[index]);          //группу массива
            if (split(arr, groups, index + 1)) {    //далее вызываю рекурсивно этот же метод с увеличением индекса
                return true;
            }
            group.remove(group.size() - 1); //если не получилось разделить, то удаляю элемент из группы.
        }
        return false;
    }

}
