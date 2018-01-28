package com.ka.kalah.api.model.com;

import com.ka.kalah.core.model.Game;
import lombok.Getter;
import lombok.Setter;

/**
 * Description: Game response model
 * Project: kalah-api
 * Package: com.ka.kalah.api.model.com
 * Author: kakyurek
 * Date: 2018.01.27
 */
@Getter
@Setter
public class GameResponse {

    private String id;
    private Game game;

    @Override
    public String toString() {
        return "GameResponse{" +
                "id='" + id + '\'' +
                ", game=" + game +
                '}';
    }

}
