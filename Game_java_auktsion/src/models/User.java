package models;

import java.util.UUID;

/**
 * Created by Эмиль on 13.05.2015.
 */
public class User {
    private UUID guid;
    private int id;
    private String sessionId;

    public UUID getGUID() {
        return guid;
    }

    public void setGUID(UUID GUID) {
        this.guid = GUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
