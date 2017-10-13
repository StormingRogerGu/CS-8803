package com.example.zhangyongzheng.a8803oct13am;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button friend;
    private Button timer;
    private Button home;
    private Button puzzle;
    private Button profile;

    private Button addtask;
    private TextView due_date;

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

        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(MainActivity.this,friend.class);
                startActivity(addnewtask);
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(MainActivity.this,timesetting.class);
                startActivity(addnewtask);
            }
        });
        puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(MainActivity.this,puzzle.class);
                startActivity(addnewtask);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(MainActivity.this,profile.class);
                startActivity(addnewtask);
            }
        });
        due_date = (TextView)findViewById(R.id.date);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        if(bundle != null){
            Log.v("test",bundle.getString("key"));
            due_date.setText(bundle.getString("key"));
        }


    }

    private void setUpView(){

        friend = (Button)findViewById(R.id.friend_bar);
        timer = (Button)findViewById(R.id.timer);
        home = (Button)findViewById(R.id.home);
        puzzle = (Button)findViewById(R.id.puzzle);
        profile = (Button)findViewById(R.id.profile);
        addtask = (Button)findViewById(R.id.addnewtask);

        
    }




}

