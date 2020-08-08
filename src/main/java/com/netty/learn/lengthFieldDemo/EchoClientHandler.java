package com.netty.learn.lengthFieldDemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EchoClientHandler  extends SimpleChannelInboundHandler<User> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, User msg) throws Exception {
        System.out.println("receive message from server: " + msg);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.write(getUser());
    }

    private User getUser() {
        User user = new User();
        user.setAge(27);
        user.setName("zhangxufeng");
        return user;
    }
}
