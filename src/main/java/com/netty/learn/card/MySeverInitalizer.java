package com.netty.learn.card;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.util.Base64;

public class MySeverInitalizer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline  = ch.pipeline();
        pipeline.addLast(new Decoder());
        pipeline.addLast(new SocketByteHandler());
    }

    public static void main(String[] args) {

        String a = "}";
        String b = new String(Base64.getMimeEncoder().encode(a.getBytes()));
        System.out.println(b);
        String c = new String(Base64.getMimeDecoder().decode(b));
        System.out.println(c);
    }
}
