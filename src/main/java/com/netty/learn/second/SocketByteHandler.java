package com.netty.learn.second;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import struct.JavaStruct;

import java.util.Base64;


public class SocketByteHandler extends ChannelInboundHandlerAdapter {


    public final static Base64.Decoder mimeDecoder = Base64.getMimeDecoder();
    public final static Base64.Encoder mimeEncoder = Base64.getMimeEncoder();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        CommonMsg commonMsg = (CommonMsg) msg;
        System.out.println("客户端地址"+ctx.channel().remoteAddress());
        byte[] bytes = commonMsg.getData();
        String data = new String(bytes,"UTF-8");

        System.out.println("收到来着客户端的消息"+new String(mimeDecoder.decode(data)));

//        String abc = new String(bytes,"GB2312");
//        commonMsg.setData(abc.getBytes());
//        byte[] bytes1 = JavaStruct.pack(commonMsg);
//        System.out.println(bytes1.length);
        ctx.writeAndFlush(commonMsg);



//
//        ByteBuf result = (ByteBuf) msg;
//        byte[] result1 = new byte[result.readableBytes()];
//        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
//        result.readBytes(result1);
//
////        String resultStr = new String(result1);
////        System.out.println("Client said:" + resultStr);
//        byte[] debyte = decryptBuf(result1);
//        CommonMsg commonMsg = new CommonMsg();
//        JavaStruct.unpack(commonMsg,debyte);
////        System.out.println(">>>>>>>>>>>"+new String(debyte).trim());
//        String encodedText = new String(debyte);
//        System.out.println("base64"+new String(mimeDecoder.decode(encodedText), "UTF-8"));//解码


        // 释放资源，这行很关键
//        result.release();

//        String response = "I am ok!";
//        // 在当前场景下，发送的数据必须转换成ByteBuf数组
//        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
//        encoded.writeBytes(response.getBytes());
//        ctx.write(encoded);
//        ctx.flush();
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


    /**
     * 解密
     * @param src
     * @return
     */
    public  static  byte[] decryptBuf(byte[] src){
        byte[] decryBufArr = new byte[src.length];
        int pos = 0;
        for (; pos < src.length; pos++) {
            if (src[pos] >= 68)
            SocketByteHandler.transInt2Bytes(decryBufArr,pos,src[pos] - 68);
                //decryBufarr[pos] = SocketByteHandler.transInt2Bytes(de,src[pos] - 68);
            else
//                decryBufarr[pos] = src[pos] + 256 - 68;
                SocketByteHandler.transInt2Bytes(decryBufArr,pos,src[pos] + 256 - 68);
        }
        return decryBufArr;
    }

    /**
     * 加密
     * @param src
     * @return
     */
    public static byte[] encryptBuf(byte[] src){
        byte[] encryptBufArr = new byte[src.length];
        int pos = 0;
        for(;pos < src.length;pos++)
        {
            int data  = (src[pos] + 68) % 256;
            SocketByteHandler.transInt2Bytes(encryptBufArr,pos,data);
        }
        return encryptBufArr;
    }

    public static void main(String[] args) {

        String encodedText = mimeEncoder.encodeToString("abc".getBytes());//编码

        byte[] encryptBufArr = encryptBuf(encodedText.getBytes());

        byte[]  decryptBufArr =  decryptBuf(encryptBufArr);

        String decrryBaet64 =new String(decryptBufArr);
        System.out.println(new String(mimeDecoder.decode(decrryBaet64)));
        //System.out.println("base64"+new String(mimeDecoder.decode(decrryBaet64), "UTF-8"));//解码


    }
}


