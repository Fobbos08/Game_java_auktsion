package game;

import business.ConstFields;
import business.SessionManipulator;
import business.WebSocketHelper;
import game.bonuses.Bonus;
import game.bonuses.BonusFactory;
import game.bonuses.IBuyHelper;
import game.bonuses.Joker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Game {
    private UUID gameId;
    private int startCost = 100;
    private int costInterval;
    private int currentCost;
    private int minCost;
    private boolean isWork = false;
    private int maxPlayerCount;
    private int sessionsCount;
    private int timerTime;
    private int timerInterval;
    private int lastTime;
    private int currentTovarIndex;
    private boolean gameIsEnd = false;
    private int startPlayerMoney = 100;

    private boolean isJoker = false;
    private IBuyHelper joker = new Joker();


    private int currentSessionNumber;
    private boolean sessionIsWork;

    private ArrayList<Player> players;
    private ArrayList<Tovar> tovars;
    private ArrayList<Integer> tovarsCount;

    private ISender sender;

    public Game(int maxPlayerCount, ISender sender) {
        this.sender = sender;
        this.maxPlayerCount = maxPlayerCount;
        int currentSessionNumber = 0;
        sessionIsWork = false;
        gameId = UUID.randomUUID();
    }

    public UUID getId() {
        return gameId;
    }

    public boolean startGame() {
        int tovarCount = getPlayersCount() / 2 + 1;
        fillTovarsCount(tovarCount);
        sessionsCount = 3 * tovarCount * tovars.size();
        isWork = true;
        startNextSession();
        return true;
    }

    public boolean startNextSession() {
        currentTovarIndex = getTovarIndex();
        if (currentTovarIndex < 0) {
            gameIsEnd = true;
            return false;
        }

        randomJoker();

        tovars.get(currentTovarIndex).setCurrentCost(startCost);

        timerInterval = 1 + (int) (Math.random() * 20);
        costInterval = 1 + (int) (Math.random() * 10);
        minCost = 1 + (int) (Math.random() * 50);

        currentCost = startCost;
        currentSessionNumber++;
        sessionIsWork = true;
        return true;
    }

    private int getTovarIndex() {
        for (int i = 0; i < tovarsCount.size(); i++) {
            if (tovarsCount.get(i) > 0) {
                return i;
            }
        }
        return -1;
    }

    public void timerTick(int timerInterval) {
        timerTime += timerInterval;

        if (sessionIsWork) {
            sessionTick();
        }
    }

    public void randomJoker() {
        if (Math.random() * 100 < 10) {
            isJoker = true;
            sender.send("joker", players);
        } else {
            isJoker = false;
            sender.send("joker", players);
        }
    }

    public boolean isJoker()
    {
        return isJoker;
    }

    public void sessionTick() {
        if (timerTime - lastTime > timerInterval*1000) {
            randomJoker();
            lastTime = timerTime;
            currentCost -= costInterval;
            Tovar t = tovars.get(currentTovarIndex);
            t.setCurrentCost(currentCost);
            sender.send(ConstFields.Tick, players);
        }

        stopSession(false);
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public Player getPlayer(UUID id)
    {
        for(Player player: players)
        {
            if (player.getGuid().equals(id))
            {
                return player;
            }
        }
        return null;
    }

    private void fillTovarsCount(int tovarCount) {
        if (tovarsCount == null) tovarsCount = new ArrayList<Integer>();
        for (int i = 0; i < tovars.size(); i++) {
            tovarsCount.add(tovarCount);
        }
    }

    public String addPlayer(Player Player) {
        if (players == null) players = new ArrayList<Player>();
        if (maxPlayerCount <= players.size()) return "";
        players.add(Player);
        sender.send(ConstFields.AddingUser, players);
        return "anyGuid";
    }

    public boolean addTovar(Tovar Tovar) {
        if (tovars == null) tovars = new ArrayList<Tovar>();
        tovars.add(Tovar);
        return true;
    }

    private void removeCurrentTovar()
    {
        tovarsCount.set(currentTovarIndex, tovarsCount.get(currentTovarIndex)-1);
        /*if(tovarsCount.get(currentTovarIndex)<=0)
        {
            tovars.remove(currentTovarIndex);
            tovarsCount.remove(currentTovarIndex);
        }*/
    }

    private void stopSession(boolean isBy) {
        if (isBy) {
            sessionIsWork = false;
            sender.send(ConstFields.Tick, players);
            removeCurrentTovar();
            startNextSession();
        }
        if (currentCost <= minCost){
            sessionIsWork = false;
            sender.send(ConstFields.Tick, players);
            removeCurrentTovar();
            startNextSession();
        }
    }

    public int getPlayersCount() {
        return players.size();
    }

    public int getMaxPlayerCount() {
        return maxPlayerCount;
    }

    public boolean by(UUID guid) {
        for (int i = 0; i < players.size(); i++) {
            Player currentPlayer = players.get(i);
            if (currentPlayer.getGuid().equals(guid)) {
                boolean isOk = currentPlayer.by(tovars.get(currentTovarIndex));
                stopSession(isOk);
                sender.send(ConstFields.Tick, players);
                return isOk;
            }
        }
        return false;
    }

    public boolean isActive() {
        return isWork;
    }

    public Tovar getCurrentTovar()
    {
        return tovars.get(currentTovarIndex);
    }

    public boolean getIsWork()
    {
        return isWork;
    }

    public int GetNowPlayerCount()
    {
        return players.size();
    }

    public boolean isEnd()
    {
        return gameIsEnd;
    }

    public ArrayList<Tovar> getTovars()
    {
        return tovars;
    }

    public boolean buyBonus(UUID guid, Bonus bonus)
    {
        for (int i = 0; i < players.size(); i++) {
            Player currentPlayer = players.get(i);
            if (currentPlayer.getGuid().equals(guid)) {
                boolean isOk = currentPlayer.buyBonus(BonusFactory.createHelper(bonus));
                return isOk;
            }
        }
        return false;
    }


}
