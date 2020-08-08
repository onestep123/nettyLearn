package com.netty.learn.card;

import com.netty.learn.second.CommonMsg;
import com.netty.learn.second.SocketByteHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import struct.JavaStruct;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class Decoder extends ByteToMessageDecoder {



    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);
        CommonMsg commonMsg = new CommonMsg();
        JavaStruct.unpack(commonMsg,bytes);
        out.add(commonMsg);
    }



}