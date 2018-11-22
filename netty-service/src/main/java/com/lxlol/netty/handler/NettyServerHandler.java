package com.lxlol.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.lxlol.netty.entity.HeartbeatEntity;
import com.lxlol.netty.server.NettyServer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static com.lxlol.netty.util.ChannelManage.channelGroup;

public class NettyServerHandler extends SimpleChannelInboundHandler<HeartbeatEntity> {

    private final static Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

//public class NettyServerHandler extends SimpleChannelInboundHandler<String> {
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
//        ctx.channel().writeAndFlush("from server:"+ UUID.randomUUID()+"\n");
//        System.out.println(msg);
//    }

    /**
     * ChannelHandlerContext表示的是上下文 可以获得一些相关的信息，比如连接地址等
     * @param ctx
     * @param heartbeatEntity 真正的接受到的请求对象
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartbeatEntity heartbeatEntity) throws Exception {

        LOGGER.info(JSONObject.toJSONString(heartbeatEntity));
        Channel channel = ctx.channel();

        channelGroup.forEach(ch ->{
//                    if(channel != ch){
//                        ch.writeAndFlush(heartbeatEntity);
//                    }else{
                        HeartbeatEntity heartBeat = new HeartbeatEntity();
                        heartBeat.setChannelId(ctx.channel().id().asLongText());
                        heartBeat.setTimestamp(new Date().getTime());
                        ctx.writeAndFlush(heartBeat);
//                    }
                }
        );
//        ctx.channel().writeAndFlush("from server:"+ UUID.randomUUID());
    }
//
//
//    /**
//     * 表示连接建立
//     * @param ctx
//     * @throws Exception
//     */
//    @Override
//    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        Channel channel = ctx.channel();
////        channelGroup.writeAndFlush("【服务器】-"+channel.remoteAddress()+" 加入\n");//会遍历channelGroup中的每个channel并发送消息
//        channelGroup.add(channel);
//    }
//
//    /**
//     * 表示连接断开
//     * @param ctx
//     * @throws Exception
//     */
//    @Override
//    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        Channel channel = ctx.channel();
//        channelGroup.writeAndFlush("【服务器】-"+channel.remoteAddress()+" 离开\n");//会遍历channelGroup中的每个channel并发送消息
//        //netty会自动调用remove方法删除这个断开连接的channel
//        System.out.println(channelGroup.size());
//    }
//
//    /**
//     * 表示连接表示活动状态
//     * @param ctx
//     * @throws Exception
//     */
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        Channel channel = ctx.channel();
//        System.out.println("【服务器】-"+channel.remoteAddress()+" 上线\n");
//    }
//
//    /**
//     *
//     * @param ctx
//     * @throws Exception
//     */
//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        Channel channel = ctx.channel();
//        System.out.println("【服务器】-"+channel.remoteAddress()+" 下线\n");
//    }
//
//    /**
//     * 出现异常后会调用此方法
//     * @param ctx
//     * @param cause
//     * @throws Exception
//     */
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        ctx.close();
//    }

}
