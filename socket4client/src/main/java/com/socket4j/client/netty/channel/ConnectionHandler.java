package com.socket4j.client.netty.channel;

import com.socket4j.base.pojo.monitor.ClientInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ConnectionHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    ClientInfo clientInfo;

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        clientInfo.getConnectionCount().incrementAndGet();

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        clientInfo.getConnectionCount().decrementAndGet();
    }
}
