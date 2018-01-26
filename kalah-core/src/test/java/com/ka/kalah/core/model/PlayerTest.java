package com.ka.kalah.core.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

/**
 * Description: Player model test suite
 * Project: kalah-core
 * Package: com.backbase.kalah.core.model
 * Author: kakyurek
 * Date: 2018.01.25
 */
public class PlayerTest {

    private Player playerOne;
    private Player playerTwo;

    @Before
    public void beforeTest() {
        playerOne = new Player();
        playerTwo = new Player();
    }

    @Test
    public void shouldSamePlayersEqual() {
        playerOne.setId(1);
        playerTwo.setId(1);
        assertTrue(playerOne.equals(playerTwo));
    }

    @Test
    public void shouldSamePlayersHashesEqual() {
        playerOne.setId(1);
        playerTwo.setId(1);
        assertEquals(playerOne.hashCode(), playerTwo.hashCode());
    }

    @Test
    public void shouldDifferentPlayersNotEqual() {
        playerOne.setId(1);
        playerTwo.setId(2);
        assertNotEquals(playerOne, playerTwo);
    }

    @Test
    public void shouldDifferentPlayersHashesNotEqual() {
        playerOne.setId(1);
        playerTwo.setId(2);
        assertNotEquals(playerOne.hashCode(), playerTwo.hashCode());
    }

    @After
    public void afterTest() {
        playerOne = null;
        playerTwo = null;
    }

}
