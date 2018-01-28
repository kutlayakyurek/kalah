package com.ka.kalah.api.repository;

import com.ka.kalah.api.model.persistence.GameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description: GameModel repository
 * Project: kalah-api
 * Package: com.ka.kalah.api.repository
 * Author: kakyurek
 * Date: 2018.01.27
 */
@Repository
public interface GameRepository extends JpaRepository<GameModel, Long> {

    /**
     * Returns game by id
     *
     * @param id Game id
     * @return Found game
     */
    GameModel findById(String id);

}