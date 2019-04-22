package com.lxlol.netty.controller;

import com.lxlol.netty.server.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Function:
 *
 * @author crossoverJie
 * Date: 22/05/2018 14:46
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private NettyServer nettyServer;

    /**
     * 向所有通道发送消息端发消息
     *
     * @return
     */
    @RequestMapping("sendMsg")
    public String sendMsg(String string) {
        nettyServer.sendMsg(string);
        return "success";
    }
}
