package com.ka.kalah.api.model.com;

import com.ka.kalah.core.model.Game;
import lombok.Getter;
import lombok.Setter;

/**
 * Description: Status response model
 * Project: kalah-api
 * Package: com.ka.kalah.api.model.com
 * Author: kakyurek
 * Date: 2018.01.27
 */
@Getter
@Setter
public class StatusResponse {

    private Game game;
    private String message;

    @Override
    public String toString() {
        return "StatusResponse{" +
                "game=" + game +
                ", message='" + message + '\'' +
                '}';
    }

}