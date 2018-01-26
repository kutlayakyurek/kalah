package com.ka.kalah.core.game;

import com.ka.kalah.core.exception.KalahGameException;
import com.ka.kalah.core.model.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Description: Kalah game test suite
 * Project: kalah-core
 * Package: com.ka.kalah.core.game
 * Author: kakyurek
 * Date: 2018.01.25
 */
public class KalahGameTest {

    private KalahGame kalahGame;
    private static final int PITS = 6;
    private static final int STONES = 6;
    private static final int ID_FIRST_PLAYER = 1;
    private static final int ID_SECOND_PLAYER = 2;

    @Before
    public void beforeTest() {
        kalahGame = new KalahGame();
    }

    @Test
    public void shouldInitGame() {
        kalahGame.init(new Player(ID_FIRST_PLAYER), new Player(ID_SECOND_PLAYER), STONES, PITS);
    }

    @Test(expected = KalahGameException.class)
    public void shouldNullPlayerOneNotAllowedWhenInit() {
        kalahGame.init(null, new Player(ID_SECOND_PLAYER), STONES, PITS);
    }

    @Test(expected = KalahGameException.class)
    public void shouldNullPlayerTwoNotAllowedWhenInit() {
        kalahGame.init(new Player(ID_FIRST_PLAYER), null, STONES, PITS);
    }

    @Test(expected = KalahGameException.class)
    public void shouldSamePlayersNotAllowedWhenInit() {
        kalahGame.init(new Player(ID_FIRST_PLAYER), new Player(ID_FIRST_PLAYER), STONES, PITS);
    }

    @Test(expected = KalahGameException.class)
    public void shouldNotAllowLessThanOneStoneCount() {
        kalahGame.init(new Player(ID_FIRST_PLAYER), new Player(ID_SECOND_PLAYER), 0, PITS);
    }

    @Test
    public void shouldGameFinish() {
        Player firstPlayer = new Player(ID_FIRST_PLAYER);
        Player secondPlayer = new Player(ID_SECOND_PLAYER);
        kalahGame.init(firstPlayer, secondPlayer, STONES, PITS);
        LinkedHashMap<Integer, Integer> firstPlayerPits = firstPlayer.getPits();

        for (int i = 1; i <= PITS; i++) {
            firstPlayerPits.put(i, 0);
        }

        assertTrue(kalahGame.isFinished());
    }

    @Test
    public void shouldGameNotBeFinish() {
        kalahGame.init(new Player(ID_FIRST_PLAYER), new Player(ID_SECOND_PLAYER), STONES, PITS);
        assertFalse(kalahGame.isFinished());
    }

    @After
    public void afterTest() {
        this.kalahGame = null;
    }

}
