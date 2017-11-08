package com.example.zhangyongzheng.a8803oct13am;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;

/**
 * Created by guxiaofeng on 10/13/17.
 */

public class timecounting extends Activity{
    private Mycount myCount;
    private Resume_time resume_time;
    private TextView mTvShow;
    private int seconds;
    private int resume_seconds;
    private Button pause_resume;
    long current_seconds;
    private boolean button_resume = true;

    private int notifyId = 100;
    private NotificationManager mNotificationManager;
    public NotificationCompat.Builder mBuilder;
    private User_id Utils;
    private String user_id;
    boolean finish_task = true;
    private int current_puzzle_id;

    private DatabaseReference myref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_count);
        mTvShow = (TextView) findViewById(R.id.counting_time);
        pause_resume = (Button) findViewById(R.id.pause_resume_button);
        Utils = (User_id) getApplication();
        user_id = Utils.getUserid();
        myref = FirebaseDatabase.getInstance().getReference("User_profile").child(user_id).child("Time_mode").child("puzzle_ongoing_id").child("puzzle_piece_ongoing");
        initNotify();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            //Log.v("Key", bundle.getString("Key"));
             seconds = bundle.getInt("Key") * 1000;
             resume_seconds = bundle.getInt("Pause") * 1000;
        }


        myCount = new Mycount(seconds, 1000);
        resume_time = new Resume_time(resume_seconds, 1000);
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
                showIntentActivityNotify("Lose due to no focus !!!");
                Intent intent = new Intent(timecounting.this, timesetting.class);
                startActivity(intent);
            }
            else{
                showIntentActivityNotify(" You are using pause time! Back!!!!!");

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

    //Regular Timer
    class Mycount extends CountDownTimer{
        long timetofinfish;

        public Mycount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }
        @Override
        public void onFinish() {
            mTvShow.setText("You have successfully finished your study time!");

            myref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int ongoing_id = dataSnapshot.getValue(int.class);

                    current_puzzle_id = ongoing_id+1;

                    Log.v("Piece_id","piece"+current_puzzle_id);
                    myref.setValue(current_puzzle_id);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            //current_puzzle_id = current_puzzle_id + 1;


            mBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
            showIntentActivityNotify("Congratulations! New Puzzle!");
            AlertDialog dialog = new AlertDialog.Builder(timecounting.this).setTitle("Mission Complete")
                                                    .setPositiveButton("Share",new DialogInterface.OnClickListener(){
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which){

                                                            Intent intent = new Intent(timecounting.this, puzzle.class);
                                                            startActivity(intent);
                                                            //TODO share to facebook
                                                        }
                                                    })
                                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            Intent intent = new Intent(timecounting.this, puzzle.class);
                                                            startActivity(intent);
                                                        }
                                                    }).setMessage("Share or not").create();

            dialog.show();

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

    //Resume Timer
    class Resume_time extends CountDownTimer{
        long rest_resume_time;
        public Resume_time(long millisinFuture, long countDownInterval){
            super(millisinFuture, countDownInterval);
        }

        @Override
        public void onTick(long millistoEnd) {
            rest_resume_time = millistoEnd;

        }

        @Override
        public void onFinish() {
            showIntentActivityNotify("Lose due to running out of resume time!!");
            Intent intent = new Intent(timecounting.this, timesetting.class);
            startActivity(intent);

        }
    }

    public void setPause_Resume(){
        pause_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button_resume == true){
                    current_seconds = myCount.timetofinfish;
                    myCount.cancel();
                    resume_time.start();
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
