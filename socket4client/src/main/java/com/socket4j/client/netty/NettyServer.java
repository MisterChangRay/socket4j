package com.socket4j.client.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.com.socket4j.base.util.CustomThreadFactory;

@Service
public class NettyServer {
    private static int coreSize = Runtime.getRuntime().availableProcessors();
    private final static Logger logger = LoggerFactory.getLogger(NettyServer.class);
    @Value("${socket4j.netty.soBacklog:1024}")
    private int SO_BACKLOG;
    @Value("${socket4j.netty.revbufAllocator:512}")
    private int RCVBUF_ALLOCATOR;
    @Value("${socket4j.netty.soRcvbuf:512}")
    private int SO_RCVBUF;
    @Value("${socket4j.netty.soSndbuf:512}")
    private int SO_SNDBUF;
    @Value("${socket4j.netty.portStart:7500}")
    private int portStart;
    @Value("${socket4j.netty.portEnd:7510}")
    private int portEnd;

    @Autowired
    NettyChannelInitializer nettyChannelInitializer;

    public void startTCPServer(int port) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(10);
        EventLoopGroup workerGroup = new NioEventLoopGroup(coreSize * 8 ,
                new CustomThreadFactory("socket4j-worker-thread"));

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .channel(NioServerSocketChannel.class)
                    .childHandler(nettyChannelInitializer)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option((ChannelOption.SO_BACKLOG), SO_BACKLOG)
                    .option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(RCVBUF_ALLOCATOR))
                    .option(ChannelOption.SO_RCVBUF, SO_RCVBUF)
                    .option(ChannelOption.SO_SNDBUF, SO_SNDBUF)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
            ;

            ChannelFuture channelFuture = null;
            for(int i=portStart, j = portEnd; i<=j; i++) {
                channelFuture = bootstrap.bind(i).sync();
                channelFuture.channel().closeFuture().addListener((ChannelFutureListener) future -> {
                    future.channel().close();
                });
            }
            logger.info("Netty Server Started Success! Port: {} - {}", portStart, portEnd);

//            Channel sender = bootstrap.bind(port).sync().channel();
//            sender.closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("Netty Server Started Fail!", e);
        } finally {
//            bossGroup.shutdownGracefully();
        }
    }
}
