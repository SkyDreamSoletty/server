package com.lxlol.netty.decode;

import com.lxlol.netty.entity.HeartbeatEntity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Function:编码
 *
 * @author crossoverJie
 *         Date: 17/05/2018 19:07
 * @since JDK 1.8
 */

public class HeartbeatEncode extends MessageToByteEncoder<HeartbeatEntity> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, HeartbeatEntity heartbeatEntity, ByteBuf byteBuf) throws Exception {
        byteBuf.writeLong(heartbeatEntity.getTimestamp()) ;
        byteBuf.writeBytes(heartbeatEntity.getChannelId().getBytes()) ;
    }

}
