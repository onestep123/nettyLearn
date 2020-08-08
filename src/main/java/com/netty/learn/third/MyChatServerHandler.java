package com.netty.learn.third;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            Channel channel = ctx.channel();

            channelGroup.forEach(ch -> {
                System.out.println(ch!=channel);
                if(ch!=channel){
                    System.out.println("服务器端转发聊天消息："+ ch.remoteAddress() + "发送的消息，内容：" + msg + "\n");
                    ch.writeAndFlush(ch.remoteAddress() + "发送的消息，内容：" + msg + "\n");
                }else{
                    System.out.println("服务器端转发聊天消息：【自己】发送的消息, 内容：" + msg + "\n");
                    ch.writeAndFlush("【自己】发送的消息, 内容：" + msg + "\n");
                }
            });
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("{服务器} -"+channel.remoteAddress()+" 加入\n");
        channelGroup.add(channel);
        System.out.println(channelGroup.size());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("{服务器} -"+channel.remoteAddress()+" 离开\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel =ctx.channel();
        System.out.println(channel.remoteAddress()+"上线 \n");

    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+"下线 \n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}