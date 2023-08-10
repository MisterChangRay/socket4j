package com.socket4j.client;

import com.socket4j.client.netty.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication( scanBasePackages = "com.socket4j")
public class Socket4Client implements ApplicationRunner {
    @Autowired
    NettyServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(Socket4Client.class);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        nettyServer.start();
    }
}
