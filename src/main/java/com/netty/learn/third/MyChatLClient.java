package com.netty.learn.third;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;

public class MyChatLClient {

    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyChatClientInitializer());
            Channel channel = bootstrap.connect("localhost",8899).sync().channel();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String a = "}";
            String b = new String(Base64.getMimeEncoder().encode(a.getBytes()));
            System.out.println(b);
            for (; ;){
//                channel.writeAndFlush(br.readLine()+"\r\n"+"asdasd");
//                channel.writeAndFlush(br.readLine()+"}"+"asdasd");
                channel.writeAndFlush(br.readLine()+b+"asdasd");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
