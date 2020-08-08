package com.netty.learn.lengthFieldDemo;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class JsonDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        System.out.println(System.currentTimeMillis()+"Start");
        User user = JSON.parseObject(new String(bytes, CharsetUtil.UTF_8), User.class);
        System.out.println(System.currentTimeMillis()+"end");
        out.add(user);
    }
}
