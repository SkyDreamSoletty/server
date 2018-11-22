package com.lxlol.netty.decode;

import com.lxlol.netty.entity.HeartbeatEntity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Function: 解码信息
 *
 * @author crossoverJie
 *         Date: 17/05/2018 18:34
 * @since JDK 1.8
 */
public class HeartbeatDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        long timestamp = in.readLong() ;
        byte[] bytes = new byte[in.readableBytes()] ;
        in.readBytes(bytes);
        String channelId = new String(bytes) ;

        HeartbeatEntity heartbeatEntity = new HeartbeatEntity() ;
        heartbeatEntity.setChannelId(channelId);
        heartbeatEntity.setTimestamp(timestamp);
        out.add(heartbeatEntity) ;
    }
}
