package com.ka.kalah.api.controller;

import com.ka.kalah.api.model.com.*;
import com.ka.kalah.api.model.persistence.GameModel;
import com.ka.kalah.api.service.GameService;
import com.ka.kalah.core.exception.InvalidMoveException;
import com.ka.kalah.core.game.KalahGame;
import com.ka.kalah.core.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Description: Kalah game controller
 * Project: kalah-api
 * Package: com.ka.kalah.api.controller
 * Author: kakyurek
 * Date: 2018.01.27
 */
@RestController
public class GameController {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);
    private static final int PITS_DEFAULT = 6;
    private static final int STONES_DEFAULT = 6;
    private static final String DEFAULT_FIRST_PLAYER = "First Player";
    private static final String DEFAULT_SECOND_PLAYER = "Second Player";

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Creates brand new game
     *
     * @param request Game request
     * @return Created game with its id
     */
    @PostMapping(path = "/games")
    public GameResponse createGame(@RequestBody GameRequest request) {
        logger.info("Create game request: " + request.toString());
        int pits = request.getPits() == null ? PITS_DEFAULT : request.getPits();
        int stones = request.getStones() == null ? STONES_DEFAULT : request.getStones();
        String firstPlayerName = request.getFirstPlayer() == null || request.getFirstPlayer().isEmpty() ? DEFAULT_FIRST_PLAYER : request.getFirstPlayer();
        String secondPlayerName = request.getSecondPlayer() == null || request.getSecondPlayer().isEmpty() ? DEFAULT_SECOND_PLAYER : request.getSecondPlayer();
        GameModel createdGame = gameService.createGame(firstPlayerName, secondPlayerName, pits, stones);
        GameResponse response = new GameResponse();
        response.setGame(createdGame.getKalahGame().getGame());
        response.setId(createdGame.getId());
        logger.info("Game created: " + createdGame.toString());
        return response;
    }

    /**
     * Returns status of the game
     *
     * @param id Game id to search
     * @return Found game
     */
    @GetMapping(path = "/games/{id}")
    public ResponseEntity<StatusResponse> getStatus(@PathVariable String id) {
        logger.info("Status request: " + id);
        StatusResponse response = new StatusResponse();
        GameModel foundGame = gameService.getStatus(id);

        // Unless game is found, return 404
        if (foundGame == null) {
            response.setMessage("Game is not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.setGame(gameService.getStatus(id).getKalahGame().getGame());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Moves the player in game
     *
     * @param moveRequest Game id with pit selection
     * @return Current game status and a message
     */
    @PostMapping(path = "/games/{id}/move")
    public ResponseEntity<MoveResponse> move(@PathVariable String id, @RequestBody MoveRequest moveRequest) {
        logger.info("Move request: " + moveRequest.toString());
        MoveResponse moveResponse = new MoveResponse();

        // Validate move request
        if (moveRequest.getPit() == null) {
            moveResponse.setMessage("Did you forget to send 'pit' parameter?");
            return new ResponseEntity<>(moveResponse, HttpStatus.BAD_REQUEST);
        }

        GameModel currentGame = gameService.getStatus(id);

        // Unless game is found, return 404
        if (currentGame == null) {
            moveResponse.setMessage("Game is not found");
            return new ResponseEntity<>(moveResponse, HttpStatus.NOT_FOUND);
        }

        GameModel gameAfterMove;

        try {
            gameAfterMove = gameService.move(id, moveRequest.getPit());
        } catch (InvalidMoveException e) {
            moveResponse.setMessage("Invalid move. Did you choose empty pit?");
            return new ResponseEntity<>(moveResponse, HttpStatus.BAD_REQUEST);
        }

        KalahGame kalahGameAfterMove = gameAfterMove.getKalahGame();
        moveResponse.setGame(kalahGameAfterMove.getGame());

        // If game is finished just send a winner
        if (kalahGameAfterMove.isFinished()) {
            Player winner = kalahGameAfterMove.getWinner();
            moveResponse.setMessage("Game is finished. Winner is: " + winner.getName());
            return new ResponseEntity<>(moveResponse, HttpStatus.OK);
        }

        moveResponse.setMessage("Next player to move is: " + kalahGameAfterMove.getCurrentPlayer().getName());
        return new ResponseEntity<>(moveResponse, HttpStatus.OK);
    }

}
