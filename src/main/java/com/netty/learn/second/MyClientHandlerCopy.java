package com.netty.learn.second;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Base64;
import java.util.Date;

public class MyClientHandlerCopy extends ChannelInboundHandlerAdapter {

    public final static Base64.Decoder mimeDecoder = Base64.getMimeDecoder();
    public final static Base64.Encoder mimeEncoder = Base64.getMimeEncoder();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress());

        System.out.println("client output"+msg);
        CommonMsg commonMsg = (CommonMsg) msg;
//        System.out.println("客户端地址"+ctx.channel().remoteAddress());
        byte[] bytes = commonMsg.getData();
        String data = new String(bytes,"UTF-8");

        System.out.println("收到来着服务端的消息"+new String(mimeDecoder.decode(data)));
        System.out.println(new Date().getSeconds());
//        Thread.sleep(20);


//        ctx.writeAndFlush("from client"+ LocalDateTime.now());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("审批人用户登录");
        String msg ="{\n" +
                "\"PacketType\":1,\n" +
                "\"UserName\":\"boss\",\n" +
                "\"UserPwd\":\"Aa12345678\",\n" +
                "\"LoginType\":1,\n" +
                "\"DeviceId\":\"0E40A05D36C7shenqing\",\n" +
                "\"MacAddress\":\"0E:40:A0:5D:36:C7\",\n" +
                "\"IpAddress\":\"192.168.0.25\",\n" +
                "\"HostName\":\"Kylin\"\n" +
                "}";
//        String msg ="{\n" +
//                "\"PacketType\":3,\n" +
//                "\"UserName\":\"fm\",\n" +
//                "\"DeviceId\":\"0E40A05D36C7\",\n" +
//                "\"IpAddress\":\"192.168.0.25\",\n" +
//                "\"HostName\":\"Kylin\",\n" +
//                "\"MacAddress\":\"0E:40:A0:5D:36:C7\"\n" +
//                "}";
//        String msg = "{\n" +
//                "\"PacketType\":5,\n" +
//                "\"UserName\":\"fm\",\n" +
//                "\"UserPwd\":\"Aa12345678\",\n" +
//                "\"DeviceId\":\"0E40A05D36C7\",\n" +
//                "\"IpAddress\":\"192.168.0.25\",\n" +
//                "\"HostName\":\"Kylin\",\n" +
//                "\"MacAddress\":\"0E:40:A0:5D:36:C7\",\n" +
//                "\"UserAliveTime\":\"2020-07-08 11:44:06\"\n" +
//                "}";
        CommonMsg commonMsg = new CommonMsg();
        commonMsg.setiDesProductID(1);
        byte[] bytess =mimeEncoder.encode(msg.getBytes());
        System.out.println(new String(mimeDecoder.decode(bytess)));
        commonMsg.setiLength(bytess.length);
        commonMsg.setData(bytess);
        ctx.writeAndFlush(commonMsg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date().getTime());
        super.channelUnregistered(ctx);
    }
}
