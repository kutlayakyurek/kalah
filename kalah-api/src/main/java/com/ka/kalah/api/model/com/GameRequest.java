package com.ka.kalah.api.model.com;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: Game request model
 * Project: kalah-api
 * Package: com.ka.kalah.api.model.com
 * Author: kakyurek
 * Date: 2018.01.27
 */
@Getter
@Setter
public class GameRequest {

    private String firstPlayer;
    private String secondPlayer;
    private Integer pits;
    private Integer stones;

    @Override
    public String toString() {
        return "GameRequest{" +
                "firstPlayer='" + firstPlayer + '\'' +
                ", secondPlayer='" + secondPlayer + '\'' +
                ", pits=" + pits +
                ", stones=" + stones +
                '}';
    }

}