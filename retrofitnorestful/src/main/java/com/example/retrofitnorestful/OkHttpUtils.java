package com.example.retrofitnorestful;


import com.squareup.okhttp.OkHttpClient;

/**
 * @author  yangyakun on 16-3-5.
 */
public class OkHttpUtils {
    /**
     * 全局用的网络服务
     */
    private static OkHttpClient singleton;

    /**
     * @return okhttp 的Client
     */
    public static OkHttpClient getInstance() {
        if (singleton == null) {
            synchronized (OkHttpUtils.class) {
                if (singleton == null) {
                    singleton = new OkHttpClient();
                    singleton.interceptors().add(new LoggingInterceptor());
                }
            }
        }
        return singleton;
    }
}
