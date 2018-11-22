package com.lxlol.netty.handler;

import com.lxlol.netty.entity.HeartbeatEntity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private final static Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);

    /**
     * ChannelHandlerContext表示的是上下文 可以获得一些相关的信息，比如连接地址等
     * @param ctx
     * @param msg 真正的接受到的请求对象
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

        //从服务端收到消息时被调用
        LOGGER.info("客户端收到消息={}",msg.toString(CharsetUtil.UTF_8)) ;

    }



    /**
     * 出现异常后会调用此方法
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
