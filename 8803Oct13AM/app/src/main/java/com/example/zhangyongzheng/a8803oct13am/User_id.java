package com.example.zhangyongzheng.a8803oct13am;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;

import java.util.List;

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

    public static boolean isForeground(Context context)
    {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
