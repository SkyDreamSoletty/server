package com.lxlol.netty.init;

import com.lxlol.netty.decode.HeartbeatDecoder;
import com.lxlol.netty.decode.HeartbeatEncode;
import com.lxlol.netty.entity.HeartbeatEntity;
import com.lxlol.netty.handler.EchoClientHandle;
import com.lxlol.netty.handler.MyChatClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

/**
 * 客户端和服务端连接之后这个类的对象就会被创建，就会调用initChannel方法
 */
public class MyChatClientInitalizer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline
        .addLast(new IdleStateHandler(2, 2, 4))
//        .addLast(new LineBasedFrameDecoder(1024))//netty内置的解码器，根据分隔符对消息进行解码
//                .addLast(
//                        new ObjectDecoder(1024 * 1024, ClassResolvers
//                                .weakCachingConcurrentResolver(this.getClass()
//                                        .getClassLoader())))
//                // 添加对象编码器 在服务器对外发送消息的时候自动将实现序列化的POJO对象编码
//        .addLast(new ObjectEncoder())

//        .addLast(new StringDecoder(CharsetUtil.UTF_8))
//        .addLast(new StringEncoder(CharsetUtil.UTF_8))
//        .addLast(new HeartBeatClientHandler())

        //心跳解码
                .addLast(new HeartbeatDecoder())
                .addLast(new HeartbeatEncode())
        .addLast(new EchoClientHandle())
//        .addLast(new MyChatClientHandler())
        ;
    }
}
