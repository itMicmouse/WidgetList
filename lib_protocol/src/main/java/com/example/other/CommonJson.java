package com.example.other;

import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by yakun on 2016/10/25.
 */

public class CommonJson<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3440061414071692254L;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 数据
     */
    private T data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static CommonJson fromJson(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = type(CommonJson.class, clazz);
        return gson.fromJson(json, objectType);
    }

    public String toJson(Class<T> clazz) {
        Gson gson = new Gson();
        Type objectType = type(CommonJson.class, clazz);
        return gson.toJson(this, objectType);
    }

    static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}