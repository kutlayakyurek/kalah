package com.ka.kalah.core.util;

import com.ka.kalah.core.model.Player;

import java.util.LinkedHashMap;

/**
 * Description: Prints the game board
 * Project: kalah-core
 * Package: com.ka.kalah.core.util
 * Author: kakyurek
 * Date: 2018.01.27
 */
public class PrintBoard {

    private static final PrintBoard instance = new PrintBoard();

    private PrintBoard() {
    }

    public static PrintBoard getInstance() {
        return instance;
    }

    public String printBoard(Player firstPlayer, Player secondPlayer, int pits) {
        LinkedHashMap<Integer, Integer> firstPlayerPits = firstPlayer.getPits();
        LinkedHashMap<Integer, Integer> secondPlayerPits = secondPlayer.getPits();
        String firstPlayerName = firstPlayer.getName();
        String secondPlayerName = secondPlayer.getName();
        int kalahIndex = pits + 1;
        StringBuilder board = new StringBuilder();

        // Preparing second player to the top of the screen

        for (int i = pits; i >= 1; i--) {
            board.append(secondPlayerPits.get(i));
            board.append(" ");
        }

        board.append(secondPlayerName);
        board.append(": ");
        board.append(secondPlayer.getKalah());
        board.append("\n");

        // Preparing first player to the bottom of the screen
        for (int i = 1; i <= pits; i++) {
            board.append(firstPlayerPits.get(i));
            board.append(" ");
        }

        board.append(firstPlayerName);
        board.append(": ");
        board.append(firstPlayer.getKalah());

        return board.toString();
    }

}