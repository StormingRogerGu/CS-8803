package com.example.zhangyongzheng.a8803oct13am;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.io.IOException;

/**
 * Created by guxiaofeng on 10/13/17.
 */

public class timecounting extends Activity{
    private Mycount myCount;
    private TextView mTvShow;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_count);
        mTvShow = (TextView) findViewById(R.id.counting_time);
        myCount = new Mycount(10000, 1000);
        myCount.start();
    }

    class Mycount extends CountDownTimer{

        public Mycount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {
            mTvShow.setText("You have successfully finished your study time!");

            Intent jumptogallery = new Intent();
            jumptogallery.setClass(timecounting.this,puzzle.class);
            startActivity(jumptogallery);


        }
        @Override
        public void onTick(long millisUntilFinished) {
            mTvShow.setText( (millisUntilFinished / 1000) / 3600 + "Hours " +
                                millisUntilFinished / 1000 % 3600 / 60 + "Minutes "+
                                millisUntilFinished / 1000 % 60 + "Seconds");

        }
    }

}
