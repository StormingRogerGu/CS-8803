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
    TimePicker simpleTimePicker;
    NumberPicker nopicker = null;
    private Button start;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_setting);

        //  initiate the view's
        time = (TextView) findViewById(R.id.time);
        simpleTimePicker = (TimePicker) findViewById(R.id.simpleTimePicker);
        simpleTimePicker.setIs24HourView(true); // used to not display AM/PM mode
        simpleTimePicker.setHour(0);
        // perform set on time changed listener event
        simpleTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // display a toast with changed values of time picker
                //Toast.makeText(getApplicationContext(), hourOfDay + " hours " + minute + "min", Toast.LENGTH_SHORT).show();
            }
        });

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
                int seconds = (int)(simpleTimePicker.getHour() * 3600 + simpleTimePicker.getMinute()*60);
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

}