package com.netty.learn.ssl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


public class SpiderServerBusiHandler  extends SimpleChannelInboundHandler<Object> {

    private boolean isClient;

    public SpiderServerBusiHandler(boolean isClient) {
        this.isClient = isClient;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
        if(isClient){
            ctx.writeAndFlush(" 来自客户端的问候！".getBytes());
        }else{
            System.out.println("channel active");
        }
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg.toString());
    }
}
