package com.example.demo;


import com.example.demo.beans.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @Package: com.nanyibang.rm.utils
 * @Description:
 * @Author: zyh
 * @CreateDate: 2019/4/26
 * @company: 上海若美科技有限公司
 */
public class EventBusUtil {

    public static void register(Object subscriber ){
        EventBus.getDefault().register(subscriber);
    }
    public static void unRegister(Object subscriber){
        EventBus.getDefault().unregister(subscriber);
    }
    public static void postEvent(int msgType,Object obj){
        MessageEvent event = new MessageEvent(msgType,obj);
        EventBus.getDefault().post(event);
    }
    public static void postStickEvent(int msgType,Object obj){
        MessageEvent event = new MessageEvent(msgType,obj);
        EventBus.getDefault().postSticky(event);
    }

}
