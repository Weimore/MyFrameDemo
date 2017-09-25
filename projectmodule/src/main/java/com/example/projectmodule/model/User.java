package com.example.projectmodule.model;

import java.io.Serializable;

/**
 * Created by 吴城林 on 2017/8/4.
 */

public class User implements Serializable{
    private String name;
    private int imageRes;

    public User(String name, int imageRes) {
        this.name = name;
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", imageRes=" + imageRes +
                '}';
    }
}
