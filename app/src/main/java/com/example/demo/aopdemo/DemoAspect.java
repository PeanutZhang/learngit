package com.example.demo.aopdemo;

import android.os.SystemClock;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


/**
 * @Package: com.example.demo.aopdemo
 * @Description:
 * @Author: zyh
 * @CreateDate: 2020/3/3
 * @company: 上海若美科技有限公司
 */


@Aspect
public class DemoAspect {

    public static final String TAG ="zyh";

    //execution(<修饰符模式>? <返回类型模式> <方法名模式>(<参数模式>) <异常模式>?)

    // execution(<@注解类型模式>? <修饰符模式>? <返回类型模式> <方法名模式>(<参数模式>) <异常模式>?)

   // 除了返回类型模式、方法名模式和参数模式外，其它项都是可选的。


    @Pointcut("execution(* com.example.demo.aopdemo.AopDemoActivity.test(..))")
    public void test(){}



    @Before("test()")
    public void before(JoinPoint joinPoint){
        Log.e(TAG, "before: test()" );
    }

    @Around("test()")
    public void log(ProceedingJoinPoint point) throws Throwable {
        Log.e("zyh","before onclick around");
        long beginTime = SystemClock.currentThreadTimeMillis();
        point.proceed();
        long endTime = SystemClock.currentThreadTimeMillis();
        long dx = endTime - beginTime;
        Log.e(TAG,"耗时：" + dx + "ms");
        Log.e("zyh","aftertest  around");

        MethodSignature signature = (MethodSignature) point.getSignature();
        String name = signature.getName();
        Method method = signature.getMethod();
        Class returnType = signature.getReturnType();//返回值类型
        String declaringTypeName= signature.getDeclaringTypeName();
        Class[] parameterTypes = signature.getParameterTypes();
        String[] parameterNames = signature.getParameterNames();
        Log.e(TAG, "log: name = "+name+ " method= "+method +" returnType "+returnType.getName() +
                "declarname = "+declaringTypeName );
        for (String s :
                parameterNames) {
            Log.e(TAG, "log: parameName "+s );
        }
    }

    @After("test()")
    public void after(JoinPoint joinPoint){
        Log.e(TAG, "after: test()" );
    }



    @AfterReturning("test()")
    public void afterreturening(JoinPoint joinPoint){
        Log.e(TAG, "afterreturening: ," );
    }

    @AfterThrowing(value = "test()",throwing = "error")
    public void afterthrowing(Throwable e){
        Log.e(TAG, "afterthrowing: "+e.getMessage());
    }

    @Pointcut("execution(@com.example.demo.aopdemo.SecurityCheckAnnoation * *(..))")
    public void makePhone(){}

    @After("makePhone()")
    public void checkpermission(JoinPoint point){
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        SecurityCheckAnnoation annotation = method.getAnnotation(SecurityCheckAnnoation.class);
        String permission = annotation.declaredPermission();
        Log.e(TAG, "checkpermission: "+permission);

    }
}
