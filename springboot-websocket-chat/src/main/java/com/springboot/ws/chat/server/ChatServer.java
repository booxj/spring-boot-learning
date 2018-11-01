package com.springboot.ws.chat.server;

import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ChatServer {

    //在线总人数
    public static AtomicInteger onlineCount = new AtomicInteger(0);

    //存放所有的用户
    public static CopyOnWriteArraySet<ChatServer> userSet = new CopyOnWriteArraySet<>();

    //会话Session
    protected Session session;

    public void onOpen(Session session, EndpointConfig config) throws IOException {
    }

    public void onClose() throws IOException {

    }

    public void onMessage(String message) throws IOException {

    }

    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }


    protected void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    protected abstract void broadcast(String message) throws IOException;

    protected int getOnlineCount() {
        return onlineCount.get();
    }

    protected void addOnlineCount() {
        onlineCount.incrementAndGet();
    }

    protected void subOnlineCount() {
        onlineCount.decrementAndGet();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
