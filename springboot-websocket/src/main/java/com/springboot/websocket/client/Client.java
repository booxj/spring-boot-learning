package com.springboot.websocket.client;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

public class Client {


    public static WebSocketClient client;


    public static void main(String[] args) throws URISyntaxException, UnsupportedEncodingException {
//        client = new WebSocketClient(new URI("ws://172.19.80.181:801/ws/v1/dxjl?access_token=c8c899bc5d5a46792f94b4fa0d73ef2c.001537497987.1041"), new Draft_17()) {
        client = new WebSocketClient(new URI("ws://127.0.0.1:5000/ws"), new Draft_17()) {

            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                System.out.println("打开链接");
            }

            @Override
            public void onMessage(String s) {
                System.out.println(s);
            }

            @Override
            public void onMessage(ByteBuffer bytes) {
                try {
                    System.out.println(new String(bytes.array(), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                System.out.println("链接已关闭");
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }

        };

        client.connect();

        while (!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
            System.out.println("还没有打开");
        }
        System.out.println("打开了");
        send("hello world".getBytes("utf-8"));
        client.send("hello world");
    }

    public static void send(byte[] bytes) {
        client.send(bytes);
    }
}
