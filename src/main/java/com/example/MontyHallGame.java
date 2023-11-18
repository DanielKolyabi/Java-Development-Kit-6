package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MontyHallGame {
  private static final int TOTAL_TRIALS = 1000;

  public static void main(String[] args) {
    // Создаем карту для хранения результатов (шаг цикла -> результат)
    Map<Integer, Boolean> results = new HashMap<>();

    // Выполняем серию игр
    for (int i = 1; i <= TOTAL_TRIALS; i++) {
      // Играем в парадокс Монти Холла и сохраняем результат
      boolean win = playMontyHallGame();
      results.put(i, win);
    }

    // Выводим статистику
    int positiveResults = (int) results.values().stream().filter(Boolean::valueOf).count();
    int negativeResults = TOTAL_TRIALS - positiveResults;
    double positivePercentage = (double) positiveResults / TOTAL_TRIALS * 100;

    System.out.println("Позитивные результаты: " + positiveResults);
    System.out.println("Негативные результаты: " + negativeResults);
    System.out.println("Процент позитивных результатов: " + positivePercentage + "%");
  }

  private static boolean playMontyHallGame() {
    // Создаем объект Random для генерации случайных чисел
    Random random = new Random();

    // Генерируем случайный номер двери, за которой находится автомобиль (1, 2 или 3)
    int carDoor = random.nextInt(3) + 1;

    // Игрок выбирает одну из трех дверей
    int playerChoice = random.nextInt(3) + 1;

    // Ведущий открывает одну из дверей с козлом (не выбранной игроком и не содержащей автомобиль)
    int goatDoor;
    do {
      goatDoor = random.nextInt(3) + 1;
    } while (goatDoor == carDoor || goatDoor == playerChoice);

    // Игрок принимает решение изменить выбор
    boolean switchChoice = random.nextBoolean();

    // Если игрок решает изменить выбор, меняем его выбор
    if (switchChoice) {
      do {
        playerChoice = random.nextInt(3) + 1;
      } while (playerChoice == goatDoor);
    }

    // Проверка, выиграл ли игрок
    return playerChoice == carDoor;
  }
}
