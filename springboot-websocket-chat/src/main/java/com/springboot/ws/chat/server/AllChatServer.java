package com.springboot.ws.chat.server;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/ws/chat")
@Component
public class AllChatServer extends ChatServer {

    @OnOpen
    @Override
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        this.session = session;
        this.userSet.add(this);
        addOnlineCount();
        sendMessage(session,"连接成功");
    }

    @OnClose
    @Override
    public void onClose() throws IOException {
        this.userSet.remove(this);
        subOnlineCount();
        broadcast("one client has been disconnected.");
        System.out.println(("有一连接关闭！当前在线人数为" + getOnlineCount()));
    }

    @OnMessage
    @Override
    public void onMessage(String message) throws IOException {
        broadcast(message);
    }

    @OnError
    @Override
    public void onError(Session session, Throwable error) {
        super.onError(session, error);
    }

    @Override
    protected void broadcast(String message) throws IOException {
        for (ChatServer server : userSet) {
            sendMessage(server.session,message);
        }
    }
}
