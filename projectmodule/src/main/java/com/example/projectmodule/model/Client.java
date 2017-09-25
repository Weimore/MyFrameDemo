package com.example.projectmodule.model;

/**
 * Created by 吴城林 on 2017/8/4.
 */
//未使用
public class Client {
    public int ID;
    public Authority authority;

    public int Login_status= 1;

    public Client(int ID, Authority authority) {
        this.ID = ID;
        this.authority = authority;
    }

    public static enum Authority{
        VIP1,VIP2,VIP3
    }
}
