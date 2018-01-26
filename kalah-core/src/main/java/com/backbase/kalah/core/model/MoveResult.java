package com.backbase.kalah.core.model;

/**
 * Description: Indicates the result of the players move
 * Project: kalah-core
 * Package: com.backbase.kalah.core.model
 * Modified By: kakyurek
 * Modification Date: 26.01.2018
 */
public class MoveResult {

    private int lastPitIndex;
    private int stonesToMove;

    public MoveResult(int lastPitIndex, int stonesToMove) {
        this.lastPitIndex = lastPitIndex;
        this.stonesToMove = stonesToMove;
    }

    public int getLastPitIndex() {
        return lastPitIndex;
    }

    public int getStonesToMove() {
        return stonesToMove;
    }

}
