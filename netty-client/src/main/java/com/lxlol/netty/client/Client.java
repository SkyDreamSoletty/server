package com.lxlol.netty.client;

import com.lxlol.netty.init.MyChatClientInitalizer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Client {

    private final static Logger LOGGER = LoggerFactory.getLogger(Client.class);

    private SocketChannel channel;

    private EventLoopGroup group = new NioEventLoopGroup();

    @PostConstruct
    public void start() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new MyChatClientInitalizer())
        ;

        ChannelFuture future = bootstrap.connect("localhost", 11211).sync();
        if (future.isSuccess()) {
            LOGGER.info("启动 Netty 成功");
        }
        channel = (SocketChannel) future.channel();
    }

//    public static void main(String[] args) throws Exception{
//        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
//        try{
//            Bootstrap bootstrap = new Bootstrap();
//            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
//                    .handler(new MyChatClientInitalizer());
//
////            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
//            ChannelFuture future = bootstrap.connect("localhost", 11211).sync();
//            if (future.isSuccess()) {
//                SocketChannel socketChannel = (SocketChannel)future.channel();
//                System.out.println("connect server  成功---------");
//            }
////            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
////            while (true){
////                channel.writeAndFlush("from client:"+ LocalDateTime.now()+"\r\n");
////                System.out.println("_____");
////                channel.writeAndFlush(br.readLine()+"\r\n");
////            }
//        }finally {
//            eventLoopGroup.shutdownGracefully();
//        }
//    }

}
