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
    private static HashMap<UUID, Game> games;

    public static void create()
    {
        if (timer == null) {
            games = new HashMap<UUID, Game>();
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
        games.put(g.getId(), g);
        return g.getId();
    }

    public static Tovar getTovar(UUID userId, UUID gameId)
    {
        Game game =  games.get(gameId);
        if (game != null)
        {
            return game.getCurrentTovar();
        }

        return null;
    }

    public static boolean startGame(UUID gameId)
    {
        create();
        Game game =  games.get(gameId);
        if (game != null)
        {
            return game.startGame();
        }
        return false;
    }

    public static boolean addUserToGame(UUID gameId, UUID userId)
    {
        Game game =  games.get(gameId);
        if (game != null)
        {
            User user = SessionManipulator.getUser(userId);
            String login = DBConnector.getLogin(user.getId());
            Player p = new Player(SessionManipulator.getUser(userId), login);
            game.addPlayer(p);
            return true;
        }
        return false;
    }

    public static Player getPlayer(UUID gameId, UUID playerId)
    {
        Game game =  games.get(gameId);
        if (game != null)
        {
            return game.getPlayer(playerId);
        }
        return null;
    }

    public static boolean buy(UUID gameId, UUID playerId)
    {
        Game game =  games.get(gameId);
        if (game != null)
        {
            return game.by(playerId);
        }
        return false;
    }

    public static ArrayList<ReturnedGame> getGames()
    {
        create();
        ArrayList<ReturnedGame> returnedGames = new ArrayList<ReturnedGame>();
        for(Game game: games.values())
        {
            if(!game.getIsWork())
            {
                ReturnedGame g = new ReturnedGame();
                g.setId(game.getId());
                g.setMaxPlayerCount(game.getMaxPlayerCount());
                g.setNowPlayerCount(game.getPlayersCount());
                returnedGames.add(g);
            }
        }
        return returnedGames;
    }

    public static void gameLoop()
    {
        for(Game game: games.values())
        {
            game.timerTick(timerDelay);
        }
    }


}

class TimerTick extends TimerTask {

    @Override
    public void run() {
        GameManipulator.gameLoop();
    }
}
