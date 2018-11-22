package com.lxlol.netty.handler;

import com.lxlol.netty.decode.HeartbeatEncode;
import com.lxlol.netty.entity.HeartbeatEntity;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
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
//public class EchoClientHandle extends SimpleChannelInboundHandler<HeartbeatEntity> {
public class EchoClientHandle extends SimpleChannelInboundHandler<String> {

    private final static Logger LOGGER = LoggerFactory.getLogger(EchoClientHandle.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务端返回的数据："+msg);
    }

        @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt ;

            if (idleStateEvent.state() == IdleState.WRITER_IDLE){
                LOGGER.info("已经 10 秒没有发送信息！");
                //向服务端发送消息
                HeartbeatEntity heartBeat = new HeartbeatEntity();
                heartBeat.setChannelId(ctx.channel().id().asLongText());
                heartBeat.setTimestamp(new Date().getTime());
//                ctx.writeAndFlush(heartBeat);
                    ctx.writeAndFlush("test"+"\n");
//                        addListener(ChannelFutureListener.CLOSE_ON_FAILURE) ;
            }


        }

        super.userEventTriggered(ctx, evt);
    }

//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//
//        if (evt instanceof IdleStateEvent){
//            IdleStateEvent idleStateEvent = (IdleStateEvent) evt ;
//
//            if (idleStateEvent.state() == IdleState.WRITER_IDLE){
//                LOGGER.info("已经 10 秒没有发送信息！");
//                //向服务端发送消息
//                HeartbeatEntity heartBeat = new HeartbeatEntity();
//                heartBeat.setChannelId(ctx.channel().id().asLongText());
//                heartBeat.setTimestamp(new Date().getTime());
//                ctx.writeAndFlush(heartBeat);
////                        addListener(ChannelFutureListener.CLOSE_ON_FAILURE) ;
//            }
//
//
//        }
//
//        super.userEventTriggered(ctx, evt);
//    }

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        //客户端和服务端建立连接时调用
//        LOGGER.info("已经建立了联系。。");
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        //异常时断开连接
//        cause.printStackTrace() ;
//        ctx.close() ;
//    }
//
//    @Override
//    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HeartbeatEntity heartbeatEntity) throws Exception {
//
////        从服务端收到消息时被调用
//        LOGGER.info("服务器本地时间戳为=={}",heartbeatEntity.getTimestamp()) ;
//
//    }
}
