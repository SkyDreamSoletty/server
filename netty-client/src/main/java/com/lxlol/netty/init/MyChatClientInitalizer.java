package com.lxlol.netty.init;

import com.lxlol.netty.decode.HeartbeatEncode;
import com.lxlol.netty.handler.ClientHandler;
import com.lxlol.netty.handler.HeartbeatClientHandle;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 客户端和服务端连接之后这个类的对象就会被创建，就会调用initChannel方法
 */
public class MyChatClientInitalizer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
        .addLast(new IdleStateHandler(2, 2, 4))
        .addLast(new HeartbeatEncode())
        .addLast(new HeartbeatClientHandle())
        .addLast(new ClientHandler())
        ;
    }
}
