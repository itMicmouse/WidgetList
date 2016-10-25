package com.example;

import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by yakun on 2016/10/25.
 */

public class BaseProtocol<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -3440061414071692254L;

    private Header header = new Header();
    private Body<T> body = new Body<>();

    public Header getHeader() {
        return header;
    }

    public Body<T> getBody() {
        return body;
    }


    public static BaseProtocol fromJson(String json, Class clazz, Class clazzerror) {
        Gson gson = new Gson();
        BaseProtocol baseProtocol = gson.fromJson(json, BaseProtocol.class);
        if(baseProtocol.getBody().getCode().equals("0000"))
            return gson.fromJson(json, type(BaseProtocol.class, clazz));
        else {
//            return  gson.fromJson(json, type(BaseProtocol.class, clazzerror));
            return  baseProtocol;
        }
    }

    public String toJson(Class<T> clazz) {
        Gson gson = new Gson();
        Type objectType = type(BaseProtocol.class, clazz);
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
