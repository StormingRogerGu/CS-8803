package com.example.zhangyongzheng.a8803oct13am;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button friend;
    private Button timer;
    private Button home;
    private Button tasklist;
    private Button profile;
    private Button addtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_layout);
        setUpView();

        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(MainActivity.this,tasksetting.class);
                startActivity(addnewtask);
            }
        });
    }

    private void setUpView(){

        friend = (Button)findViewById(R.id.friend_bar);
        timer = (Button)findViewById(R.id.timer);
        home = (Button)findViewById(R.id.home);
        tasklist = (Button)findViewById(R.id.puzzle);
        addtask = (Button)findViewById(R.id.addnewtask);
    }




}

