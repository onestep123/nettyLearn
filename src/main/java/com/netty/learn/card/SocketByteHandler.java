package com.netty.learn.card;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class SocketByteHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        CardStruct cardStruct = (CardStruct) msg;
        System.out.println("客户端地址"+ctx.channel().remoteAddress());

    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelReadComplete(ctx);
        ctx.flush();
    }

    public static int transInt2Bytes(byte[] bytedata, int pos, int Data) {
        bytedata[pos] = (byte) (0xff & (Data >> 24));
        bytedata[pos] = (byte) (0xff & (Data >> 16));
        bytedata[pos] = (byte) (0xff & (Data >> 8));
        bytedata[pos] = (byte) (0xff & Data);
        return pos;
    }

}


