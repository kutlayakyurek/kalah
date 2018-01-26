package com.backbase.kalah.core.game;

import com.backbase.kalah.core.exception.InvalidMoveException;
import com.backbase.kalah.core.exception.KalahGameException;
import com.backbase.kalah.core.model.Game;
import com.backbase.kalah.core.model.MoveResult;
import com.backbase.kalah.core.model.Player;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
    private int pits;
    private int kalahIndex;
    private Player currentPlayer;

    /**
     * Initializes the game with two players as well as stone and pit amounts
     *
     * @param firstPlayer  First player
     * @param secondPlayer Second player
     * @param stones       Stone count in the game
     * @param pits         Pits count of each player
     * @throws KalahGameException thrown if game is invalid
     */
    public void init(Player firstPlayer, Player secondPlayer, int stones, int pits) throws KalahGameException {
        // Validating game setup
        validateGame(firstPlayer, secondPlayer, stones, pits);

        // Setting configurations
        this.pits = pits;
        this.kalahIndex = pits + 1;
        this.game = new Game(firstPlayer, secondPlayer);
        this.currentPlayer = firstPlayer;

        // Initializing player pits
        Map<Integer, Integer> firstPlayerPits = firstPlayer.getPits();
        Map<Integer, Integer> secondPlayerPits = secondPlayer.getPits();

        // Filling stones into pits
        for (int i = 1; i <= pits; i++) {
            firstPlayerPits.put(i, stones);
            secondPlayerPits.put(i, stones);
        }

        // Setting kalah to zero for both players
        firstPlayerPits.put(kalahIndex, 0);
        secondPlayerPits.put(kalahIndex, 0);
    }

    /**
     * Next player moves with his pit selection with turn based game rules
     *
     * @param selectedPit Selected pit to play
     * @return Game status
     * @throws InvalidMoveException thrown if move is invalid
     */
    public boolean move(int selectedPit) throws InvalidMoveException {
        // Validate move before actually player moves
        validateMove(selectedPit, currentPlayer);

        // Sow the stone on to the right
        LinkedHashMap<Integer, Integer> map = currentPlayer.getPits();
        int stonesToMove = map.get(selectedPit);

        // Move to the right until the end
        MoveResult moveResult = moveStones(stonesToMove, selectedPit, map, false);
        int lastPitIndex = moveResult.getLastPitIndex();
        stonesToMove = moveResult.getStonesToMove();
        Player otherPlayer = getOtherPlayer();
        currentPlayer = otherPlayer;

        // There is still stone to move so continue on players kalah and then opponents pits but not opponents kalah.
        // If last pit is current players pit and its empty, grab opponents stones inside opposite pit and add to current players kalah
        // If last pit plus one is equals to current players kalah, current player continues to play
        if (stonesToMove == 0 && lastPitIndex == kalahIndex) {
            currentPlayer = getOtherPlayer();
        } else if (stonesToMove > 0) {
            currentPlayer.setKalah(currentPlayer.getPits().get(kalahIndex));
            map = otherPlayer.getPits();
            moveStones(stonesToMove, 0, map, true);
        } else if (map.get(moveResult.getLastPitIndex()) == 0) {
            int opponentPitIndex = pits - lastPitIndex + 1;
            currentPlayer.setKalah(currentPlayer.getKalah() + otherPlayer.getPits().get(opponentPitIndex));
        }

        // Check if game is finished
        boolean gameFinished = isFinished();

        // Calculate remaining stones if game is finished
        if (gameFinished) {
            calculateScores();
        }

        return gameFinished;
    }

    /**
     * Calculates stones left and make scores ready when the game is finished
     */
    private void calculateScores() {
        Player firstPlayer = game.getFirstPlayer();
        Player secondPlayer = game.getSecondPlayer();
        LinkedHashMap<Integer, Integer> firstPlayerPits = firstPlayer.getPits();
        LinkedHashMap<Integer, Integer> secondPlayerPits = secondPlayer.getPits();

        for (int i = 1; i <= pits; i++) {
            firstPlayer.setKalah(firstPlayer.getKalah() + firstPlayerPits.get(i));
            secondPlayer.setKalah(secondPlayer.getKalah() + secondPlayerPits.get(i));
        }
    }

    /**
     * Moves stones over players pits
     *
     * @param stonesToMove    Stones left to move
     * @param currentPitIndex Current pit index to continue
     * @param map             Players pits
     * @param oppositeMove    Moving on other players pits
     */
    private MoveResult moveStones(int stonesToMove, int currentPitIndex, LinkedHashMap<Integer, Integer> map, boolean oppositeMove) {
        Set<Map.Entry<Integer, Integer>> set = map.entrySet();
        Iterator<Map.Entry<Integer, Integer>> i = set.iterator();
        Map.Entry<Integer, Integer> pit = null;
        int lastPitIndex = 0;

        // Iterate to selected pit
        while (currentPitIndex > 0) {
            pit = i.next();
            currentPitIndex--;
        }

        // Grab stones from selected pit
        if (pit != null) {
            pit.setValue(0);
        }

        // Sow stones to the right
        while (stonesToMove > 0 && i.hasNext()) {
            pit = i.next();

            // Skip opponents kalah
            if (oppositeMove && kalahIndex == pit.getKey()) {
                continue;
            }

            pit.setValue(pit.getValue() + 1);
            stonesToMove--;
            lastPitIndex = pit.getKey();
        }

        return new MoveResult(lastPitIndex, stonesToMove);
    }

    /**
     * Checks if game is finished on each move
     *
     * @return Game status
     */
    boolean isFinished() {
        Player firstPlayer = game.getFirstPlayer();
        Player secondPlayer = game.getSecondPlayer();
        LinkedHashMap<Integer, Integer> firstPlayerPits = firstPlayer.getPits();
        LinkedHashMap<Integer, Integer> secondPlayerPits = secondPlayer.getPits();
        int firstPlayerStones = 0;
        int secondPlayerStones = 0;

        // Calculating stones left in pits of first player
        for (int i = 1; i <= pits; i++) {
            firstPlayerStones += firstPlayerPits.get(i);
        }

        // Calculating stones left in pits of second player
        for (int i = 1; i <= pits; i++) {
            secondPlayerStones += secondPlayerPits.get(i);
        }

        // If either of the amounts zero then the game is finished
        return firstPlayerStones == 0 || secondPlayerStones == 0;
    }

    /**
     * Returns other player
     */
    private Player getOtherPlayer() {
        if (currentPlayer.equals(game.getFirstPlayer())) {
            return game.getSecondPlayer();
        }
        return game.getFirstPlayer();
    }

    /**
     * Validates each move. Selected pit must be between ranges.
     * Also checks for current player
     *
     * @param selectedPit   Selected pit by the user to move from
     * @param currentPlayer Player currently moves
     */
    private void validateMove(int selectedPit, Player currentPlayer) {
        if (selectedPit > pits || selectedPit < 1) {
            throw new KalahGameException("Selected pit out of bounds");
        } else if (currentPlayer == null) {
            throw new KalahGameException("Current player can not be null");
        } else if (currentPlayer.getPits().get(selectedPit) == 0) {
            throw new InvalidMoveException("Invalid pit. Pit is empty");
        }
    }

    /**
     * Validates new game. Players of the game must be set.
     * Stone and pit counts must be bigger than minimums
     *
     * @param firstPlayer  First player
     * @param secondPlayer Second player
     * @param stones       Stone count in the game
     * @param pits         Pits count of each player
     */
    private void validateGame(Player firstPlayer, Player secondPlayer, int stones, int pits) {
        if (firstPlayer == null || secondPlayer == null) {
            throw new KalahGameException("Players can not be null");
        } else if (firstPlayer.equals(secondPlayer)) {
            throw new KalahGameException("Identical players can not play");
        } else if (stones < MIN_STONE_COUNT) {
            throw new KalahGameException("Pits must have at least " + MIN_STONE_COUNT + " stone for valid game");
        } else if (pits < MIN_PIT_COUNT) {
            throw new KalahGameException("At least " + MIN_PIT_COUNT + "pits should be used in game");
        }
    }

    /**
     * Returns the players name currently moves on
     *
     * @return Name of the player
     */
    public String getCurrentPlayerName() {
        return currentPlayer.getName();
    }

}
