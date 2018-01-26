package com.ka.kalah.core.exception;

/**
 * Description: Invalid game move exception
 * Project: kalah-core
 * Package: com.ka.kalah.core.exception
 * Author: kakyurek
 * Date: 2018.01.26
 */
public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException(String message) {
        super(message);
    }

}