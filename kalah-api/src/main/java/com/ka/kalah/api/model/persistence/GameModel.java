package com.ka.kalah.api.model.persistence;

import com.ka.kalah.core.game.KalahGame;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Description: Game persistence model
 * Project: kalah-api
 * Package: com.ka.kalah.api.model.persistence
 * Author: kakyurek
 * Date: 2018.01.27
 */
@Getter
@Setter
@Entity
@Table(name = "game")
public class GameModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "game_id")
    private Long gameId;

    private String id;

    @Lob
    @Column(name = "kalah_game")
    private KalahGame kalahGame;

    @Override
    public String toString() {
        return "GameModel{" +
                "id='" + id + '\'' +
                '}';
    }

}