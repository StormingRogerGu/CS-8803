package com.example.zhangyongzheng.a8803oct13am;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
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

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import com.example.zhangyongzheng.a8803oct13am.numberpickerstyle;

/**
 * Created by zhangyongzheng on 10/13/17.
 */

public class timesetting extends Activity {
    TextView time;

    private ImageButton start;


    private ImageButton friend;
    private ImageButton timer;
    private ImageButton home;
    private ImageButton puzzle;
    private ImageButton profile;

    @SuppressLint("WrongViewCast")
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


        //numberpicker hour
        final numberpickerstyle nopicker_hour = (numberpickerstyle) findViewById(R.id.numberPicker_hour);
        nopicker_hour.setMaxValue(8);
        nopicker_hour.setMinValue(0);

        nopicker_hour.setWrapSelectorWheel(true);
        nopicker_hour.setBackgroundColor(Color.BLACK);
        nopicker_hour.setNumberPickerDividerColor(nopicker_hour);

        //numberpicker min
        final numberpickerstyle nopicker_min = (numberpickerstyle) findViewById(R.id.numberPicker_min);
        nopicker_min.setMaxValue(59);
        nopicker_min.setMinValue(0);
        nopicker_min.setBackgroundColor(Color.BLACK);
        nopicker_min.setNumberPickerDividerColor(nopicker_min);
        nopicker_min.setWrapSelectorWheel(true);

        //numberpicker1
        final numberpickerstyle nopicker = (numberpickerstyle) findViewById(R.id.numberPicker1);
        nopicker.setMaxValue(10);
        nopicker.setMinValue(0);
        nopicker.setSelected(true);
        nopicker.setBackgroundColor(Color.BLACK);
        nopicker.setNumberPickerDividerColor(nopicker);

        //startTiming();
        start = (ImageButton)findViewById(R.id.btn_start);
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

        friend = (ImageButton)findViewById(R.id.friend_bar_time);
        timer = (ImageButton)findViewById(R.id.timer_time);
        home = (ImageButton)findViewById(R.id.home_time);
        puzzle = (ImageButton)findViewById(R.id.puzzle_time);
        profile = (ImageButton)findViewById(R.id.profile_time);

    }

//    public static byte[] drawable2Bytes(Drawable drawable) {
//
//
//        if (drawable == null) {
//            return null;
//        }
//        Bitmap bitmap = drawableToBitmap(drawable);
//
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        return baos.toByteArray();
//    }
//    public static Bitmap drawableToBitmap(Drawable drawable) {
//
//
//        Bitmap bitmap = Bitmap
//                .createBitmap(
//                        drawable.getIntrinsicWidth(),
//                        drawable.getIntrinsicHeight(),
//                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
//                                : Bitmap.Config.RGB_565);
//        Canvas canvas = new Canvas(bitmap);
//        // canvas.setBitmap(bitmap);
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
//                drawable.getIntrinsicHeight());
//        drawable.draw(canvas);
//        return bitmap;
//    }

}