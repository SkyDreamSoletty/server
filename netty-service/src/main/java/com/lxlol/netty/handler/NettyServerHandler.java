package com.lxlol.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.lxlol.netty.entity.HeartbeatEntity;
import com.lxlol.netty.server.NettyServer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


public class NettyServerHandler extends SimpleChannelInboundHandler<HeartbeatEntity> {

    private final static Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    /**
     * ChannelHandlerContext表示的是上下文 可以获得一些相关的信息，比如连接地址等
     * @param ctx
     * @param heartbeatEntity 真正的接受到的请求对象
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartbeatEntity heartbeatEntity) throws Exception {
        LOGGER.info("服务端收到消息：{}",JSONObject.toJSONString(heartbeatEntity));
        String longId = ctx.channel().id().asLongText();
        long timestamp = new Date().getTime();
        ByteBuf HEART_BEAT =  Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(new HeartbeatEntity(timestamp, longId).toString(), CharsetUtil.UTF_8));
        ctx.writeAndFlush(HEART_BEAT);
    }

}
