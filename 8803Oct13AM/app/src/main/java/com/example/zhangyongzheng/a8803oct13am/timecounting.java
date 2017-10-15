package com.example.zhangyongzheng.a8803oct13am;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.io.IOException;
import java.util.List;

/**
 * Created by guxiaofeng on 10/13/17.
 */

public class timecounting extends Activity{
    private Mycount myCount;
    private TextView mTvShow;
    Context mContext;
    private int seconds;
    private Button pause;
    private Button resume;
    long current_seconds;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_count);
        mTvShow = (TextView) findViewById(R.id.counting_time);
        pause = (Button) findViewById(R.id.pause_button);
        resume = (Button) findViewById(R.id.resume_button);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            //Log.v("Key", bundle.getString("Key"));
             seconds = bundle.getInt("Key") * 1000;
        }


        myCount = new Mycount(seconds, 1000);
        myCount.start();
        setPause();
        setResume();
    }

    class Mycount extends CountDownTimer{
        long timetofinfish;

        public Mycount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }
        @Override
        public void onFinish() {
            mTvShow.setText("You have successfully finished your study time!");

        //TODO: AFITER FINISH THE TIME MODE, SEND MESSAGE TO PUZZLE GALLERY AND
//            Intent jumptogallery = new Intent();
//            jumptogallery.setClass(timecounting.this,puzzle.class);
//            startActivity(jumptogallery);


        }
        @Override
        public void onTick(long millisUntilFinished) {
            
            mTvShow.setText( (millisUntilFinished / 1000) / 3600 + ":" +
                        millisUntilFinished / 1000 % 3600 / 60 + ":"+
                        millisUntilFinished / 1000 % 60);

            timetofinfish = millisUntilFinished;
        }
    }

    public void setPause(){
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_seconds = myCount.timetofinfish;
                myCount.cancel();
            }
        });
    }

    public void setResume(){
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myCount = new Mycount(current_seconds, 1000);
                myCount.start();
            }
        });
    }

    private boolean isTopActivity(String activityName){
        ActivityManager manager = (ActivityManager) mContext.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        String cmpNameTemp = null;
        if(runningTaskInfos != null){
            cmpNameTemp = runningTaskInfos.get(0).topActivity.toString();
        }
        if(cmpNameTemp == null){
            return false;
        }
        return cmpNameTemp.equals(activityName);
    }

}
