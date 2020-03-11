package com.example.demo.aopdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.demo.R;

public class AopDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aop_demo);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @SecurityCheckAnnoation(declaredPermission = "android.permission.READ.PHONE.STATE")
    private void testmakePhone(){

    }

    public void test(View view) {
        Log.e("zyh","test inner click");
        int i = 0;
        testmakePhone();
//        int t= 1/i;
    }
}
