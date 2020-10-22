package com.example.demo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.example.demo.utils.LinNotify;

import static android.support.v4.app.NotificationCompat.PRIORITY_DEFAULT;

public class DemoService extends Service {

    private NotificationManager manager;

    public DemoService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e("zyh", "Demoservice  onbind---------");

        return new CountBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("zyh", "Demoservice  oncreate---------");

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel("lock", "lock", NotificationManager.IMPORTANCE_LOW);
//            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            if (manager == null)
//                return;
//            manager.createNotificationChannel(channel);
//            //此处的channelId必须和上面NotificationChannel设置的id一致
//            Notification notification = new NotificationCompat.Builder(this, "lock")
//                    .setAutoCancel(true)
//                    .setCategory(Notification.CATEGORY_SERVICE)
//                    .setOngoing(true)
//                    .setPriority(NotificationManager.IMPORTANCE_LOW)
//                    .build();
//            //注意 id不能为0
//            startForeground(107, notification);
//
//        }
    }

    private void remove() {

        manager.cancel(107);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("zyh", "Demoservice  onstartCommand---------");

        return super.onStartCommand(intent, flags, startId);
    }

    public class CountBinder extends Binder {
        public DemoService getDemoService() {
            return DemoService.this;
        }
    }


    public int getCount() {
        return App.getApp().getCount();
    }

    int i = 1;

    public void showNotifiction() {
//        LinNotify.setNotificationChannel(this);
        LinNotify.show(this, "标题", "服务中发送通知" + i++, null);
        Log.e("zyh", "----showNotification----");
    }

    public void clearNotification(){
        LinNotify.clearNotification(this);
    }
}
