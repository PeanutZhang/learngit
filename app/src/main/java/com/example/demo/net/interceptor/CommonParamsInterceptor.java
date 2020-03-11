package com.example.demo.net.interceptor;

import android.support.annotation.NonNull;
import android.text.TextUtils;


import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 添加通用数据
 * @author wujian
 * @date 2016-11-18 12:06
 * @company 上海若美科技有限公司
 */
public class CommonParamsInterceptor implements Interceptor {

    private static final String TAG = "通用参数添加拦截器";

    private  static  final String KEY_VERSION_CODE = "versionCode";
    private  static  final String KEY_SYSTEM_NAME = "system_name";
    private  static  final String KEY_CHANNEL = "channel";
    private  static  final String KEY_AGE = "age";
//
//    private final String mVersionCode;
//    private final String mSystemName;
//    private final String mChannel;
//    private String userAgentStr;
//    private  final String mVersion;
//    private  final String mOSVersion;
    private String mXContent = "";


    public CommonParamsInterceptor() {
//        mVersionCode = AppHelper.getVersionCode(RMApplication.getInstance().getApplicationContext()) + "";
//        mSystemName = Constants.DIVICE_TYPE;
//        userAgentStr = AppHelper.getUserAgentStr(RMApplication.getInstance().getApplicationContext());
//        mVersion =  AppHelper.getVersion(RMApplication.getInstance().getApplicationContext());
//        mOSVersion = AppHelper.getAndroidOSVersion() + "";
//        mChannel = RMApplication.getInstance().getChannel();
//        String devicesId = AppHelper.getDevicesId(RMApplication.getInstance().getApplicationContext());
//        try {
//            mXContent = devicesId + "_" + HmacMd5.encryptHMAC(devicesId, NConstantsUtils.getSecretKey());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody body = request.body();
        HttpUrl url = request.url();
        Headers headers = request.headers();
        headers = addCommonHeaders(headers);
        if (body != null){
            body = addFromBodyParams(body);
        } else{
            url = addUrlParams(url);
        }
        request = request.newBuilder().url(url).headers(headers).method(request.method(), body).build();

        return chain.proceed(request);
    }

    /**
     * 添加头信息
     * @param headers
     * @return
     */
    private Headers addCommonHeaders(Headers headers) {
//        return  headers.newBuilder().set("User-Agent",userAgentStr).add("x-nanyibang",mVersion)
//                .add("x-android", mOSVersion).add("x-content", mXContent).build();
      return headers;
    }

    /**
     * 当是get请求时 添加通用参数
     * @param url
     * @return
     */
    @NonNull
    private HttpUrl addUrlParams(HttpUrl url) {
//        HttpUrl.Builder builder = url.newBuilder();
//                .addQueryParameter(KEY_VERSION_CODE, mVersionCode)
//                .addQueryParameter(KEY_SYSTEM_NAME, mSystemName).addQueryParameter(KEY_CHANNEL, mChannel);
        //添加年龄参数
//        if (TextUtils.isEmpty(url.queryParameter(KEY_AGE))){
//            builder.addQueryParameter(KEY_AGE, BangApplication.getInstance().getChooseAge() + "");
//        }
//        url = builder.build();
        return url;
    }

    /**
     * 给body添加通用参数
     * @param body
     * @return
     */
    private RequestBody addFromBodyParams(RequestBody body) {
        if (body instanceof FormBody){
            FormBody formBody = (FormBody) body;
            FormBody.Builder builder = new FormBody.Builder();
            boolean hasAge = false;
            for (int i = 0; i < formBody.size(); i++){
                String key = formBody.encodedName(i);
                builder.add(formBody.name(i),formBody.value(i));
                if (TextUtils.equals(key,KEY_AGE)) hasAge = true;
            }
//            builder.add(KEY_VERSION_CODE, mVersionCode);
//            builder.add(KEY_SYSTEM_NAME, mSystemName);
//            builder.add(KEY_CHANNEL, mChannel);
            //添加年龄
            if (!hasAge) {
//                builder.add(KEY_AGE, BangApplication.getInstance().getChooseAge() + "");
            }
            body = builder.build();
        }
        return body;
    }
}
