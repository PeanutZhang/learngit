package com.example.demo.broadrecieve;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.demo.VpActivity;
import com.example.demo.utils.LinNotify;

/**
 * @Package: com.example.demo.broadrecieve
 * @Description:
 * @Author: zyh
 * @CreateDate: 2020/4/23
 * @company: 上海若美科技有限公司
 */
public class TestBroadcastReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                LinNotify.show(context,"广播中测试","测试内容", VpActivity.class);
            }
        }).start();
        Log.e("zyh","------onrecivev---");
    }
}
