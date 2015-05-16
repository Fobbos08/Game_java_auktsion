package game;

import java.util.UUID;

/**
 * Created by Эмиль on 16.05.2015.
 */
public class ReturnedGame {
    private UUID id;
    private int maxPlayerCount;
    private int nowPlayerCount;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getMaxPlayerCount() {
        return maxPlayerCount;
    }

    public void setMaxPlayerCount(int maxPlayerCount) {
        this.maxPlayerCount = maxPlayerCount;
    }

    public int getNowPlayerCount() {
        return nowPlayerCount;
    }

    public void setNowPlayerCount(int nowPlayerCount) {
        this.nowPlayerCount = nowPlayerCount;
    }
}
