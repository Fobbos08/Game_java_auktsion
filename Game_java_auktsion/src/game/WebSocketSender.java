package game;

import business.WebSocketHelper;

import java.util.ArrayList;

public class WebSocketSender implements ISender {
    @Override
    public void send(String message, ArrayList<Player> players) {
        ArrayList<String> ids = new ArrayList<String>();
        for(int i=0; i<players.size(); i++)
        {
            ids.add(players.get(i).getSessionId());
        }
        WebSocketHelper.sendUpdateInfo(message, ids);
    }
}
