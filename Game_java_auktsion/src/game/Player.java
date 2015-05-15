package game;

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
    private UUID guid;
    private User user;

    public Player(User user, String name)
    {
        this.user = user;
        this.name = name;
    }

    public boolean canBy(Tovar currentTovar)
    {
        if (cash > currentTovar.getCost())
        {
            return true;
        }
        return false;
    }

    public boolean by(Tovar currentTovar)
    {
        if(!canBy(currentTovar)) return false;
        cash -= currentTovar.getCost();
        if (tovarList == null)
        {
            tovarList = new ArrayList<Tovar>();
        }
        tovarList.add(currentTovar);
        return true;
    }

    public UUID getGuid()
    {
        return guid;
    }
}
