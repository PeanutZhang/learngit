package com.example.demo;

import java.util.Comparator;

/**
 * @Package: com.example.demo
 * @Description:
 * @Author: zyh
 * @CreateDate: 2020/4/15
 * @company: 上海若美科技有限公司
 */
public class User implements Comparable<User> {
    public String name;
    public int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }




    @Override
    public int compareTo(User u) {
        return this.age - u.age;
    }
}
