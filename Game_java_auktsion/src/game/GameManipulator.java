package game;

import business.DBConnector;
import business.SessionManipulator;
import models.Goods;
import models.User;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

public final class GameManipulator {
    private static int  timerDelay = 1000;
    private static Timer timer;
    private static ArrayList<Game> games;

    public static void create()
    {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTick(), timerDelay);
            games = new ArrayList<Game>();
        }
    }

    public static UUID createGame(UUID id)
    {
        create();
        Game g = new Game(7);
        Goods g1 =  DBConnector.getGoods(1);
        Goods g2 =  DBConnector.getGoods(2);
        Tovar t = new Tovar(g1, 100 );
        Tovar t2 = new Tovar(g2, 100);
        g.addTovar(t);
        g.addTovar(t2);
        games.add(g);
        return g.getId();
    }

    public static Tovar getTovar(UUID userId, UUID gameId)
    {
        for(int i=0; i<games.size(); i++)
        {
            if (games.get(i).getId().equals(gameId))
            {
                return games.get(i).getCurrentTovar();
            }
        }
        return null;
    }

    public static boolean startGame(UUID gameId)
    {
        for(int i=0; i<games.size(); i++)
        {
            if (games.get(i).getId().equals(gameId))
            {
                games.get(i).startGame();
                return true;
            }
        }
        return false;
    }

    public static boolean addUserToGame(UUID gameId, UUID userId)
    {
        for(int i=0; i<games.size(); i++)
        {
            if (games.get(i).getId().equals(gameId))
            {
                User user = SessionManipulator.getUser(userId);
                String login = DBConnector.getLogin(user.getId());
                Player p = new Player(SessionManipulator.getUser(userId), login);
                games.get(i).addPlayer(p);
                return true;
            }
        }
        return false;
    }

    public static void gameLoop()
    {
        for(int i=0; i<games.size(); i++)
        {
            games.get(i).timerTick(timerDelay);
        }
    }


}

class TimerTick extends TimerTask {

    @Override
    public void run() {
        GameManipulator.gameLoop();
    }
}
