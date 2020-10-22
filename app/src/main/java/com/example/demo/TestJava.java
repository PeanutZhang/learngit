package com.example.demo;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.demo.beans.Address;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Package: com.example.demo
 * @Description:
 * @Author: zyh
 * @CreateDate: 2019/8/23
 * @company: 上海若美科技有限公司
 */
public class TestJava {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String args[]) {
        String str = "http://wx.nanyibang.com/mall/item/2803?sdf&&&&";
        String pattern = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());

        int x = 344;
        int y = 23330;

        calcuMaxDivisor(x,y);
        System.out.println("percent x-> "+(float)x/(x+y)+"  percent y-->   "+(float)y/(x+y)+"  x/y = " +(float)x/y);


        String uuid_Random = UUID.randomUUID().toString().replace("-","");
        System.out.println("uuid---> "+uuid_Random);
        // 0ac1d8ee-603a-441e-b0ed-99e532fdab94

        ArrayList<User> list = new ArrayList<>();
        User u1 = new User("Anita",28);
        User u2 = new User("杨紫琼",26);
        User u3 = new User("张曼玉",27);
        list.add(u1);
        list.add(u2);
        list.add(u3);
        HashMap<String,User> userHashMap = new HashMap<>();
        userHashMap.put(u1.name,u1);
        userHashMap.put(u2.name,u2);
        userHashMap.put(u3.name,u3);

        User anita = userHashMap.get("Anita");
        anita.age = 29;
        System.out.println("Anita:-->  "+list.get(0).age);
        Collections.sort(list);
        for (User u:list) {
            System.out.println(String.format("age: %1$d",u.age));
        }
    list.stream().toArray(User[]::new);

        String jstr = "\"{\"name\":\"张三\",\"contact\":\"020-81167888\",\"postal\":\"510000\",\"province\":\"广东省\",\"city\":\"广州市\",\"district\":\"海珠区\",\"addressDetail\":\"新港中路397号\",\"address\":\"广东省 广州市 海珠区 新港中路397号\"}\"";
        try {
            String substring = jstr.substring(1, jstr.length() - 1);
            Gson gson = new Gson();
         Address a = gson.fromJson(substring,new TypeToken<Address>(){}.getType());
         System.out.println(a.name);
         System.out.println(jstr);
//            JSONObject jsonObject = new JSONObject(substring);
//            boolean isnull = jsonObject == null;
//          String  name = jsonObject.optString("name");
//          System.out.println("isnull:  "+isnull);
//         System.out.println(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String a = "GIF87a";
        try {
            System.out.println("GIF87a==> "+a.getBytes("ASCII"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


    private static void calcuMaxDivisor(int m, int n){

        if(m == 0 || n == 0){
            return;
        }
        int remained = m % n;
        while ( remained != 0 ){
            m = n;
            n = remained;
            remained = m % n;
        }

       System.out.println("max Divisor--  "+n);

    }



}
