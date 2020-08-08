package com.netty.learn.second;


public class MyLsProtocol {


    private int sockFd;

    private CommonMsg commonMsg;

    public CommonMsg getCommonMsg() {
        return commonMsg;
    }

    public void setCommonMsg(CommonMsg commonMsg) {
        this.commonMsg = commonMsg;
    }

    public int getSockFd() {
        return sockFd;
    }

    public void setSockFd(int sockFd) {
        this.sockFd = sockFd;
    }

    public MyLsProtocol(int sockFd, CommonMsg commonMsg) {
        this.sockFd = sockFd;
        this.commonMsg = commonMsg;
    }
}
