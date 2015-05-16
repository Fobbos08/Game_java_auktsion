package game;

import business.DBConnector;
import business.SessionManipulator;
import models.Goods;
import models.User;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;

public final class GameManipulator {
    private static int  timerDelay = 1000;
    private static Timer timer;
    private static ArrayList<Game> games;

    public static void create()
    {
        if (timer == null) {
            games = new ArrayList<Game>();
            //timer = new Timer(true);
            //timer.schedule(new TimerTick(), timerDelay);
            //timer.scheduleAtFixedRate(new TimerTick(), 1, timerDelay);


            ActionListener taskPerformer = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    gameLoop();
                }
            };

            timer = new Timer(timerDelay, taskPerformer);
            timer.start();
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

    public static Player getPlayer(UUID gameId, UUID playerId)
    {
        for(Game game: games)
        {
            if(game.getId() == gameId)
            {
                return game.getPlayer(playerId);
            }
        }
        return null;
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
