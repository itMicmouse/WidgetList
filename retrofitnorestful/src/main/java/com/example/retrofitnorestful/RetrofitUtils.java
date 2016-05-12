package com.example.retrofitnorestful;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.CustomConverterFactory;

/**
 * @author yangyakun
 */
public class RetrofitUtils {
    private static Retrofit singleton;
    private static Retrofit singletonRx;

    public static <T> T createApi(Class<T> clazz) {
        if (singleton == null) {
            synchronized (RetrofitUtils.class) {
                singleton = new Retrofit.Builder().
                        baseUrl("http://192.168.16.202:8080")
                        .client(OkHttpUtils.getInstance())
                        .addConverterFactory(CustomConverterFactory.create())
                        .build();
            }
        }
        return singleton.create(clazz);
    }

    public static <T> T createApiRx(Class<T> clazz) {
        if (singletonRx == null) {
            synchronized (RetrofitUtils.class) {
                singletonRx = new Retrofit.Builder().
                        baseUrl("http://192.168.16.92:8080")
                        .client(OkHttpUtils.getInstance())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
            }
        }
        return singletonRx.create(clazz);
    }

}
