package com.lxlol.netty.init;

import com.lxlol.netty.decode.HeartbeatDecoder;
import com.lxlol.netty.decode.HeartbeatEncode;
import com.lxlol.netty.handler.HeartBeatHandler;
import com.lxlol.netty.handler.NettyServerHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.util.CharsetUtil;

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
                .addLast(new IdleStateHandler(10, 10, 15))
//                .addLast(new ObjectDecoder(1024, ClassResolvers
//                                .weakCachingConcurrentResolver(this.getClass()
//                                        .getClassLoader())))
//                // 添加对象编码器 在服务器对外发送消息的时候自动将实现序列化的POJO对象编码
//                .addLast(new ObjectEncoder())
        //字符串解析,换行防拆包
//        .addLast(new LineBasedFrameDecoder(1024))
//                .addLast(new StringDecoder(CharsetUtil.UTF_8))
//                .addLast(new StringEncoder(CharsetUtil.UTF_8))
        // ====================== 以下是用于支持http协议    ======================
        // websocket 基于http协议，所以要有http编解码器
//        .addLast(new HttpServerCodec())
        // 对写大数据流的支持
//        .addLast(new ChunkedWriteHandler())
        // 对httpMessage进行聚合，聚合成FullHttpRequest或FullHttpResponse
        // 几乎在netty中的编程，都会使用到此hanler
//        .addLast(new HttpObjectAggregator(1024*64))
        // ====================== 以上是用于支持http协议    ======================

        // ====================== 增加心跳支持 start    ======================
        // 读空闲/写空闲/读写空闲 -- 心跳机制
                .addLast(new HeartbeatDecoder())
                .addLast(new HeartbeatEncode())
                // 自定义的空闲状态检测
        .addLast(new HeartBeatHandler())
        // ====================== 增加心跳支持 end    ======================
        // ====================== 以下是支持httpWebsocket ======================

        /**
         * websocket 服务器处理的协议，用于指定给客户端连接访问的路由 : /ws
         * 本handler会帮你处理一些繁重的复杂的事
         * 会帮你处理握手动作： handshaking（close, ping, pong） ping + pong = 心跳
         * 对于websocket来讲，都是以frames进行传输的，不同的数据类型对应的frames也不同
         */
//        .addLast(new WebSocketServerProtocolHandler("/ws"))
        // 自定义的handler
        .addLast(new NettyServerHandler());
    }
}
