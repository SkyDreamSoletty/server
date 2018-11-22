package com.lxlol.netty.init;

import com.lxlol.netty.decode.HeartbeatDecoder;
import com.lxlol.netty.handler.HeartBeatHandler;
import com.lxlol.netty.handler.NettyServerHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Function:
 *
 * @author crossoverJie
 *         Date: 17/05/2018 18:51
 * @since JDK 1.8
 */
public class NettyServerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                .addLast(new IdleStateHandler(5, 5, 10))
                .addLast(new HeartbeatDecoder())
                // 自定义的空闲状态检测
        .addLast(new HeartBeatHandler())
        .addLast(new NettyServerHandler());
    }
}
