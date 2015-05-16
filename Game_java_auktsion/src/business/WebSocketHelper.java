package business;

import org.apache.tomcat.websocket.WsSession;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Эмиль on 15.05.2015.
 */
@ServerEndpoint("/socket")
public final class WebSocketHelper {

    static ArrayList<Session> sessions = new ArrayList<Session>();
    private static Set<Session> allSessions;

    @OnOpen
    public void follow(Session session){
        allSessions = session.getOpenSessions();
        //sendUpdateInfo(session);
        for(Session ss: sessions)
        {
            if (((WsSession) ss).getHttpSessionId().equals(((WsSession) session).getHttpSessionId()))
                return;
        }
        sessions.add(session);
    }

    public static void sendUpdateInfo(String message, ArrayList<String> ids){
        //allSessions = session.getOpenSessions();
        for (int i = 0; i<sessions.size(); i++){
            try{
                for(String ss: ids)
                    if(((WsSession) sessions.get(i)).getHttpSessionId().equals(ss)) {
                        sessions.get(i).getBasicRemote().sendText(message);
                    }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }
}
