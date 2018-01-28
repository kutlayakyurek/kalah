package com.ka.kalah.api.model.com;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: Status request model
 * Project: kalah-api
 * Package: com.ka.kalah.api.model.com
 * Author: kakyurek
 * Date: 2018.01.27
 */
@Getter
@Setter
public class StatusRequest {

    private String id;

    @Override
    public String toString() {
        return "StatusRequest{" +
                "id='" + id + '\'' +
                '}';
    }

}