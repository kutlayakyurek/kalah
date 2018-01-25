package com.backbase.kalah.core.model;

/**
 * Description: Kalah game model
 * Project: kalah-core
 * Package: com.backbase.kalah.core.game
 * Author: kakyurek
 * Date: 2018.01.25
 */
public class Game {

    private Player playerOne;
    private Player playerTwo;

    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

}