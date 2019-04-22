package com.lxlol.netty.server;

import com.lxlol.netty.init.WebSocketChannelInitalizer;
import com.lxlol.netty.util.ChannelManage;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Map;


@Component
public class NettyServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup work = new NioEventLoopGroup();


    @Value("${netty.server.port}")
    private int nettyPort;


    /**
     * 启动 Netty
     *
     * @return
     * @throws InterruptedException
     */
    @PostConstruct
    public void start() throws InterruptedException {

        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(nettyPort))
                .handler(new LoggingHandler(LogLevel.INFO))
                //保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //childHandler针对的是workerGroup，handler针对的是bossGroup
                .childHandler(new WebSocketChannelInitalizer());
//                .childHandler(new NettyServerInitializer());

        ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()) {
            LOGGER.info("启动 Netty 成功");
        }
    }

    /**
     * 发送消息
     *
     */
    public void sendMsg(String message) {
        ChannelManage.channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    }


    /**
     * 销毁
     */
    @PreDestroy
    public void destroy() {
        boss.shutdownGracefully().syncUninterruptibly();
        work.shutdownGracefully().syncUninterruptibly();
        LOGGER.info("关闭 Netty 成功");
    }

}
