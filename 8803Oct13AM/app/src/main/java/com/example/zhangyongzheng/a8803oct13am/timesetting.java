package com.example.zhangyongzheng.a8803oct13am;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Button;


import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * Created by zhangyongzheng on 10/13/17.
 */

public class timesetting extends Activity {
    TextView time;
    //TimePicker simpleTimePicker;
    NumberPicker nopicker = null;
    private Button start;
    NumberPicker nopicker_hour = null;
    NumberPicker nopicker_min = null;

    private Button friend;
    private Button timer;
    private Button home;
    private Button puzzle;
    private Button profile;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_setting);
        setUpView();

        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(timesetting.this,friend.class);
                startActivity(addnewtask);
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(timesetting.this,timesetting.class);
                startActivity(addnewtask);
            }
        });
        puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(timesetting.this,puzzle.class);
                startActivity(addnewtask);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(timesetting.this,profile.class);
                startActivity(addnewtask);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(timesetting.this,MainActivity.class);
                startActivity(addnewtask);
            }
        });




        //  initiate the view's
//        time = (TextView) findViewById(R.id.time);
//        simpleTimePicker = (TimePicker) findViewById(R.id.simpleTimePicker);
//        simpleTimePicker.setIs24HourView(true); // used to not display AM/PM mode
//        simpleTimePicker.setHour(0);
//        // perform set on time changed listener event
//        simpleTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
//            @Override
//            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//                // display a toast with changed values of time picker
//                //Toast.makeText(getApplicationContext(), hourOfDay + " hours " + minute + "min", Toast.LENGTH_SHORT).show();
//            }
//        }

        //numberpicker hour
        nopicker_hour = (NumberPicker)findViewById(R.id.numberPicker_hour);
        nopicker_hour.setMaxValue(8);
        nopicker_hour.setMinValue(0);
        nopicker_hour.setWrapSelectorWheel(true);

        //numberpicker min
        nopicker_min = (NumberPicker)findViewById(R.id.numberPicker_min);
        nopicker_min.setMaxValue(59);
        nopicker_min.setMinValue(0);
        nopicker_min.setWrapSelectorWheel(true);

        //numberpicker1
        nopicker = (NumberPicker)findViewById(R.id.numberPicker1);
        nopicker.setMaxValue(10);
        nopicker.setMinValue(1);
        nopicker.setWrapSelectorWheel(true);

        startTiming();

    }

    public void startTiming(){
        start = (Button)findViewById(R.id.btn_start);
        start.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v){
                int seconds = (int)(nopicker_hour.getValue() * 3600 + nopicker_min.getValue()*60);
                int pause_seconds = (int)(nopicker.getValue() * 60);

                Intent intent = new Intent(timesetting.this,timecounting.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Key", seconds);
                bundle.putInt("Pause", pause_seconds);

                intent.putExtras(bundle);
                startActivity(intent);

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