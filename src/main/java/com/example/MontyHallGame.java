package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class MontyHallGame {
  private static final int TOTAL_TRIALS = 1000;

  public static void main(String[] args) {
    Map<Integer, Boolean> results = new HashMap<>();
    Map<Integer, List<Integer>> moves = new HashMap<>();

    for (int i = 1; i <= TOTAL_TRIALS; i++) {
      boolean win = playMontyHallGame(moves, i);
      results.put(i, win);
    }

    int positiveResults = (int) results.values().stream().filter(Boolean::valueOf).count();
    int negativeResults = TOTAL_TRIALS - positiveResults;
    double positivePercentage = (double) positiveResults / TOTAL_TRIALS * 100;

    System.out.println("Позитивные результаты: " + positiveResults);
    System.out.println("Негативные результаты: " + negativeResults);
    System.out.println("Процент позитивных результатов: " + positivePercentage + "%");

// Вывод ходов игр
    for (int i = 1; i <= TOTAL_TRIALS; i++) {
      List<Integer> gameMoves = moves.get(i);

      // Проверка, чтобы избежать ошибок, если что-то пошло не так
      if (gameMoves != null && gameMoves.size() >= 3) {
        int playerChoice = gameMoves.get(0);
        int goatDoor = gameMoves.get(1);
        int switchChoice = gameMoves.get(2);

        // Получение результата для текущей игры
        boolean win = results.get(i);

        // Формирование строки с пояснением
        String explanation = "Игрок выбрал дверь " + playerChoice + ", " +
            "Ведущий открывает дверь " + goatDoor + ", за которой козёл, " +
            (switchChoice > 0 ?
                "Игрок решает выбрать дверь " + switchChoice :
                "Игрок решает оставить выбранную дверь.") +
            (win ? " Игрок выйграл, за дверью авто! " : " Игрок проиграл, за дверью козёл! ");

        // Вывод строки с пояснением в консоль
        System.out.println("Ход игры " + i + ": " + explanation);
      } else {
        // Если что-то пошло не так, выведем сообщение об ошибке
        System.out.println("Ошибка при обработке хода игры " + i);
      }
    }


  }

  private static boolean playMontyHallGame(Map<Integer, List<Integer>> moves, int gameNumber) {
    Random random = new Random();

    int carDoor = random.nextInt(3) + 1;
    int playerChoice = random.nextInt(3) + 1;

    int goatDoor;
    do {
      goatDoor = random.nextInt(3) + 1;
    } while (goatDoor == carDoor || goatDoor == playerChoice);

    boolean switchChoice = random.nextBoolean();

    if (switchChoice) {
      do {
        playerChoice = random.nextInt(3) + 1;
      } while (playerChoice == goatDoor);
    }

    // Сохранение хода игры
    List<Integer> gameMoves = new ArrayList<>();
    gameMoves.add(playerChoice);
    gameMoves.add(goatDoor);
    gameMoves.add(switchChoice ? playerChoice : 0); // Если произошло изменение выбора, добавляем новый выбор
    moves.put(gameNumber, gameMoves);

    return playerChoice == carDoor;
  }
}
