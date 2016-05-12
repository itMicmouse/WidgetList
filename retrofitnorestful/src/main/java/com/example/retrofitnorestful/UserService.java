package com.example.retrofitnorestful;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by yakun on 2016/5/12.
 */
public interface UserService {
    @GET("{name}.json")
    Call<User> loadUser(@Path("name") String name);
}
