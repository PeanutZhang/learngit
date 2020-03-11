#include <jni.h>
#include <stdio.h>

#ifdef __cplusplus
extern "C" {
#endif

/**
 * 函数名规则: Java_包名_类名_方法名
 * @param env  表示一个指向JNI环境的指针, 可以通过它来方位JNI提供的接口方法
 * @param thiz 表示Java对象中的this
 * @return
 */
jstring Java_com_example_demo_NUtils_getKey(JNIEnv *env, jobject thiz) {
    printf("invoke get in c++\n");
    return env->NewStringUTF("Hello from JNI in keyjni.so !");
}
/**
void Java_com_qyh_hellojni_MainActivity_set(JNIEnv *env, jobject thiz, jstring string) {
    printf("invoke set from C++\n");
    char* str = (char*)env->GetStringUTFChars(string,NULL);
    printf("%s\n", str);
    env->ReleaseStringUTFChars(string, str);
}
*/
#ifdef __cplusplus
}
#endif
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_demo_NUtils_fuck(JNIEnv *env, jobject instance) {

    // TODO


    return env->NewStringUTF("sss");
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_demo_NUtils_f(JNIEnv *env, jclass type) {

    // TODO


    return env->NewStringUTF("静态方法");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_demo_NUtils_test(JNIEnv *env, jobject instance, jstring s_) {
    const char *s = env->GetStringUTFChars(s_, 0);

    // TODO

    env->ReleaseStringUTFChars(s_, s);

    return env->NewStringUTF("s");
}
extern "C"
JNIEXPORT void JNICALL
Java_com_example_demo_NUtils_set(JNIEnv *env, jobject instance, jint s) {

    // TODO

}

extern "C"
JNIEXPORT jstring  JNICALL
Java_com_example_demo_NUtils_jnicalljava(JNIEnv *env,jobject instance,jobject context){
   jclass  jc = env->GetObjectClass(instance);
   jmethodID  id = env->GetMethodID(jc,"jnicalljava","(Ljava/lang/String;)Ljava/lang/String;");
   jstring  s = (jstring)env->CallObjectMethod(instance,id,context);
   const  char *cs = env->GetStringUTFChars(s,0);
    return env->NewStringUTF(cs);
}

