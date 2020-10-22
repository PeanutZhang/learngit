package com.example.demo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;

import com.example.demo.net.A;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.memory.PoolConfig;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.memory.PoolParams;

/**
 * @Package: com.example.demo
 * @Description:
 * @Author: zyh
 * @CreateDate: 2020/3/27
 * @company: 上海若美科技有限公司
 */
public class App extends Application {

    private static App mapp;
    private int mCount;
    String TAG = "zyh";
    @Override
    public void onCreate() {
        super.onCreate();
            mapp = this;


        Fresco.initialize(this,configPool(this));

            registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks(){

                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {


                }

                @Override
                public void onActivityStarted(Activity activity) {
                            mCount++;
                    Log.e(TAG, "onActivityStarted: "+mCount );
                }

                @Override
                public void onActivityResumed(Activity activity) {

                }

                @Override
                public void onActivityPaused(Activity activity) {

                }

                @Override
                public void onActivityStopped(Activity activity) {
                      mCount--;
                    Log.e(TAG, "onActivityStopped: count  "+mCount);
                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {

                }
            });

    }

    public static ImagePipelineConfig configPool(Context context){
        int	MaxRequestPerTime = 64;
        SparseIntArray defaultBuckets = new SparseIntArray();
        defaultBuckets.put(16 * ByteConstants.KB, MaxRequestPerTime);
        PoolParams smallByteArrayPoolParams = new PoolParams(
                16 * ByteConstants.KB * MaxRequestPerTime,
                2 * ByteConstants.MB,
                defaultBuckets);
        PoolFactory factory = new PoolFactory(
                PoolConfig.newBuilder()
                        . setSmallByteArrayPoolParams(smallByteArrayPoolParams)
                        .build());


        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(context)
                .setDownsampleEnabled(true)
                .setPoolFactory(factory)
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .build();
        Log.e("zyh","config:  "+imagePipelineConfig.getBitmapConfig().name());
        return imagePipelineConfig;
    }

  public static App getApp(){
        return mapp;
  }

  public int getCount(){
        return mCount;
  }

}
