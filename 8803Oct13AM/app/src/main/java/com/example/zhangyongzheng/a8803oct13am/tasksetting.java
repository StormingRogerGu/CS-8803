package com.example.zhangyongzheng.a8803oct13am;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Date;

/**
 * Created by guxiaofeng on 10/12/17.
 */

public class tasksetting extends Activity{
    private TextView showDate = null;
    private Button pickDate = null;
    private TextView showTime = null;
    private Button pickTime = null;
    private Button confirm_btn;

    private static final int SHOW_DATAPICK = 0;
    private static final int DATE_DIALOG_ID = 1;
    private static final int SHOW_TIMEPICK = 2;
    private static final int TIME_DIALOG_ID = 3;

    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.task_setting);

        initializeViews();

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        setDateTime();
        setTimeOfDay();
        confirm_task();
    }

    public void confirm_task(){
        confirm_btn = (Button)findViewById(R.id.confirm_button);
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String due_date = (String)showDate.getText();
                Log.v("key",due_date);
                Intent intent = new Intent(tasksetting.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key",due_date);

                intent.putExtras(bundle);

                startActivity(intent);




            }
        });
    }

    public void initializeViews(){
        showDate = (TextView) findViewById(R.id.show_date);
        pickDate = (Button) findViewById(R.id.pick_date);
        showTime = (TextView) findViewById(R.id.show_time);
        pickTime = (Button) findViewById(R.id.pick_time);

        pickDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Message msg = new Message();
                if (pickDate.equals((Button) v)){
                    msg.what = tasksetting.SHOW_DATAPICK;
                }
                tasksetting.this.dateandtimeHandler.sendMessage(msg);
            }
        });

        pickTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Message msg = new Message();
                if (pickTime.equals((Button) v)){
                    msg.what = tasksetting.SHOW_TIMEPICK;
                }
                tasksetting.this.dateandtimeHandler.sendMessage(msg);
            }
        });
    }

    private void setDateTime(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        updateDateDisplay();
    }

    private void updateDateDisplay(){
        showDate.setText(new StringBuilder().append(mYear).append("-")
                .append((mMonth+1) < 10 ? "0" + (mMonth+1) : (mMonth+1)).append("-")
                .append((mDay < 10) ? "0" + mDay : mDay));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;

            updateDateDisplay();
        }
    };

    private void setTimeOfDay(){
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        updateTimeDisplay();
    }

    private void updateTimeDisplay(){
        showTime.setText(new StringBuilder().append(mHour).append(":")
                .append((mMinute < 10) ? "0" + mMinute : mMinute));
    }

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener(){

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mHour = hourOfDay;
            mMinute = minute;
            updateTimeDisplay();
        }
    };

    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute, true);
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
                break;
            case TIME_DIALOG_ID:
                ((TimePickerDialog) dialog).updateTime(mHour, mMinute);
                break;
        }
    }

    Handler dateandtimeHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case tasksetting.SHOW_DATAPICK:
                    showDialog(DATE_DIALOG_ID);
                    break;
                case tasksetting.SHOW_TIMEPICK:
                    showDialog(TIME_DIALOG_ID);
                    break;
            }
        }

    };

//    private String dateprase(String origin_date){
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date nowDate = null;
//        try{
//            nowDate = df.parse(origin_date);
//        }catch (ParseException e){
//            e.printStackTrace();
//        }
//
//    }


}
