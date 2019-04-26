package com.example.demo;

import android.util.Log;

public class Child extends  Foo{

    public  Child(){
        Log.e("zyh","--child contr-> ");

    }
    @Override
    protected void dosth( String sss ) {
        Log.e("zyh","--child dosth-> "+ sss);
//        super.dosth(sss);
    }

    @Override
    public void disPalay() {
        super.disPalay();
    }
}
