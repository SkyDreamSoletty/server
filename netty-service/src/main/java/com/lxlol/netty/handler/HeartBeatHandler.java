package com.lxlol.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;

import java.time.LocalDateTime;

/**
 * @Description: 用于检测channel的心跳handler 
 * 				 继承ChannelInboundHandlerAdapter，从而不需要实现channelRead0方法
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		
		// 判断evt是否是IdleStateEvent（用于触发用户事件，包含 读空闲/写空闲/读写空闲 ）
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent)evt;		// 强制类型转换
			switch (event.state()){
				case READER_IDLE:
					System.out.println("进入读空闲...");
					ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间："+ LocalDateTime.now()));
					break;
				case WRITER_IDLE:
					System.out.println("进入写空闲...");
					break;
				case ALL_IDLE:
					System.out.println("读写空闲...");
					ctx.channel().close();
					break;
			}
		}
		
	}
	
}
