package com.example.zhangyongzheng.a8803oct13am;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
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
        myCount = new Mycount(3610000, 1000);
        myCount.start();
    }

    class Mycount extends CountDownTimer{

        public Mycount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {
            mTvShow.setText("finish");
        }
        @Override
        public void onTick(long millisUntilFinished) {
            mTvShow.setText( (millisUntilFinished / 1000) / 3600 + "Hours " +
                                millisUntilFinished / 1000 % 3600 / 60 + "Minutes "+
                                millisUntilFinished / 1000 % 60 + "Seconds");

        }
    }

}
