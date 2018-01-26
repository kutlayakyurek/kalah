package com.backbase.kalah.core.exception;

/**
 * Description: Invalid game move exception
 * Project: kalah-core
 * Package: com.backbase.kalah.core.exception
 * Author: kakyurek
 * Date: 2018.01.26
 */
public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException(String message) {
        super(message);
    }

}