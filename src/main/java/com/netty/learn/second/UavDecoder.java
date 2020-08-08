package com.netty.learn.second;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import struct.JavaStruct;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteOrder;
import java.util.List;

public class UavDecoder extends ByteToMessageDecoder {


    public static int  num = 16777216;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);

        ByteBuf delemiter= Unpooled.buffer();
        delemiter.writeBytes("}".getBytes());
        byte[] bytesadas= new byte[delemiter.readableBytes()];
        delemiter.readBytes(bytesadas);
        System.out.println(bytesadas.length);

        byte[] decryBytes = SocketByteHandler.decryptBuf(bytes);

        // Unpack it into an object
        CommonMsg commonMsg = new CommonMsg();
//        JavaStruct.unpack(commonMsg,bytes);
        JavaStruct.unpack(commonMsg,decryBytes);
        out.add(commonMsg);

    }


    //反序列化为Object
    public static Object deserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("bytes Could not deserialize:" + e.toString());
            return null;
        } finally {
            try {
                if (bais != null) {
                    bais.close();
                }
            } catch (IOException ex) {
                System.out.println("LogManage Could not serialize:" + ex.toString());
            }
        }
    }

    public static void main(String[] args) {


    }
}