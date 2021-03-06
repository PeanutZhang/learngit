package com.example.demo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.InputEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.demo.activitys.CalendarDemoActivity;
import com.example.demo.activitys.ConstrainsActivity;
import com.example.demo.activitys.RxActivity;
import com.example.demo.activitys.SocketActivity;
import com.example.demo.aopdemo.AopDemoActivity;
import com.example.demo.audio_video.MediaPlayerDemoActivity;
import com.example.demo.beans.MessageEvent;
import com.example.demo.beans.Test;
import com.example.demo.views.loadingview.LoadingView;
import com.umeng.message.PushAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

@BindEventBus
public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushAgent f = PushAgent.getInstance(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //全屏
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        //沉浸式状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        if(this.getClass().isAnnotationPresent(BindEventBus.class)){
//            EventBus.getDefault().register(this);
            Log.e("zyh","mani is annotation present");
        }


        //适配刘海屏
        WindowManager.LayoutParams lp = getWindow().getAttributes();

//        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT;
//        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        getWindow().setAttributes(lp);
        LinearLayout linearLayout = findViewById(R.id.rootl);
        ScrollView.LayoutParams layoutParams = (ScrollView.LayoutParams) linearLayout.getLayoutParams();


       int statusBarHeight = getStatusHeight();
        Log.e("zyh","-barHeight- >  "+statusBarHeight);
        layoutParams.topMargin = statusBarHeight;
        linearLayout.setLayoutParams(layoutParams);
        findViewById(R.id.btss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PictureActivity.class));
            }
        });
        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Child foo = new Child();
                foo.disPalay();
            }
        });
        ImageView imageView = findViewById(R.id.iv);
        LoadingView loadingView = findViewById(R.id.load);
        findViewById(R.id.bt3).setOnClickListener(v -> {
            Log.e("zyh","---onclcikBt3 》》");
//           loadingView.show();
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.longimg);
//            imageView.setImageBitmap(bitmap);
          startActivity(new Intent(MainActivity.this,VpActivity.class));

        });
        findViewById(R.id.bt5).setOnClickListener(v->startActivity(new Intent(MainActivity.this,VpActivity.class)));
        findViewById(R.id.bt6).setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this, ConstrainsActivity.class));

        });
        findViewById(R.id.bt7).setOnClickListener(v->startActivity(new Intent(MainActivity.this, AopDemoActivity.class)));
        NUtils nUtils = new NUtils();
        Log.e("zyh","-----> " +NUtils.f());
        findViewById(R.id.bt8).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SocketActivity.class)));
        findViewById(R.id.bt9).setOnClickListener(v->startActivity(new Intent(MainActivity.this, RxActivity.class)));
        findViewById(R.id.bt10).setOnClickListener(v->setComponetNameDefault());
        findViewById(R.id.bt11).setOnClickListener(v->setComponetNameFestival());
        findViewById(R.id.bt12).setOnClickListener(v->{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            getWindow().setWindowAnimations(R.style.windowAnimationFadeInOut);
            recreate();
        });
        findViewById(R.id.bt13).setOnClickListener(v->{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            getWindow().setWindowAnimations(R.style.windowAnimationFadeInOut);
            recreate();
        });
        findViewById(R.id.btn15).setOnClickListener(v->startActivity(new Intent(this,MediaPlayerDemoActivity.class)));
        findViewById(R.id.btn15).setOnClickListener(v -> startActivity(new Intent(this, CalendarDemoActivity.class)));
    }

    private int getStatusHeight() {
         int statusBarHeight = 0;
         int resourceId = getBaseContext().getResources().getIdentifier("status_bar_height","dimen","android");

         if(resourceId > 0){

            statusBarHeight  = getBaseContext().getResources().getDimensionPixelSize(resourceId);
         }

        return statusBarHeight;
    }


    private void setComponetNameDefault(){
        Log.e("zyh","default");
        PackageManager packageManager = getPackageManager();
        ComponentName cnmDefault = new ComponentName(getPackageName(),"com.example.demo.MainActivity");
        ComponentName festCnm = new  ComponentName(getPackageName(),"com.example.demo.FestivalActivity");
        packageManager.setComponentEnabledSetting(festCnm,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
        packageManager.setComponentEnabledSetting(cnmDefault,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);
    }
    private void setComponetNameFestival(){
        Log.e("zyh","festival");
        PackageManager packageManager = getPackageManager();
        ComponentName cnmDefault = new ComponentName(getPackageName(),"com.example.demo.MainActivity");
        Log.e("zyh", "setComponetNameFestival: "+getPackageName());
        ComponentName festCnm = new ComponentName(getPackageName(),"com.example.demo.FestivalActivity");
        packageManager.setComponentEnabledSetting(cnmDefault,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
        packageManager.setComponentEnabledSetting(festCnm,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    void onMessgageEvent(MessageEvent<Test> event){
        Log.e("zyh","onmessage--->"+event.getData().name);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky =  true)
    void onMessageStickEvent(MessageEvent event){
        Log.e("zyh","onStickMessageEvent--> "+((Test)event.getData()).name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
     if(this.getClass().isAnnotationPresent(BindEventBus.class)){
         EventBus.getDefault().unregister(this);
     }
    }
}
