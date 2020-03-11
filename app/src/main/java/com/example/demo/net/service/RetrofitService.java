package com.example.demo.net.service;


import com.example.demo.BuildConfig;
import com.example.demo.net.converter.factory.StringConverterFactory;
import com.example.demo.net.interceptor.CommonParamsInterceptor;
import com.example.demo.net.interceptor.LoginEncryptParamsInterceptor;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.io.File;
import java.lang.reflect.Type;
import java.net.Proxy;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @Package: com.nanyibang.rm.service
 * @Description:
 * @Author: zyh
 * @CreateDate: 2019/4/24
 * @company: 上海若美科技有限公司
 */
public class RetrofitService {

    protected static final long CACHE_STALE_SEC =  60 * 60 * 24 * 2;
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    private LinkedHashMap<Class, Object> map = new LinkedHashMap<>();
    private static  final  int MAX_OBJ = 15;


    private RetrofitService() {
        generOkhttpClient();
        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        // 因为BaseUrl相同 所以这里Retrofit为静态，  OkHttpClient配置是一样的,静态创建一次即可

        mRetrofit = new Retrofit.Builder().client(mOkHttpClient).baseUrl("").addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").setLenient().create()))
//                .addConverterFactory(GsonConverterFactory.create(builder.create()))
//                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 获取 网络服务
     *
     * @return
     */
    public static final RetrofitService getInstance() {
        return RetrofitServiceHolder.retrofitService;
    }

    /**
     * 获得指定的api 接口
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getApiService(Class<T> clazz) {
        Object obj = map.get(clazz);
        if (obj == null){
            synchronized (RetrofitService.class){
                if (obj == null)
                    obj = mRetrofit.create(clazz);
                addObjToMap(clazz, obj);
            }
        }
        return  (T)obj;
    }

    private <T> void addObjToMap(Class<T> clazz, Object obj) {
        if (map.size() >= MAX_OBJ){
            try {
                map.remove(map.keySet().iterator().next());
            }catch (Exception e){}
        }
        map.put(clazz, obj);
    }

    private static class RetrofitServiceHolder {
        private static final RetrofitService retrofitService = new RetrofitService();
    }

    public void updateRetrofit(String url){
        mRetrofit = new Retrofit.Builder().client(mOkHttpClient).baseUrl(url).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    private void generOkhttpClient() {
        // 云端响应头拦截器，用来配置缓存策略
//        File cacheFile = new File(RMApplication.getInstance().getCacheDir(),
//                "HttpCache"); // 指定缓存路径
//        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); // 指定缓存大小100Mb
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


//        ClearableCookieJar cookieJar =
//
//                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(RMApplication.getInstance().getApplicationContext()));


        //        mOkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                .cache(cache)
                .sslSocketFactory(createSSLSocketFactory())
                .hostnameVerifier(new TrustAllHostnameVerifier())
//                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
//                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
//                .cookieJar(cookieJar)
                .addInterceptor(new CommonParamsInterceptor())
//                .addInterceptor(new LoginEncryptParamsInterceptor())
//                .addInterceptor(httpLoggingInterceptor)
//                .addInterceptor(new LoggingInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .proxy(Proxy.NO_PROXY);
        mOkHttpClient = builder.build();
    }
    private static SSLSocketFactory createSSLSocketFactory() {

        SSLSocketFactory sSLSocketFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return sSLSocketFactory;
    }
    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)

                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
