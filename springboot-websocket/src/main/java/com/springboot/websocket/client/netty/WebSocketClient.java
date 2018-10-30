package com.springboot.websocket.client.netty;

import com.springboot.websocket.client.netty.handler.WebSocketClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class WebSocketClient {

    public static void main(String[] args) throws InterruptedException, URISyntaxException, UnknownHostException {


        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap boot = new Bootstrap();
        boot.option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_BACKLOG, 1024 * 1024 * 10)
                .group(group)
                .handler(new LoggingHandler(LogLevel.INFO))
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline p = socketChannel.pipeline();

                        //wss协议
//                        SSLEngine sslEngine = SSLContext.getDefault().createSSLEngine();
//                        sslEngine.setUseClientMode(true);
//                        p.addLast("ssl", new SslHandler(sslEngine));

                        p.addLast(new ChannelHandler[]{new HttpClientCodec(), new HttpObjectAggregator(1024 * 1024 * 10)});
                        p.addLast("hookedHandler", new WebSocketClientHandler());
                    }
                });

        URI websocketURI = new URI("ws://127.0.0.1:8000/ws");

        HttpHeaders httpHeaders = new DefaultHttpHeaders();
        //进行握手
        WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(websocketURI, WebSocketVersion.V13, (String) null, true, httpHeaders);
        System.out.println("connect");
        final Channel channel = boot.connect(websocketURI.getHost(), getPort(websocketURI)).sync().channel();


        WebSocketClientHandler handler = (WebSocketClientHandler) channel.pipeline().get("hookedHandler");
        handler.setHandshaker(handshaker);
        handshaker.handshake(channel);
        //阻塞等待是否握手成功
        handler.handshakeFuture().sync();
    }

    static int getPort(URI uri) {
        int port = uri.getPort();
        if (port == -1) {
            String scheme = uri.getScheme();
            if ("wss".equals(scheme)) {
                return 443;
            } else if ("ws".equals(scheme)) {
                return 80;
            } else {
                throw new IllegalArgumentException("unknown scheme: " + scheme);
            }
        } else {
            return port;
        }
    }

}
