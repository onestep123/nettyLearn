package com.netty.learn.card;

import java.io.IOException;
import java.net.*;

public class UdpBroadcastClient {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // TODO Auto-generated method stub
        // 广播的实现 :由客户端发出广播，服务器端接收
//        String host = "255.255.255.255";// 广播地址
//        int port = 9999;// 广播的目的端口

        String host = "127.0.0.1";// 广播地址
        int port = 8899;// 广播的目的端口
        String message = "test";// 用于发送的字符串
        try {
            InetAddress adds = InetAddress.getByName(host);
            DatagramSocket ds = new DatagramSocket();
            DatagramPacket dp = new DatagramPacket(message.getBytes(),
                    message.length(), adds, port);
            while (true) {
                ds.send(dp);
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
