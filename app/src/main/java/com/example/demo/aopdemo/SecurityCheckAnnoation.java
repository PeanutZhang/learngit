package com.example.demo.aopdemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Package: com.example.demo.aopdemo
 * @Description:
 * @Author: zyh
 * @CreateDate: 2020/3/3
 * @company: 上海若美科技有限公司
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityCheckAnnoation {

     String declaredPermission();

}
