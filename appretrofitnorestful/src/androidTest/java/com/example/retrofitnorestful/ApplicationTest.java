package com.example.retrofitnorestful;

import android.app.Application;
import android.test.ApplicationTestCase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() throws JSONException {
        super(Application.class);
        String body = "{\"ret\": 0,\"msg\": \"123\",\"data\": {\"uid\": \"1\",\"name\": \"kkmike999\"}}";
        JSONObject json = new JSONObject(body);
        System.out.println(json.getInt("ret"));
    }
}