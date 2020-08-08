package com.netty.learn.four;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class MySeverInitalizer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline= ch.pipeline();

        pipeline.addLast(new IdleStateHandler(5, 7,10));
        pipeline.addLast(new MyServerHandler());
    }
}
