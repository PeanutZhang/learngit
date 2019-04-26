package com.example.demo;

import android.util.Log;

public class Foo {

    public Foo() {


Log.e("zyh","foo create--");
    }

    public void disPalay(){
      this.dosth("foo 传的信息");
      Log.e("zyh","this ---> "+this.getClass().getName());
  }

    protected void dosth(String  sss) {
        Log.e("zyh","Foo dosth "+ sss);
    }


}
