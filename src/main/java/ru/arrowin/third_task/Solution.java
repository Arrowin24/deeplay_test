package ru.arrowin.third_task;

/**
 * Играют 2 игрока. Каждый из них перед игрой тайно от другого игрока выбирает по одной
 * последовательности из 3 чисел от 1 до 6 (числа могут совпадать). Далее они по очереди
 * бросают кубик определенное число раз (100, 1000, ...). Каждый из игроков получает в игре
 * столько очков, сколько раз выпала последовательность из тех чисел, которые он выбрал. При
 * этом последовательности чисел одного игрока (которые приносят ему очки) не должны
 * пересекаться, а последовательности чисел разных игроков могут пересекаться.
 * Необходимо для двух фиксированных наборов 3 чисел для каждого из игроков и
 * фиксированного числа бросков кубика вычислить для обоих игроков вероятность набора
 * игроком большего, чем у соперника, числа очков, а также вероятность ничьей. Достаточно
 * приближенного вычисления, допустимо применить метод Монте-Карло.
 */

/* ПОЯСНЕНИЕ. Для запуска теста достаточно запустить main в классе Test.
 *            Для создания массива использовалась функция createRandomArray() из решения первой задачи (FirstTask).
 *            Поиск подмассива в массиве я решал на HackerRank, а с методом Монте-Карло познакомился
 *            в магистратуре университета. Спасибо, что напомнили про него.
 *            Единственное, мне кажется, что для метода Монте-Карло можно прописать итерации с помощью многопоточности,
 *            однако я решил сделать реализацию в лоб, так как не знал, будет ли это лучше. Был бы наставник, спросил)
 */

import java.util.Arrays;

import static ru.arrowin.first_task.Solution.createRandomArray;

/**
 * Выделил отдельный класс, в котором весь функционал решения третьего задания.
 */
public class Solution {
    private final int MIN = 1; //Наименьшее число на кубике
    private final int MAX = 6;//Наименьшее число на кубике

    /*Так как я вероятности округляю до сотых, то я решил пойти итеративно и взял минимальное число
     *итераций, при которых ответ перестает изменяться, то есть погрешность метода не влияет на
     *ответ
     */
    private final int MonteCarloIterations = 10_000;// Количество итераций для метода Монте-Карло

    /**
     * Функция для построения ответа для данного задания. Пожалуйста, не заносите некорректные
     * данные(т.е. массив большей размерностью чем 3, и числа меньше 1 и больше 3)
     *
     * @param playerOneArray массив, выбранный первым игроком
     * @param playerTwoArray массив, выбранный вторым игроком
     * @param numberDiceRoll количество бросков кубика.
     */
    public void getAnswer(int[] playerOneArray, int[] playerTwoArray, int numberDiceRoll) {
        System.out.println("Количество итераций для метода Монте-Карло: " + MonteCarloIterations);
        System.out.println("Первый игрок загадал последовательность: " + Arrays.toString(playerOneArray));
        System.out.println("Второй игрок загадал последовательность: " + Arrays.toString(playerTwoArray));
        doMonteCarlo(playerOneArray, playerTwoArray, numberDiceRoll, MonteCarloIterations);
    }

    /**
     * Функция для подсчета количества очков игроком
     * @param playerArray массив, выбранный игроком
     * @param fullArray массив, получившийся при бросании кубика
     * @return количество очков, полученных игроком
     */

    private int countSubArrayMatches(int[] playerArray, int[] fullArray) {
        int count = 0;
        for (int i = 0; i <= fullArray.length - playerArray.length; i++) { //Пробегаю по всему массиву
            if (fullArray[i] == playerArray[0]) { //Если первая цифра совпадает, то проверяю остальные
                boolean isMatch = true;
                for (int j = 1; j < playerArray.length; j++) {
                    if (fullArray[i + j] != playerArray[j]) {   //если нашел несовпадение, то не считаю результат
                        isMatch = false;                        // и выхожу из массива
                        break;
                    }
                }
                if (isMatch) {                      //если все хорошо, то увеличиваю счетчик
                    count++;
                    i += playerArray.length - 1;   //смещаюсь по массиву от бросков, чтобы отбросить несовпадения
                }
            }
        }
        return count;
    }


    /**
     * Итерационный метод Монте-Карло. В пояснении я написал, что мне кажется, можно его изменить.
     * Данный метод позволяет определить вероятности победы игроков.
     * @param playerOneArray массив, выбранный первым игроком
     * @param playerTwoArray массив, выбранный вторым игроком
     * @param numberDiceRoll количество бросков кубика.
     * @param monteCarloIterations количество итераций методом Монте-Карло. Установил 10_000.
     */
    private void doMonteCarlo(
            int[] playerOneArray, int[] playerTwoArray, int numberDiceRoll, int monteCarloIterations)
    {
        int playerOneWins = 0;                  //счетчик побед первого игрока
        int playerTwoWins = 0;                  //счетчик побед второго игрока

        for (int i = 0; i < monteCarloIterations; i++) {
            int[] array = createRandomArray(MIN, MAX, numberDiceRoll); //использую функцию из первого задания для
                                                                        //создания имитации бросков
            int playerOneScore = countSubArrayMatches(playerOneArray, array); //считаю очки первого игрока
            int playerTwoScore = countSubArrayMatches(playerTwoArray, array); //считаю очки второго игрока
            if (playerOneScore > playerTwoScore) {      //при победе первого увеличиваю его счетчик
                playerOneWins++;
            }
            if (playerOneScore < playerTwoScore) {      //при победе второго увеличиваю его счетчик
                playerTwoWins++;
            }

        }
        //Тут считаю и вывожу на экран вероятности победы первого, второго и ничью. Ничью не стал отдельно счетчиком
        //делать так как всего 3 исхода. Хотя наверное нужно было для победы второго игрока. Тут можно поговорить.
        System.out.println("Вероятность победы первого игрока: "
                                   + String.format("%.2f", (double) playerOneWins / (double) monteCarloIterations));
        System.out.println("Вероятность победы второго игрока: "
                                   + String.format("%.2f", (double) playerTwoWins / (double) monteCarloIterations));
        System.out.println(
                "Вероятность ничьи: "
                        + String.format("%.2f", (1d - (double)(playerOneWins+playerTwoWins)/ (double) monteCarloIterations)));

    }

}
