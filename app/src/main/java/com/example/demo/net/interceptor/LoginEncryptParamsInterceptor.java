package com.example.demo.net.interceptor;

import android.util.Log;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 登录数据加密拦截器
 * @author zyh
 * @date 2019-04-24 12:06
 * @company 上海若美科技有限公司
 */
public class LoginEncryptParamsInterceptor implements Interceptor {

    private static final String TAG = "数据加密拦截器";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//
//        User user = RMApplication.getInstance().getUser();
//        if (Constants.LOGIN_STATUS.SUCCESS.equals(user.login_status)) {
//            Log.i(TAG, "登录状态 开始加密");
//            RequestBody body = request.body();
//            HttpUrl url = request.url();
//            String method = request.method();
//
//            if (body != null) {
//                if (body instanceof FormBody) {
//                    FormBody formBody = (FormBody) body;
//                    body = encryptFromBody(formBody);
//                }
//            } else if (url.querySize() != 0) {
//                //  解析URL
//                url = encryptUrl(url);
//            }
//            request = request.newBuilder().method(method, body).url(url).build();
//        }else {
//            Log.i(TAG, "非登录状态不用加密");
//        }

        return chain.proceed(request);
    }

//    private HttpUrl encryptUrl(HttpUrl url) {
//        Map<String, String> paramsMap = new HashMap<String, String>();
//        HttpUrl.Builder builder = url.newBuilder();
//        for (int i = 0; i < url.querySize(); i++) {
//            paramsMap.put(url.queryParameterName(i), url.queryParameterValue(i));
//        }
//        for (String key : paramsMap.keySet()) {
//            builder.removeAllQueryParameters(key);
//        }
//        Map<String, String> params = addSingAndKeyParam(paramsMap);
//        ArrayList<String> keySet = new ArrayList<>(params.keySet());
//        Collections.sort(keySet);
//        for ( String key: keySet) {
//            builder.addQueryParameter(key, params.get(key));
//        }
//        return  builder.build();
//    }

    /**
     * 生成加密的FromBody
     * @param formBody
     * @return
     */
//    private RequestBody encryptFromBody(FormBody formBody) {
//
//        Map<String, String> paramsMap = new HashMap<String, String>();
//        for (int i = 0; i < formBody.size(); i++) {
//            paramsMap.put(formBody.name(i), formBody.value(i));
//        }
//        Map<String, String> params = addSingAndKeyParam(paramsMap);
//        FormBody.Builder builder = new FormBody.Builder();
//        ArrayList<String> keySet = new ArrayList<>(params.keySet());
//        Collections.sort(keySet);
//        for ( String key: keySet) {
//            builder.add(key, params.get(key));
//        }
//        return builder.build();
//    }

    /**
     * 加密
     * @param paramsMap
     * @return
     */
//    private Map<String, String> addSingAndKeyParam(Map<String, String> paramsMap) {
//        User user = RMApplication.getInstance().getUser();
//            int random_key = 40832;
//            paramsMap.put("member_id", user.member_id + "");
//            paramsMap.put("member_type", user.member_type);
//            paramsMap.put("random_key", random_key + "");
//
//            Collection<String> keyset = paramsMap.keySet();
//            ArrayList<String> list = new ArrayList<String>(keyset);
//            StringBuffer buffer = new StringBuffer();
//            //
//            // 对key键值按字典升序排序
//            Collections.sort(list);
//            if (!list.isEmpty()) {
//                for (int i = 0; i < list.size(); i++) {
//                    String key = list.get(i);
//                    if (key == null) {
//                        continue;
//                    }
//                    Object value = paramsMap.get(key);
//                    if (value == null) {
//                        continue;
//                    }
//                    buffer.append(encode(key) + "=" + encode(value.toString()) + "&");
//                }
//            }
//            String str = buffer.toString();
//            String url = str.substring(0, str.length() - 1);
//            try {
//                String string_1 = url + random_key + "nybang0143";
//                StringBuffer sb = new StringBuffer();
//                byte data[] = HmacMd5.encryptHMAC(string_1.getBytes(),
//                        user.token);
//                for (byte b : data) {
//                    sb.append(String.format("%02x", b));
//                }
//                paramsMap.put("hkm_sign2", sb.toString());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        return paramsMap;
//    }

    /**
     * 描述：参数转换.
     *
     * @param s
     * @return
     */

    public String encode(String s) {
        try {
//			s = s.replaceAll(" ", "%20");
            String encode = URLEncoder.encode(s, "UTF-8");
            if (encode.contains("*")) {
                encode = encode.replace("*", "%2A");
            }
            encode = encode.replaceAll("\\+", "%20");
            return encode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

}
