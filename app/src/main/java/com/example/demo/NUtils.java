package com.example.demo;

import android.util.Log;

/**
 * @Package: com.example.demo
 * @Description:
 * @Author: zyh
 * @CreateDate: 2019/11/25
 * @company: 上海若美科技有限公司
 */
public class NUtils {

   static {
       System.loadLibrary("key");
   }


   public native String getKey();
   public native String fuck();
   public static native String f();
   public native String test(String s);
   public native void set(int s);

   public  String jnicallJava(String msg){
      Log.e("zyh","c调用了我----");
     return "java传给c++的";
   }
}
