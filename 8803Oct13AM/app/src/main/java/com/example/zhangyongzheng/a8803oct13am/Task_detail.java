package com.example.zhangyongzheng.a8803oct13am;

/**
 * Created by zhangyongzheng on 10/20/17.
 */

public class Task_detail {
    //public String user_id;
    //public String task_name;
    public String due_date;
    public String remind_time;
    public String task_note;
    public String task_top;

    public Task_detail(){

    }

    public Task_detail(String due_date, String remind_time, String task_note, String task_top) {
        //this.user_id = user_id;
        //this.task_name = task_name;
        this.due_date = due_date;
        this.remind_time = remind_time;
        this.task_note = task_note;
        this.task_top = task_top;
    }

    public String getDue_date(){
        return this.due_date;
    }
    public String getRemind_time(){
        return this.remind_time;
    }
    public String getTask_note(){
        return this.task_note;
    }
    public String getTask_top(){
        return this.task_top;
    }

    public String toString(){
        return this.due_date + " " + this.remind_time + " " + this.task_note + " " + this.task_top;
    }
}
