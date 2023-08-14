package com.socket4j.client.netty.channel;

import com.socket4j.client.consts.AttrKeys;
import io.netty.channel.*;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ExceptionHandler extends ChannelDuplexHandler {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state()== IdleState.READER_IDLE){
                logger.info("连接长时间没有发送心跳数据, 主动断开");
                if(!ctx.isRemoved()) {
                    if(!ctx.channel().hasAttr(AttrKeys.OFFLINE_TIME) {
                        ctx.channel().attr(AttributeKeyConsts.closeStatus).set(DeviceTcpMessage.MessageType.OFFLINE_TIMEOUT);
                    }
                    ctx.channel().close();
                }
            }
        }else {
            super.userEventTriggered(ctx,evt);
        }
    }

    /**
     * 全局处理消息接收异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error(" 发生异常 -> ", cause);
        if(cause instanceof TooLongFrameException) {
            // 无效数据帧异常
            if(!ctx.isRemoved()) {

                if(null != ctx) {
                    ctx.close();
                }
            }
        }


    }


    /**
     * 全局处理消息发送异常
     * @param ctx
     * @param msg
     * @param promise
     * @throws Exception
     */
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ctx.write(msg, promise.addListener((ChannelFutureListener) future -> {
            if (!future.isSuccess()) {
                // 数据发送失败时回调

            }
        }));
    }
}
