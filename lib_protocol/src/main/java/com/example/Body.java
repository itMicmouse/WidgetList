package com.example;

import java.util.Objects;

/**
 * Created by yakun on 2016/10/25.
 */

public class Body<T> {
    private String code = "";
    private T content;
    private Object param;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
