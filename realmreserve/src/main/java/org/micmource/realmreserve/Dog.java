package org.micmource.realmreserve;

import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by yakunyang on 16/7/1.
 */
public class Dog extends RealmObject {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
