package com.ka.kalah.core.game;

import com.ka.kalah.core.exception.InvalidMoveException;
import com.ka.kalah.core.model.Player;
import com.ka.kalah.core.util.PrintBoard;

import java.util.Scanner;

/**
 * Description: Main method implementation to test the application on console
 * Project: kalah-core
 * Package: com.ka.kalah.core.game
 * Modified By: kakyurek
 * Modification Date: 26.01.2018
 */
final class KalahMain {

    private static final int PITS = 6;
    private static final int STONES = 6;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the first player name: ");
        String firstPlayerName = sc.next();
        System.out.println("Please enter the second player name: ");
        String secondPlayerName = sc.next();
        KalahGame game = new KalahGame();
        Player firstPlayer = new Player(1, firstPlayerName);
        Player secondPlayer = new Player(2, secondPlayerName);
        game.init(firstPlayer, secondPlayer, STONES, PITS);
        int input;
        boolean gameFinished = false;

        System.out.println("Welcome to the Kalah Game!");
        printBoard(firstPlayer, secondPlayer);

        while (!gameFinished) {
            System.out.println(game.getCurrentPlayer().getName() + " please enter pit(0 For Exit): ");
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

        Player winner = game.getWinner();
        System.out.println("Winner of the game: " + winner);
    }

    private static void printBoard(Player firstPlayer, Player secondPlayer) {
        System.out.println(PrintBoard.getInstance().printBoard(firstPlayer, secondPlayer, PITS));
    }

}
