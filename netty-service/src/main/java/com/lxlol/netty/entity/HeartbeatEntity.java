package com.lxlol.netty.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class HeartbeatEntity implements Serializable {

    private static final long serialVersionUID = 4671171056588401542L;

    private String channelId;

    private Long timestamp;

}
