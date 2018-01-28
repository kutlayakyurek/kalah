package com.ka.kalah.api.service;

import com.ka.kalah.api.model.persistence.GameModel;
import com.ka.kalah.core.exception.InvalidMoveException;
import com.ka.kalah.core.exception.KalahGameException;

/**
 * Description: Kalah game service interface
 * Project: kalah-api
 * Package: com.ka.kalah.api.service
 * Author: kakyurek
 * Date: 2018.01.27
 */
public interface GameService {

    /**
     * Returns requested game
     *
     * @param id Id of requested game
     * @return Found game
     */
    GameModel getStatus(String id);

    /**
     * Initializes the Kalah GameModel with player details
     *
     * @param firstPlayerName  First players name
     * @param secondPlayerName Second players name
     * @param pits             How many pits will be on board
     * @param stones           How many stones will reside on the pits
     * @return Created game
     * @throws KalahGameException thrown if game settings are invalid
     */
    GameModel createGame(String firstPlayerName, String secondPlayerName, int pits, int stones) throws KalahGameException;

    /**
     * Player movement in game with selected pit to move from
     *
     * @param id          Game id to play with
     * @param selectedPit Which pit player moves through
     * @return Game status
     * @throws InvalidMoveException thrown if move is out of bounds
     */
    GameModel move(String id, int selectedPit) throws InvalidMoveException;

}
