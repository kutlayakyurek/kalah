package com.ka.kalah.core.model;

/**
 * Description: Kalah game model
 * Project: kalah-core
 * Package: com.ka.kalah.core.game
 * Author: kakyurek
 * Date: 2018.01.25
 */
public class Game {

    private Player firstPlayer;
    private Player secondPlayer;

    public Game(Player playerOne, Player playerTwo) {
        this.firstPlayer = playerOne;
        this.secondPlayer = playerTwo;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

}
