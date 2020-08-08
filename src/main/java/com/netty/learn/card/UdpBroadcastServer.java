package com.netty.learn.card;

import struct.JavaStruct;
import struct.StructException;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.Charset;

public class UdpBroadcastServer {


    /**
     * @param args
     */
    public static void main(String[] args) {

        // TODO Auto-generated method stub
        int port = 8899;// 开启监听的端口
        DatagramSocket ds = null;
        DatagramPacket dp = null;
        byte[] buf = new byte[71];// 存储发来的消息
        try {
            // 绑定端口的
            ds = new DatagramSocket(port);
            dp = new DatagramPacket(buf, buf.length);
            System.out.println("监听广播端口打开：");
            while (true) {
                ds.receive(dp);
//                dp.getData();
//                int i;
//                StringBuffer sbuf = new StringBuffer();
//                for (i = 0; i < 71; i++) {
//                    if (buf[i] == 0) {
//                        break;
//                    }
//                    sbuf.append((char) buf[i]);
//                }

                System.out.println("收到广播消息：" + dp.getData().length);
                CardStruct cardStruct = new CardStruct() ;
//                byte[]  bytes = new byte[7];
//                for(int  i=0;i<7;i++){
//                    bytes[i]= dp.getData()[i];
//                }
                //15178935
                //1663817
              JavaStruct.unpack(cardStruct,dp.getData());
                parseFrame(dp.getData());
                System.out.println("收到广播消息：" + cardStruct.getDwCardNum());
//                try {
//                    Thread.sleep(1000);
//                }
//                catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
            }
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (StructException e) {
            e.printStackTrace();
        }
    }

    public static void parseFrame(byte[] bytes){
        InputStream in_withcode;
        try {
            in_withcode = new ByteArrayInputStream(bytes);
            DataInputStream inputStream  =  new DataInputStream(in_withcode);
            char szFlagA='1';
            String  szFlagB="",szCmd= "";
            long nodeID=0l;
//            byte[] bytes1  = new byte[1];
//            String a  =new String(bytes,"ASCII");
//            System.out.println(a);
//            szFlagA = inputStream.readChar();//readChar()读取一个1个字节。
//            System.out.println(szFlagA);
//            szFlagB = inputStream.readChar();//readChar()读取一个1个字节。
//            System.out.println(szFlagB);
//            szCmd = inputStream.readChar();//readChar()读取一个1个字节。
//            System.out.println(szCmd);
            inputStream.skipBytes(3);
            nodeID = inputStream.readInt();
            // 4279788361
            System.out.println("kahao"+nodeID);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
