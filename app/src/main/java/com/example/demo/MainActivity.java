package com.example.demo;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //全屏
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        //沉浸式状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //适配刘海屏
        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT;
//        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        getWindow().setAttributes(lp);
        LinearLayout linearLayout = findViewById(R.id.rootl);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) linearLayout.getLayoutParams();

       int statusBarHeight = getStatusHeight();
        Log.e("zyh","-barHeight- >  "+statusBarHeight);
        layoutParams.topMargin = statusBarHeight;
        linearLayout.setLayoutParams(layoutParams);
    }

    private int getStatusHeight() {
         int statusBarHeight = 0;
         int resourceId = getBaseContext().getResources().getIdentifier("status_bar_height","dimen","android");

         if(resourceId > 0){

            statusBarHeight  = getBaseContext().getResources().getDimensionPixelSize(resourceId);
         }

        return statusBarHeight;
    }
}
