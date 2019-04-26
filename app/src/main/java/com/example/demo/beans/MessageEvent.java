package com.example.demo.beans;

/**
 * @Package: com.example.demo
 * @Description:
 * @Author: zyh
 * @CreateDate: 2019/4/26
 * @company: 上海若美科技有限公司
 */
public class MessageEvent<T> {
    private int msgType;
    private T data;

    public MessageEvent(int msgType) {
        this.msgType = msgType;
    }
    public MessageEvent(int msgType,T data){
        this.msgType = msgType;
        this.data = data;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
