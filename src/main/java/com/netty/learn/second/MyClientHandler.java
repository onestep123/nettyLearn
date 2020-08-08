package com.netty.learn.second;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

public class MyClientHandler extends ChannelInboundHandlerAdapter {

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


        //打印
//        String sendMsg ="{\n" +
//                "\"PacketType\":51,\n" +
//                "\"TaskTitle\":\"ABC.txt\",\n" +
//                "\"JobFlowId\":\"a1_20200708142514613\",\n" +
//                "\"SecurityLevel\":1,\n" +
//                "\"UsageDescr\":\"ceshi\",\n" +
//                "\"InstanceCount\":2,\t\t\t\t\t\t\n" +
//                "\"ApplierUsername\":\"fm\",\n" +
//                "\"ApplyTaskTime\":\"2020-07-08 14:25:34\",\t\n" +
//                "\"PageType\":1,\t\t\t\t\t\t\t\t\n" +
//                "\"PageCount\":1,\n" +
//                "\"PageMode\":\"A4\",\n" +
//                "\"PrintBarCodeFlag\":1,\n" +
//                "\"PrintWaterMarkFlag\":0,\n" +
//                "\"RefDeviceID\":10,\t\t\t\t\t\n" +
//                "\"ApproverName\":\"boss\",\n" +
//                "\"DeviceId\":\"0E40A05D36C7\",\n" +
//                "\"IsMBFile\":0\n" +
//                "}";

        String sendMsg = "{\n" +
                "\"PacketType\":54,\n" +
                "\"TaskTitle\":\"123\",\n" +
                "\"JobFlowId\":\"sqr_20200707150157151\",\n" +
                "\"SecurityLevel\":0,\n" +
                "\"UsageDescr\":\"qwerqwer\",\n" +
                "\"InstanceCount\":1,\n" +
                "\"MediaFaceFlag\":2,\n" +
                "\"ApplierUsername\":\"fm\",\n" +
                "\"ApplyTaskTime\":\"2020-07-07 15:02:16\",\n" +
                "\"IpAddress\":\"192.168.0.25\",\n" +
                "\"DeviceId\":\"0E40A05D36C7\",\n" +
                "\"RefDeviceID\":\"1\",\n" +
                "\"Approver\":\"boss\",\n" +
                "\"IsMBFile\":0,\n" +
                "\"RemoveLabel\":0\n" +
                "}";
        commonMsg.setiDesProductID(8);
        byte[] sendByte = mimeEncoder.encode(sendMsg.getBytes());
        commonMsg.setData(sendByte);
        commonMsg.setiLength(sendByte.length);

        ctx.writeAndFlush(commonMsg);
//        ctx.writeAndFlush("from client"+ LocalDateTime.now());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("申请人用户登录");
        String msg ="{\n" +
                "\"PacketType\":1,\n" +
                "\"UserName\":\"fm\",\n" +
                "\"UserPwd\":\"Aa12345678\",\n" +
                "\"LoginType\":1,\n" +
                "\"DeviceId\":\"0E40A05D36C7\",\n" +
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
}
