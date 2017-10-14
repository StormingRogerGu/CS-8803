package com.example.zhangyongzheng.a8803oct13am;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private Button friend;
    private Button timer;
    private Button home;
    private Button puzzle;
    private Button profile;

    private Button addtask;
    private TextView due_date;

    private static ListView lv;
    private static ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();;

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
        //due_date = (TextView)findViewById(R.id.date);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        myadapter myownadapter = new myadapter(this, listItem);

        if(bundle != null){
            Log.v("test",bundle.getString("due_date"));
            Log.v("test2",bundle.getString("task_name"));
            //due_date.setText(bundle.getString("due_date"));
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemText",bundle.getString("task_name"));
            map.put("ItemTitle",bundle.getString("due_date"));
            map.put("ItemImage",R.mipmap.ic_launcher);
            listItem.add(map);

        }
        //initiData();


        myownadapter.updateData(listItem);
        lv.setAdapter(myownadapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("test","click click");

            }
        });


    }

    private void setUpView(){

        friend = (Button)findViewById(R.id.friend_bar);
        timer = (Button)findViewById(R.id.timer);
        home = (Button)findViewById(R.id.home);
        puzzle = (Button)findViewById(R.id.puzzle);
        profile = (Button)findViewById(R.id.profile);
        addtask = (Button)findViewById(R.id.addnewtask);
        lv = (ListView)findViewById(R.id.listview1);
    }

    private void initiData(){
        listItem = new ArrayList<HashMap<String, Object>>();

//        for(int i = 0; i<100;i++){
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("ItemImage",R.mipmap.ic_launcher);
//            map.put("ItemTitle",i + "th lane");
//            map.put("ItemText", "this is" + i + "th lane");
//            listItem.add(map);
//        }
    }




}

