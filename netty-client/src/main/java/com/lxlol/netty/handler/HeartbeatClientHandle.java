package com.lxlol.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.lxlol.netty.entity.HeartbeatEntity;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Function:
 *
 * @author crossoverJie
 *         Date: 16/02/2018 18:09
 * @since JDK 1.8
 */
public class HeartbeatClientHandle extends ChannelInboundHandlerAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(HeartbeatClientHandle.class);

        @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt ;

            if (idleStateEvent.state() == IdleState.WRITER_IDLE){
                LOGGER.info("已经 10 秒没有发送信息！");
                //向服务端发送消息
                String longId = ctx.channel().id().asLongText();
                long timestamp = new Date().getTime();
                ByteBuf HEART_BEAT =  Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(new HeartbeatEntity(timestamp, longId).toString(), CharsetUtil.UTF_8));
                ctx.writeAndFlush(HEART_BEAT).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }

        }
        super.userEventTriggered(ctx, evt);
    }

}
