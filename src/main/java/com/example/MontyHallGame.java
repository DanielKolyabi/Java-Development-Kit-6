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
      System.out.println("Ходы игры " + i + ": " + moves.get(i));
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
