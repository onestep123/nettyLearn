package com.netty.learn.second;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
        System.out.println(msg.getClass());
        System.out.println(msg);
//        byte[] bytes = msg.getBytes();
//
//        System.out.println(bytes);
//        int[] decryByte = decryptBuf(bytes);
//
//        System.out.println(decryByte);
//        String str = new String((byte[]) decryByte, CharsetUtil.UTF_8);

//        String abc = new String(String.valueOf(msg));
//        System.out.println(abc);
//        try {
//            ByteBuf buf = (ByteBuf) msg;
//            //创建目标大小的数组
//            byte[] barray = new byte[buf.readableBytes()];
//            //把数据从bytebuf转移到byte[]
//            buf.getBytes(0, barray);
//            //将byte[]转成字符串用于打印
//            String str = new String(barray, CharsetUtil.UTF_8);
//
//            if (str.length() > 0) {
//                System.out.println(str);
//                System.out.flush();
//            } else {
//                System.out.println("不能读啊");
//            }
//            buf.release();
//        } finally {
//            //buf.release();
//        }

        ctx.channel().writeAndFlush("from server" + UUID.randomUUID());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }







}
