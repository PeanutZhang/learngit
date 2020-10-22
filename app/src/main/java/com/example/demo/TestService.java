package com.example.demo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service {
    public TestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
      return new U();
    }
    class U extends Binder{

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("zyh","oncreate---------");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("zyh","onstarccoman---------");

        return super.onStartCommand(intent, flags, startId);
    }
}
