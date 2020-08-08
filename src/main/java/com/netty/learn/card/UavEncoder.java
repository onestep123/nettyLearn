package com.netty.learn.card;

import com.netty.learn.second.CommonMsg;
import com.netty.learn.second.SocketByteHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import struct.JavaStruct;


public class UavEncoder extends MessageToByteEncoder<CommonMsg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, CommonMsg msg, ByteBuf out) throws Exception {
//        byte[] datas = ByteObjConverter.ObjectToByte(msg);
        String cahr = "&&";
        byte[] bytesadas= cahr.getBytes();
        byte[] decryBytes = SocketByteHandler.encryptBuf(bytesadas);

        byte[] bytes = JavaStruct.pack(msg);

        byte[] emcodeBytes = SocketByteHandler.encryptBuf(bytes);
//        delemiter.writeBytes(decryBytes);
        byte[] bytes1 = addBytes(emcodeBytes,decryBytes);
        out.writeBytes(bytes1);
        ctx.flush();
    }

    /**
     *
     * @param data1
     * @param data2
     * @return data1 与 data2拼接的结果
     */
    public static byte[] addBytes(byte[] data1, byte[] data2) {
        byte[] data3 = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;

    }

}
