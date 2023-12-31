package com.socket4j.client.netty.channel;

import com.socket4j.base.pojo.config.ClientConfig;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.ipfilter.AbstractRemoteAddressFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
@Scope("prototype")
public class IpSecurityHandler  extends AbstractRemoteAddressFilter<InetSocketAddress> {
    private final static Logger logger = LoggerFactory.getLogger(IpSecurityHandler.class);
    @Autowired
    ClientConfig clientConfig;

    @Override
    protected boolean accept(ChannelHandlerContext channelHandlerContext, InetSocketAddress inetSocketAddress) throws Exception {
        String ip = inetSocketAddress.getAddress().toString();
        if(clientConfig.getIpBlackList().get(ip)) {
            return false;
        }
        return true;
    }
}
