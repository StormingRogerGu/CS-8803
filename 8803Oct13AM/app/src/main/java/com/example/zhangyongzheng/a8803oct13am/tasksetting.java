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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by guxiaofeng on 10/12/17.
 */

public class tasksetting extends Activity{
    private TextView showDate = null;
    private Button pickDate = null;
    private TextView showTime = null;
    private Button pickTime = null;
    private EditText taskname = null;
    private Button confirm_btn;
    private EditText tasknote = null;
    private DatabaseReference myDatabase;
    public String User_id;
    private User_id my_usr_id;

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

        myDatabase = FirebaseDatabase.getInstance().getReference("User_profile");

        initializeViews();
        setUsr_id();


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
                String user_id = User_id.toString();
                String due_date = (String)showDate.getText();
                String task_name = taskname.getText().toString();
                String remind_time = showTime.getText().toString();
                String task_note = tasknote.getText().toString();

                Log.v("Userid",user_id);


                write_task_data(user_id, task_name, due_date, remind_time, task_note);

                Log.v("key",due_date);
                Intent intent = new Intent(tasksetting.this,MainActivity.class);
                Bundle bundle = new Bundle();

//                bundle.putString("due_date",due_date);
//                bundle.putString("task_name",task_name);
                bundle.putString("usr_id", user_id);
                intent.putExtras(bundle);

                startActivity(intent);
                finish();

            }
        });
    }

    public void initializeViews(){
        showDate = (TextView) findViewById(R.id.show_date);
        pickDate = (Button) findViewById(R.id.pick_date);
        showTime = (TextView) findViewById(R.id.show_time);
        pickTime = (Button) findViewById(R.id.pick_time);
        taskname = (EditText)findViewById(R.id.task_name);
        tasknote = (EditText)findViewById(R.id.add_content);

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

    public class Task_detail{
        //public String user_id;
        //public String task_name;
        public String due_date;
        public String remind_time;
        public String task_note;

        public Task_detail(){

        }

        public Task_detail(String due_date, String remind_time, String task_note) {
            //this.user_id = user_id;
            //this.task_name = task_name;
            this.due_date = due_date;
            this.remind_time = remind_time;
            this.task_note = task_note;
        }
    }

    private void write_task_data(String user_id, String task_name, String due_date, String remind_time, String task_note){

        //Task_detail task_detail = new Task_detail(due_date, remind_time, task_note);

        //myDatabase.child(user_id).child(task_name).setValue(task_detail);

        myDatabase.child(user_id).child("Task").child(task_name).child("due_date").setValue(due_date);
        myDatabase.child(user_id).child("Task").child(task_name).child("remind_time").setValue(remind_time);
        myDatabase.child(user_id).child("Task").child(task_name).child("note").setValue(task_note);
        myDatabase.child(user_id).child("Task").child(task_name).child("top").setValue(false);

    }

    public void setUsr_id(){
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        if (bundle != null) {
//
//            User_id = bundle.getString("usr_id");
//            Log.v("id_to_addtask", User_id);
//        }
        my_usr_id = (User_id) getApplication();
        User_id = my_usr_id.getUserid();

    }

}
