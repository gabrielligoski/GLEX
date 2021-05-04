package com.gdx.glex.Menu.Rankings;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.sql.Date;
import java.sql.Time;

public class Player {
    private String name;
    private Date lastGame;
    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.lastGame = new Date(0);
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastGame() {
        return lastGame;
    }

    public void setLastGame(Date lastGame) {
        this.lastGame = lastGame;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
