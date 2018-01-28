package com.ka.kalah.api.model.com;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: Move request model
 * Project: kalah-api
 * Package: com.ka.kalah.api.model.com
 * Author: kakyurek
 * Date: 2018.01.27
 */
@Getter
@Setter
public class MoveRequest {

    private Integer pit;

    @Override
    public String toString() {
        return "MoveRequest{" +
                "pit=" + pit +
                '}';
    }

}