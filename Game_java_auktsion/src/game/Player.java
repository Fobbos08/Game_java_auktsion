package game;

import business.DBConnector;
import game.bonuses.IBuyHelper;
import models.User;

import java.util.ArrayList;
import java.util.UUID;

public class Player {
    private int cash;
    private int score;
    private ArrayList<Tovar> tovarList;
    private User user;
    private IBuyHelper buyHelper;

    public Player(User user, int startCash) {
        this.user = user;
        this.cash = startCash;
    }

    public boolean canBy(int cost) {
        if (cash > cost) {
            return true;
        }
        return false;
    }

    public boolean buyBonus(IBuyHelper buyHelper)
    {
        if (!canBy(buyHelper.getCost())) return false;
        this.buyHelper = buyHelper;
        cash -= buyHelper.getCost();
        return true;
    }

    public boolean by(Tovar currentTovar) {
        int tovarCost = currentTovar.getCurrentCost();

        if(buyHelper != null)
        {
            tovarCost = buyHelper.getNewCost(tovarCost);
            buyHelper = null;
        }

        if (!canBy(tovarCost)) return false;

        cash -= tovarCost;
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
        return user.getName();
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

    public int getId()
    {
        return user.getId();
    }
}
