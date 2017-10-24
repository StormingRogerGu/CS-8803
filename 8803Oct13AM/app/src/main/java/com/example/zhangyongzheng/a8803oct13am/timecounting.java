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
    private Button pause_resume;
    long current_seconds;
    private boolean button_resume = true;

    private int notifyId = 100;
    private NotificationManager mNotificationManager;
    public NotificationCompat.Builder mBuilder;
    private User_id Utils;
    boolean finish_task = true;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_count);
        mTvShow = (TextView) findViewById(R.id.counting_time);
        pause_resume = (Button) findViewById(R.id.pause_resume_button);

        initNotify();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            //Log.v("Key", bundle.getString("Key"));
             seconds = bundle.getInt("Key") * 1000;
        }


        myCount = new Mycount(seconds, 1000);
        myCount.start();
        setPause_Resume();

//        if (button_resume == false)
//            onStop();

    }


    @Override
    //Supervise the log out the page
    protected void onStop() {
        super.onStop();
        if(!Utils.isForeground(this))
        {
            if (button_resume == true) {

                finish_task = false;
                showIntentActivityNotify("Lose");

            }
            else{
                showIntentActivityNotify("Back!!!!!");

            }
        }
    }




    private void initNotify() {
        mNotificationManager = (NotificationManager) getSystemService((NOTIFICATION_SERVICE));
        mBuilder = new NotificationCompat.Builder(getApplicationContext());
        mBuilder.setContentTitle("3")
                //.setContentText("You win a new puzzle!")
                .setContentIntent(getDefaultIntent(Notification.FLAG_AUTO_CANCEL))
                .setTicker("5")
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setOngoing(false)
                .setSmallIcon(R.drawable.puzzle_icon_highlight);

    }

    public PendingIntent getDefaultIntent(int flags) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1 ,new Intent(), flags);
        return pendingIntent;
    }

    public void showIntentActivityNotify(String who) {
        mBuilder.setAutoCancel(true)
                .setContentTitle(who)
                .setWhen(System.currentTimeMillis())
                .setTicker("6");

        Intent resultIntent = new Intent(this, timecounting.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify(notifyId, mBuilder.build());
    }


    class Mycount extends CountDownTimer{
        long timetofinfish;

        public Mycount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }
        @Override
        public void onFinish() {
            mTvShow.setText("You have successfully finished your study time!");

            mBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
            if (finish_task == true)
                showIntentActivityNotify("Congratulations! New Puzzle!");
            else
                showIntentActivityNotify("Sorry, You lose");

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

    public void setPause_Resume(){
        pause_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button_resume == true){
                    current_seconds = myCount.timetofinfish;
                    myCount.cancel();
                    button_resume = false;
                    pause_resume.setBackgroundResource(R.drawable.resume_button);

                }
                else{
                    myCount = new Mycount(current_seconds, 1000);
                    myCount.start();
                    button_resume = true;
                    pause_resume.setBackgroundResource(R.drawable.pause_button);
                }

            }
        });
    }




}
