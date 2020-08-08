package com.netty.learn.second;

import struct.ArrayLengthMarker;
import struct.StructClass;
import struct.StructField;

import java.io.Serializable;


@StructClass
public class CommonMsg implements Serializable {


    private static final long serialVersionUID = 332414546804605465L;

//    @StructField(order = 0)
//    public  int  iSrcType;
//    @StructField(order = 1)
//    public  int  iSrcProductID;
//    @StructField(order = 2)
//    public  int  iSrcBusinessID;
//    @StructField(order = 3)
//    public  int  iDesType;
    @StructField(order = 0)
    public  int  iDesProductID;
//    @StructField(order = 5)
//    public  int  iDesBussinessID;
//    @StructField(order = 6)
//    public  int  iSocketID;
//    @StructField(order = 7)
//    public  int  iMessageID;
//    @StructField(order = 8)
//    public  int  iSerialNO;

    @StructField(order = 1)
    @ArrayLengthMarker(fieldName = "data")
    public  int  iLength;

    @StructField(order = 2)
//    public byte[] data = new byte[2048];
    public byte[] data;


    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


//    public int getiSrcType() {
//        return iSrcType;
//    }
//
//    public void setiSrcType(int iSrcType) {
//        this.iSrcType = iSrcType;
//    }
//
//    public int getiSrcProductID() {
//        return iSrcProductID;
//    }
//
//    public void setiSrcProductID(int iSrcProductID) {
//        this.iSrcProductID = iSrcProductID;
//    }
//
//    public int getiSrcBusinessID() {
//        return iSrcBusinessID;
//    }
//
//    public void setiSrcBusinessID(int iSrcBusinessID) {
//        this.iSrcBusinessID = iSrcBusinessID;
//    }
//
//    public int getiDesType() {
//        return iDesType;
//    }
//
//    public void setiDesType(int iDesType) {
//        this.iDesType = iDesType;
//    }

    public int getiDesProductID() {
        return iDesProductID;
    }

    public void setiDesProductID(int iDesProductID) {
        this.iDesProductID = iDesProductID;
    }

//    public int getiDesBussinessID() {
//        return iDesBussinessID;
//    }
//
//    public void setiDesBussinessID(int iDesBussinessID) {
//        this.iDesBussinessID = iDesBussinessID;
//    }
//
//    public int getiSocketID() {
//        return iSocketID;
//    }
//
//    public void setiSocketID(int iSocketID) {
//        this.iSocketID = iSocketID;
//    }
//
//    public int getiMessageID() {
//        return iMessageID;
//    }
//
//    public void setiMessageID(int iMessageID) {
//        this.iMessageID = iMessageID;
//    }
//
//    public int getiSerialNO() {
//        return iSerialNO;
//    }
//
//    public void setiSerialNO(int iSerialNO) {
//        this.iSerialNO = iSerialNO;
//    }

    public int getiLength() {
        return iLength;
    }

    public void setiLength(int iLength) {
        this.iLength = iLength;
    }

    public CommonMsg() {
    }


}
