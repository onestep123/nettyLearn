package com.netty.learn.third;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.util.Base64;

public class MyChatServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        ByteBuf delemiter= Unpooled.buffer();
//        delemiter.writeBytes("}".getBytes());
        String a = "}";
        String b = new String(Base64.getMimeEncoder().encode(a.getBytes()));
        System.out.println(b);
        delemiter.writeBytes(b.getBytes());
//        pipeline.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
        pipeline.addLast(new DelimiterBasedFrameDecoder(4096,delemiter));
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8)); // 添加字符串解码器
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8)); // 添加字符串编码器
        pipeline.addLast(new MyChatServerHandler());
    }
}
