package com.netty.learn.lengthFieldDemo;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class EchoServerHandler  extends SimpleChannelInboundHandler<User> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, User msg) throws Exception {
        System.out.println("receive from client: " + msg);
        ctx.write(msg);
    }
}
