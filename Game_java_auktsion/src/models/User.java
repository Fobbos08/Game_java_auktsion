package models;

import java.util.UUID;

/**
 * Created by Эмиль on 13.05.2015.
 */
public class User {
    private UUID guid;
    private int id;

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
}
