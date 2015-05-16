package game;

import business.SessionManipulator;
import business.WebSocketHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Эмиль on 02.05.2015.
 */
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


    private int currentSessionNumber;
    private boolean sessionIsWork;

    private ArrayList<Player> players;
    private ArrayList<Tovar> tovars;
    private ArrayList<Integer> tovarsCount;

    private Random rnd;

    public Game(int maxPlayerCount) {
        rnd = new Random();
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
        timerInterval = Math.abs(rnd.nextInt()) % 20 + 5;
        costInterval = Math.abs(rnd.nextInt()) % 10;
        minCost = Math.abs(rnd.nextInt()) % 50 + 1;

        currentTovarIndex = getTovarIndex();
        currentCost = startCost;
        currentSessionNumber++;
        sessionIsWork = true;
        return true;
    }

    private int getTovarIndex() {
        int index = 0;
        return index;
    }

    public void timerTick(int timerInterval) {
        timerTime += timerInterval;

        if (sessionIsWork) {
            sessionTick();
        }
    }

    public void sessionTick() {
        if (timerTime - lastTime > timerInterval*100) {
            lastTime = timerTime;
            currentCost -= costInterval;
            Tovar t = tovars.get(currentTovarIndex);
            t.setCurrentCost(currentCost);
            webSocketSend();
        }

        stopSession(false);
    }

    private void webSocketSend() {
        ArrayList<String> ids = new ArrayList<String>();
        for(int i=0; i<players.size(); i++)
        {
            ids.add(players.get(i).getSessionId());
        }
        WebSocketHelper.sendUpdateInfo("tick", ids);
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
        if(tovarsCount.get(currentTovarIndex)<=0)
        {
            tovars.remove(currentTovarIndex);
            tovarsCount.remove(currentTovarIndex);
        }
    }

    private void stopSession(boolean isBy) {
        if (isBy) {
            sessionIsWork = false;
            webSocketSend();
            removeCurrentTovar();
            startNextSession();
        }
        if (currentCost <= minCost){
            sessionIsWork = false;
            webSocketSend();
            removeCurrentTovar();
            startNextSession();
        }
        //если закончились товары то isWork = false;
        //и возвращаем игроков для построения статистики
        //tovarsCount.get(currentTovarIndex)--;
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
}
