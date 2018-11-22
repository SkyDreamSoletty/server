package com.lxlol.netty.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class HeartbeatEntity implements Serializable {

    private static final long serialVersionUID = 4671171056588401542L;

    private long timestamp;
    private String channelId;

    public HeartbeatEntity() {
    }

    public HeartbeatEntity(long timestamp, String channelId) {
        this.timestamp = timestamp;
        this.channelId = channelId;
    }
}
