package com.example.zhangyongzheng.a8803oct13am;

import android.app.Application;

/**
 * Created by guxiaofeng on 10/22/17.
 */

public class User_id extends Application {
    private String userid;
    private static User_id instance;

    public static User_id getInstance(){
        return instance;
    }

    public String getUserid(){
        return userid;
    }

    public void setUserid(String s){
        userid = s;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }
}
