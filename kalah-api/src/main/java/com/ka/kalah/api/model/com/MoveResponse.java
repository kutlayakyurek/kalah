package com.ka.kalah.api.model.com;

import com.ka.kalah.core.model.Game;
import lombok.Getter;
import lombok.Setter;

/**
 * Description: Move response model
 * Project: kalah-api
 * Package: com.ka.kalah.api.model.com
 * Author: kakyurek
 * Date: 2018.01.27
 */
@Getter
@Setter
public class MoveResponse {

    private Game game;
    private String message;

    @Override
    public String toString() {
        return "MoveResponse{" +
                "game=" + game +
                ", message='" + message + '\'' +
                '}';
    }

}