package com.netty.learn.second;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.*;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.util.Base64;

public class MySeverInitalizer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline  = ch.pipeline();

        ByteBuf delemiter= Unpooled.buffer();
        String cahr = "\r\n";
        byte[] bytesadas= cahr.getBytes();
        byte[] decryBytes = SocketByteHandler.encryptBuf(bytesadas);
        delemiter.writeBytes(decryBytes);
        pipeline.addLast(new DelimiterBasedFrameDecoder(10240,delemiter));
        // 进行长度字段解码，这里也会对数据进行粘包和拆包处理
//        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
//        // LengthFieldPrepender是一个编码器，主要是在响应字节数据前面添加字节长度字段
//        ch.pipeline().addLast(new LengthFieldPrepender(4));
        // 对经过粘包和拆包处理之后的数据进行json反序列化，从而得到User对象


        pipeline.addLast(new UavDecoder());
        pipeline.addLast(new UavEncoder());

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
