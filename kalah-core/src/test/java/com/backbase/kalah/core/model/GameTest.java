package com.backbase.kalah.core.model;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

/**
 * Description: Game model test suite
 * Project: kalah-core
 * Package: com.backbase.kalah.core.model
 * Author: kakyurek
 * Date: 2018.01.25
 */
public class GameTest {

    @Test
    public void shouldGameInitializeCorrectly() {
        Game game = new Game(new Player(1), new Player(2));
        assertNotNull(game.getPlayerOne());
        assertNotNull(game.getPlayerTwo());
    }

}
