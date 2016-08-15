package com.example.retrofitnorestful.net;

import com.example.retrofitnorestful.annotation.Data;
import com.example.retrofitnorestful.net.domain.User;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit2.converter.gson.main.Converter;
import retrofit2.converter.gson.main.impl.ClassConverter;

/**
 * Created by yakun on 2016/5/12.
 */
public interface UserService {
    @Converter(converter = ClassConverter.class)
//    @Data("data")
    @GET("path/{name}.json")
    Call<User> loadUser(@Path("name") String name,@Path("path") String path);
}
