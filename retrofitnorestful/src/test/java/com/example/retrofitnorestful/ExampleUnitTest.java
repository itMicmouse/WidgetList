package com.example.retrofitnorestful;

import com.example.retrofitnorestful.net.UserService;
import com.example.retrofitnorestful.net.domain.User;
import com.example.retrofitnorestful.utils.RetrofitUtils;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.junit.Test;

import java.util.UUID;

public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Gson gson = new Gson();
        User user = new User();
        user.setName("亚坤");
        user.setUid(UUID.randomUUID().toString());
        String s = gson.toJson(user);
        System.out.println(s);
    }
    @Test
    public void testNet() throws Exception {
        UserService api = RetrofitUtils.createApi(UserService.class);
        User name1 = api.loadUser("update","static").execute().body();
        System.out.println(name1.getName());
    }
    @Test
    public void testNetRx() throws Exception {
        UserService api = RetrofitUtils.createApiRx(UserService.class);
        User name1 = api.loadUser("update","static").execute().body();
        System.out.println(name1.getName());
    }
    @Test
    public void testNetNOrest() throws Exception {
        UserService api = RetrofitUtils.createApi(UserService.class);
        User name1 = api.loadUser("name","name").execute().body();
        System.out.println(name1.getName());
    }
    @Test
    public void testNetNO() throws Exception {
        String body = "{\"name\": \"kkmike999\",\"uid\": 1}";
        JSONObject json = new JSONObject(body);
        System.out.println(json.getString("name"));
    }
}