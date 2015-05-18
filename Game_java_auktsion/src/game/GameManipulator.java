package game;

import business.ConstFields;
import business.DBConnector;
import business.SessionManipulator;
import game.bonuses.Bonus;
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
    private static HashMap<UUID, ArrayList<Player>> stats;
    private static int startCash = 100;
    private static ISender sender = new WebSocketSender();
    
    private static StatisticCreator statCreator = new StatisticCreator();

    public static void create()
    {
        if (timer == null) {
            games = new HashMap<UUID, Game>();
            stats = new HashMap<UUID, ArrayList<Player>>();

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

    public static UUID createGame(UUID id)//add tovar count
    {
        create();
        Game g = new Game(7, sender);
        Goods g1 =  DBConnector.getGoods(1);
        Goods g2 =  DBConnector.getGoods(2);
        Tovar t = new Tovar(g1, 100 );
        Tovar t2 = new Tovar(g2, 100);
        g.addTovar(t);
        g.addTovar(t2);
        games.put(g.getId(), g);
        return g.getId();
    }

    public static ArrayList<Player> getPlayers(UUID gameId)
    {
        Game game =  games.get(gameId);
        if (game != null)
        {
            return game.getPlayers();
        }
        return null;
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
            Player p = new Player(SessionManipulator.getUser(userId), startCash);
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

    public static void setStartCash(int startMoney)
    {
        startCash = startMoney;
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

    public static ArrayList<Player> getStats(UUID gameId)
    {
        return stats.get(gameId);
    }

    public static boolean buyBonus(UUID gameId, UUID playerId, Bonus bonus){
        Game game =  games.get(gameId);
        if (game != null) {
            return game.buyBonus(playerId, bonus);
        }
        return false;
    }

    public static boolean isJoker(UUID gameId){
        Game game =  games.get(gameId);
        if (game != null) {
            return game.isJoker();
        }
        return false;
    }

    public static void gameLoop()
    {
        for(UUID gameKey: games.keySet())
        {
            Game game = games.get(gameKey);
            if (game.isEnd())
            {
                ArrayList<Player> playersWithStat = statCreator.createStatistic(game.getPlayers(), game.getTovars());
                stats.put(game.getId(), playersWithStat);
                for (Player p:playersWithStat)
                {
                    DBConnector.setStatistic(p);
                }
                sender.send(ConstFields.EndGame, playersWithStat);
                games.remove(gameKey);
                continue;
            }
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
