package com.example.zhangyongzheng.a8803oct13am;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by zhangyongzheng on 10/13/17.
 */


public class puzzle extends Activity {

    private Button friend;
    private Button timer;
    private Button home;
    private Button puzzle;
    private Button profile;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle_gallery);

        setUpView();
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(puzzle.this,friend.class);
                startActivity(addnewtask);
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(puzzle.this,timesetting.class);
                startActivity(addnewtask);
            }
        });
        puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(puzzle.this,puzzle.class);
                startActivity(addnewtask);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(puzzle.this,profile.class);
                startActivity(addnewtask);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(puzzle.this,MainActivity.class);
                startActivity(addnewtask);
            }
        });
    }

    private void setUpView(){

        friend = (Button)findViewById(R.id.friend_bar);
        timer = (Button)findViewById(R.id.timer);
        home = (Button)findViewById(R.id.home);
        puzzle = (Button)findViewById(R.id.puzzle);
        profile = (Button)findViewById(R.id.profile);

    }

}
