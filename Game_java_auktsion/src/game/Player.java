package game;

import business.DBConnector;
import models.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Эмиль on 02.05.2015.
 */
public class Player {
    private int cash;
    private int score;
    private ArrayList<Tovar> tovarList;/////!!!!!!!!
    private User user;

    public Player(User user, int startCash) {
        this.user = user;
        this.cash = startCash;
    }

    public boolean canBy(Tovar currentTovar) {
        if (cash > currentTovar.getCurrentCost()) {
            return true;
        }
        return false;
    }

    public boolean by(Tovar currentTovar) {
        if (!canBy(currentTovar)) return false;
        cash -= currentTovar.getCurrentCost();
        if (tovarList == null) {
            tovarList = new ArrayList<Tovar>();
        }
        tovarList.add(currentTovar);
        return true;
    }

    public UUID getGuid() {
        return user.getGUID();
    }

    public String getSessionId()
    {
        return user.getSessionId();
    }

    public String getName()
    {
        return DBConnector.getLogin(user.getId());
    }

    public int getCash()
    {
        return cash;
    }

    public ArrayList<Tovar> getTovars()
    {
        if (tovarList == null) {
            tovarList = new ArrayList<Tovar>();
        }
        return tovarList;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
