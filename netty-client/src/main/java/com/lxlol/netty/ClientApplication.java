package com.lxlol.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author crossoverJie
 */
@SpringBootApplication
public class ClientApplication {

	private final static Logger LOGGER = LoggerFactory.getLogger(ClientApplication.class);

	public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
		LOGGER.info("启动 Netty Client 成功");
	}

}