package com.netty.learn.ssl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.net.InetSocketAddress;
import java.nio.channels.ClosedChannelException;

public class SocketHelper {


    public static ChannelFuture writeMessage(Channel channel, String msg) {
        if(channel!=null){
            try {
                return channel.writeAndFlush(msg).sync();
            } catch (Exception e) {
                String otherInfo = "";

                if(channel.remoteAddress() != null) {
                    otherInfo = "remote address [" + ((InetSocketAddress)channel.remoteAddress()).toString() + "]"+e.getMessage();
                } else {
                    otherInfo = "channel is null.";
                }

                if(e instanceof ClosedChannelException) {
                    System.out.println("channel to " + otherInfo + " is closed");
                } else {
                    System.out.println("timeout occured during channel send msg, " + otherInfo+e.getMessage());
                }
            }
        }else{
            System.out.println("send msg failed, channel is disconnected or not connect. channel is null, please see caller log.");
        }
        return null;
    }

    public static ChannelFuture writeMessage(Channel channel, ByteBuf msg) {
        if(channel!=null){
            try {
                return channel.writeAndFlush(msg).sync();
            } catch (Exception e) {
                System.out.println("timeout occured during channel send msg. remote address is:" + ((InetSocketAddress)channel.remoteAddress()).toString()+e.getMessage());
            }
        }else{
            System.out.println("send msg failed, channel is disconnected or not connect, channel is null, please see caller log.");
        }
        return null;
    }
}
