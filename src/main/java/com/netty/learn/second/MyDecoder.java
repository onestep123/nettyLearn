//package com.netty.learn.second;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.ByteToMessageDecoder;
//
//import java.util.List;
//
//public class MyDecoder extends ByteToMessageDecoder {
//
//    /**
//     * 报文开始的标志 header是int类型，占4个字节
//     * 表示报文长度的contentLength是int类型，占4个字节
//     */
//    public final int BASE_LENGTH = 8;
//
//    @Override
//    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//
//            int protocolNo = in.readInt();
//            byte[] result1 = new byte[in.readableBytes()];
//        PooledByteBuf
//
//    //        byte[] content = new byte[in.w];
//    //
//    //        byte[] content = new byte[contentLength];
//    //        byteBuf.readBytes(content);
//
//    //        MyProtocol protocol = new MyProtocol(protocolNo, contentLength, content);
//    //        list.add(protocol);
//    }
//}
