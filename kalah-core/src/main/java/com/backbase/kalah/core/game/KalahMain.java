package com.backbase.kalah.core.game;

import com.backbase.kalah.core.exception.InvalidMoveException;
import com.backbase.kalah.core.model.Player;

import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Description: Main method implementation to test the application on console
 * Project: kalah-core
 * Package: com.backbase.kalah.core.game
 * Modified By: kakyurek
 * Modification Date: 26.01.2018
 */
final class KalahMain {

    private static final int PITS = 6;
    private static final int STONES = 6;
    private static final String FIRST_PLAYER = "Ahmet";
    private static final String SECOND_PLAYER = "Kutlay";

    public static void main(String[] args) {
        KalahGame game = new KalahGame();
        Player firstPlayer = new Player(1, FIRST_PLAYER);
        Player secondPlayer = new Player(2, SECOND_PLAYER);
        game.init(firstPlayer, secondPlayer, STONES, PITS);
        int input;
        int kalahIndex = PITS + 1;
        boolean gameFinished = false;
        Scanner sc = new Scanner(System.in);

        while (!gameFinished) {
            System.out.println(game.getCurrentPlayerName() + " please enter pit(0 For Exit): ");
            input = sc.nextInt();
            if (input == 0) {
                System.out.println("You are exiting the game");
                break;
            }
            try {
                gameFinished = game.move(input);
                printBoard(firstPlayer, secondPlayer);
            } catch (InvalidMoveException e) {
                System.out.println(e.getMessage());
            }
        }

        Player winner = firstPlayer.getPits().get(kalahIndex) > secondPlayer.getPits().get(kalahIndex) ? firstPlayer : secondPlayer;
        System.out.println("Winner of the game: " + winner);
    }

    private static void printBoard(Player firstPlayer, Player secondPlayer) {
        LinkedHashMap<Integer, Integer> firstPlayerPits = firstPlayer.getPits();
        LinkedHashMap<Integer, Integer> secondPlayerPits = secondPlayer.getPits();
        String firstPlayerName = firstPlayer.getName();
        String secondPlayerName = secondPlayer.getName();
        int kalahIndex = PITS + 1;

        for (int i = PITS; i >= 1; i--) {
            System.out.print(secondPlayerPits.get(i) + " ");
        }

        System.out.println(secondPlayerName + ": " + secondPlayer.getPits().get(kalahIndex));

        for (int i = 1; i <= PITS; i++) {
            System.out.print(firstPlayerPits.get(i) + " ");
        }

        System.out.println(firstPlayerName + ": " + firstPlayer.getPits().get(kalahIndex));
    }

}
