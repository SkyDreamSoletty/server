package com.lxlol.netty.handler;


import com.lxlol.netty.util.ChannelManage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

public class TextWebSokcetFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("收到消息："+msg.text());
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间："+ LocalDateTime.now()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded:"+ctx.channel().id().asLongText());
        Channel channel = ctx.channel();
        ChannelManage.channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved:"+ctx.channel().id().asLongText());
        System.out.println(ChannelManage.channelGroup.size());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       System.out.println("异常发生");
        ctx.close();
    }
}
