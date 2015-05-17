package game;

import java.util.ArrayList;

/**
 * Created by Эмиль on 17.05.2015.
 */
public interface ISender {
    public void send(String message, ArrayList<Player> players);
}
