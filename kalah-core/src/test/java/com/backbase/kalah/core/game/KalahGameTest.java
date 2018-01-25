package com.backbase.kalah.core.game;

import com.backbase.kalah.core.exception.KalahGameException;
import com.backbase.kalah.core.model.Player;
import org.junit.Before;
import org.junit.Test;

/**
 * Description: Kalah game test suite
 * Project: kalah-core
 * Package: com.backbase.kalah.core.game
 * Author: kakyurek
 * Date: 2018.01.25
 */
public class KalahGameTest {

    private KalahGame kalahGame;

    @Before
    public void beforeTest() {
        kalahGame = new KalahGame();
    }

    @Test
    public void shouldInitGame() {
        kalahGame.init(new Player(1), new Player(2), 6, 6);
    }

    @Test(expected = KalahGameException.class)
    public void shouldNullPlayerOneNotAllowedWhenInit() {
        kalahGame.init(null, new Player(2), 6, 6);
    }

    @Test(expected = KalahGameException.class)
    public void shouldNullPlayerTwoNotAllowedWhenInit() {
        kalahGame.init(new Player(1), null, 6, 6);
    }

    @Test(expected = KalahGameException.class)
    public void shouldSamePlayersNotAllowedWhenInit() {
        kalahGame.init(new Player(1), new Player(1), 6, 6);
    }

    @Test(expected = KalahGameException.class)
    public void shouldNotAllowLessThanOneStoneCount() {
        kalahGame.init(new Player(1), new Player(2), 0, 6);
    }

}
