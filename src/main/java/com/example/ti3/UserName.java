package com.example.ti3;

import java.util.ArrayList;

public class UserName {

    //CPU [0]
    //Player1 [1]
    //Player2 [2]

    private static UserName instance;

    public static UserName  getInstance(){
        if(instance == null){
            instance = new UserName();
        }
        return instance;
    }

    public UserName() {
        usernames = new String[3];
    }

    private String usernames [];

    public String[] getUsernames() {
        return usernames;
    }
}
