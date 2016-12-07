package com.example;

/**
 * Created by yakun on 2016/11/14.
 */

public class Domain {
    private String id;
    private String name;
    private String age;
    private String sex;

    @Override
    public boolean equals(Object obj) {
        return this.getId().equals(((Domain)obj).getId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
