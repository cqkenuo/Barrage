package org.medusa.barrage.action;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by 王湛智 on 2015/11/22.
 * 说明：
 */

@ServerEndpoint("/init")
public class BarrageSocketClient {

    private static HashMap<String, Object> SESSION_MAP = new HashMap<String, Object>();

    @OnOpen
    public void onOpen(Session session) {
        SESSION_MAP.put(session.getId(), session);
        System.out.println("user [" + session.getId() + "] connected");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Received: " + message);
        send(message);
    }

    @OnError
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @OnClose
    public void onClose(Session session) {
        SESSION_MAP.remove(session.getId());
        System.out.println("user [" + session.getId() + "] closed");
    }

    private void send(String message) throws IOException {
        Set<String> keys = SESSION_MAP.keySet();
        for(String key : keys) {
            Session session = (Session) SESSION_MAP.get(key);
            if(!session.isOpen()) {
                return;
            }
            session.getBasicRemote().sendText(message);
        }
    }
}
