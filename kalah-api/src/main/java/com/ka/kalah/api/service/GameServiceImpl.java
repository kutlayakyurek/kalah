package com.ka.kalah.api.service;

import com.ka.kalah.api.model.persistence.GameModel;
import com.ka.kalah.api.repository.GameRepository;
import com.ka.kalah.core.exception.InvalidMoveException;
import com.ka.kalah.core.exception.KalahGameException;
import com.ka.kalah.core.game.KalahGame;
import com.ka.kalah.core.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

/**
 * Description: Kalah game service
 * Project: kalah-api
 * Package: com.ka.kalah.api.service
 * Author: kakyurek
 * Date: 2018.01.27
 */
@Service
public class GameServiceImpl implements GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);
    private static final int ID_BOUND = 65535;
    private static final Random random = new Random();

    private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * Returns requested game
     *
     * @param id Token of requested game
     * @return Found game
     */
    @Override
    public GameModel getStatus(String id) {
        return searchGame(id);
    }

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
    @Override
    public GameModel createGame(String firstPlayerName, String secondPlayerName, int pits, int stones) throws KalahGameException {
        // Create a new game
        Player firstPlayer = new Player(random.nextInt(ID_BOUND), firstPlayerName);
        Player secondPlayer = new Player(random.nextInt(ID_BOUND), secondPlayerName);
        KalahGame newGame = new KalahGame();
        newGame.init(firstPlayer, secondPlayer, stones, pits);

        // Persist new game in database
        GameModel gameModel = new GameModel();
        gameModel.setKalahGame(newGame);
        String id = UUID.randomUUID().toString();
        gameModel.setId(id);
        gameRepository.save(gameModel);
        logger.info("New game saved into database: " + id);

        return gameModel;
    }

    /**
     * Player movement in game with selected pit to move from
     *
     * @param id          Game id to play with
     * @param selectedPit Which pit player moves through
     * @return Game status
     * @throws InvalidMoveException thrown if move is out of bounds
     */
    @Override
    public GameModel move(String id, int selectedPit) throws InvalidMoveException {
        GameModel gameModel = searchGame(id);

        // If game is found, move the player
        if (gameModel != null) {
            KalahGame game = gameModel.getKalahGame();
            boolean isFinished = game.isFinished();

            // If game is finished already, just return current game status
            if (isFinished) {
                logger.info("Game is finished: " + id);
                return gameModel;
            } else {
                // If game continues, move the player and save the status of the game into database
                game.move(selectedPit);
                gameRepository.save(gameModel);
                logger.info("Player moved with selected pit: " + selectedPit);
            }

        }

        return gameModel;
    }

    /**
     * Utility method to find a game from database
     *
     * @param id Game id to be searched
     * @return Found game
     */
    private GameModel searchGame(String id) {
        GameModel gameModel = null;

        // Check for requested game
        if (id != null) {
            // Check database and return the game if there is one
            gameModel = gameRepository.findById(id);
        }
        return gameModel;
    }

}
