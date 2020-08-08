package com.netty.learn.card;

import com.sun.jna.Structure;
import lombok.Data;
import struct.StructClass;
import struct.StructField;

import java.io.Serializable;


@Data
@StructClass
public class CardStruct extends Structure  implements Serializable {

    @StructField(order = 0)
    public char 	szFlagA;      // ?
    @StructField(order = 1)
    public char	szFlagB;      // ?
    @StructField(order = 2)
    public char	szCmd;        // 1: 接收到刷卡时该字段为1.
    @StructField(order = 3)
    public long	dwCardNum;;
    @StructField(order = 4)
    public char	szUserName;

    public CardStruct() {
//        super(ALIGN_NONE);
    }

    public char getSzFlagA() {
        return szFlagA;
    }

    public void setSzFlagA(char szFlagA) {
        this.szFlagA = szFlagA;
    }

    public char getSzFlagB() {
        return szFlagB;
    }

    public void setSzFlagB(char szFlagB) {
        this.szFlagB = szFlagB;
    }

    public char getSzCmd() {
        return szCmd;
    }

    public void setSzCmd(char szCmd) {
        this.szCmd = szCmd;
    }

    public long getDwCardNum() {
        return dwCardNum;
    }

    public void setDwCardNum(long dwCardNum) {
        this.dwCardNum = dwCardNum;
    }

    public char getSzUserName() {
        return szUserName;
    }

    public void setSzUserName(char szUserName) {
        this.szUserName = szUserName;
    }


}
