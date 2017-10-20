package com.example.zhangyongzheng.a8803oct13am;

import android.support.annotation.NonNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyongzheng on 10/20/17.
 */

public class orderduedate implements Comparator {
    public String due_date;
    public String remind_time;
    public String task_note;

    @Override
    public int compare(Object o1, Object o2) {
        Map.Entry<String, Task_detail> t1 = (Map.Entry<String, Task_detail>)o1;
        Map.Entry<String, Task_detail> t2 = (Map.Entry<String, Task_detail>)o2;

        int flag = t1.getValue().due_date.compareTo(t2.getValue().due_date);

        return flag;
    }
}
