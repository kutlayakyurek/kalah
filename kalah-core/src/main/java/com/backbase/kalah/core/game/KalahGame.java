package com.backbase.kalah.core.game;

import com.backbase.kalah.core.exception.KalahGameException;
import com.backbase.kalah.core.model.Game;
import com.backbase.kalah.core.model.Player;

import java.util.Map;

/**
 * Description: Kalah game engine
 * Project: kalah-core
 * Package: com.backbase.kalah.core.game
 * Author: kakyurek
 * Date: 2018.01.25
 */
public class KalahGame {

    private static final int MIN_PIT_COUNT = 4;
    private static final int MIN_STONE_COUNT = 1;
    private Game game;
    private Player currentTurn;
    private int stones;
    private int pits;

    /**
     * Initializes the game with two players
     *
     * @param firstPlayer  First player
     * @param secondPlayer Second player
     * @throws KalahGameException thrown if game is invalid
     */
    public void init(Player firstPlayer, Player secondPlayer, int stones, int pits) throws KalahGameException {
        // Validating the game
        if (firstPlayer == null || secondPlayer == null) {
            throw new KalahGameException("Players can not be null");
        } else if (firstPlayer.equals(secondPlayer)) {
            throw new KalahGameException("Identical players can not play");
        } else if (stones < MIN_STONE_COUNT) {
            throw new KalahGameException("Pits must have at least " + MIN_STONE_COUNT + " stone for valid game");
        } else if (pits < MIN_PIT_COUNT) {
            throw new KalahGameException("At least " + MIN_PIT_COUNT + "pits should be used in game");
        }

        // Initializing game settings
        this.stones = stones;
        this.pits = pits;
        this.game = new Game(firstPlayer, secondPlayer);
        this.currentTurn = firstPlayer;

        Map<Integer, Integer> firstPlayerPits = firstPlayer.getPits();
        Map<Integer, Integer> secondPlayerPits = secondPlayer.getPits();

        for (int i = 1; i <= pits; i++) {
            firstPlayerPits.put(i, stones);
            secondPlayerPits.put(i, stones);
        }
    }

}