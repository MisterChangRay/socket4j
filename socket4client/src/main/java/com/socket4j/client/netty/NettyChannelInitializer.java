package com.socket4j.client.netty;

import com.socket4j.client.netty.channel.BaseDecoderHandler;
import com.socket4j.client.netty.channel.ConnectionHandler;
import com.socket4j.client.netty.channel.ExceptionHandler;
import com.socket4j.client.netty.channel.IpSecurityHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Scope("prototype")
@Component
public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Autowired
    IpSecurityHandler ipSecurityHandler;
    @Autowired
    ConnectionHandler connectionHandler;
    @Autowired
    BaseDecoderHandler baseDecoderHandler;
    @Autowired
    ExceptionHandler exceptionHandler;
    @Value("${socket4j.message.readerIdleTime}")
    private int readerIdleTime;
    @Value("${socket4j.message.writerIdleTime}")
    private int writerIdleTime;
    @Value("${socket4j.message.allIdleTime}")
    private int allIdleTime;
    @Value("${socket4j.connection.maxReceiveBytesPreSec}")
    private int maxReceiveBytesPreSec;
    @Value("${socket4j.message.maxSendBytesPreSec}")
    private int maxSendBytesPreSec;
    @Override
    protected void initChannel(SocketChannel nioDatagramChannel) throws Exception {
        ChannelPipeline pipeline = nioDatagramChannel.pipeline();
        pipeline.addLast(ipSecurityHandler);
        pipeline.addLast(connectionHandler);
        // 限制每个连接每秒发送和接收流量不能大于1024字节
        pipeline.addLast(new ChannelTrafficShapingHandler(maxSendBytesPreSec,maxReceiveBytesPreSec,1000));
        // 心跳监测
        pipeline.addLast("handler",new IdleStateHandler(readerIdleTime, writerIdleTime, allIdleTime, TimeUnit.SECONDS));
        pipeline.addLast(baseDecoderHandler);
        pipeline.addLast(exceptionHandler);


    }
}
