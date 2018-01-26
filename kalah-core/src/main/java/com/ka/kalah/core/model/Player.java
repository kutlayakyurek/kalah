package com.ka.kalah.core.model;

import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * Description: Kalah game player model
 * Project: kalah-core
 * Package: com.backbase.kalah.core.model
 * Author: kakyurek
 * Date: 2018.01.25
 */
public class Player {

    private Integer id;
    private String name;
    private LinkedHashMap<Integer, Integer> pits;
    private int kalah = 0;

    Player() {
    }

    public Player(Integer id) {
        this.id = id;
    }

    public Player(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public LinkedHashMap<Integer, Integer> getPits() {
        if (pits == null) {
            pits = new LinkedHashMap<>();
        }
        return pits;
    }

    public int getKalah() {
        return kalah;
    }

    public void setKalah(int kalah) {
        this.kalah = kalah;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pits=" + pits +
                ", kalah=" + kalah +
                '}';
    }

}
