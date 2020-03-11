package com.example.demo;

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

    public static void main(String args[]) {
        String str = "http://wx.nanyibang.com/mall/item/2803?sdf&&&&";
        String pattern = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }


}
