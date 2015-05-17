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
    private String name;
    private String score;
    private ArrayList<Tovar> tovarList;/////!!!!!!!!
    private User user;

    public Player(User user, String name) {
        this.user = user;
        this.name = name;
        this.cash = 100;
    }

    public boolean canBy(Tovar currentTovar) {
        if (cash > currentTovar.getCurrentCost()) {
            return true;
        }
        return false;
    }

    public boolean by(Tovar currentTovar) {
        if (!canBy(currentTovar)) return false;
        cash -= currentTovar.getCost();
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
}
