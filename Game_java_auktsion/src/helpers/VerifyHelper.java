package helpers;

import business.SessionManipulator;
import game.GameManipulator;

import java.util.UUID;

/**
 * Created by Эмиль on 16.05.2015.
 */
public final class VerifyHelper {
    public static boolean verifySession(UUID id)
    {
        return SessionManipulator.getUser(id) == null ? false : true;
    }

    public static boolean verifyAdmin(UUID id)
    {
        return SessionManipulator.getUser(id) != null ? SessionManipulator.getUser(id).isAdmin() : false;
    }

    public static boolean verifyGame(UUID gameId, UUID playerId)
    {
        return GameManipulator.getPlayer(gameId, playerId) == null ? false : true;
    }
}
