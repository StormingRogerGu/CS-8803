package com.example.zhangyongzheng.a8803oct13am;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * Created by zhangyongzheng on 10/13/17.
 */

public class timesetting extends Activity {
    TextView time;
    TimePicker simpleTimePicker;
    NumberPicker nopicker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_setting);

        //  initiate the view's
        time = (TextView) findViewById(R.id.time);
        simpleTimePicker = (TimePicker) findViewById(R.id.simpleTimePicker);
        simpleTimePicker.setIs24HourView(true); // used to not display AM/PM mode
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
        nopicker.setMaxValue(30);
        nopicker.setMinValue(1);
        nopicker.setWrapSelectorWheel(true);

    }
}