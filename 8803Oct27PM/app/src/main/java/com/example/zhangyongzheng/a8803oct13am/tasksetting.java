package com.example.zhangyongzheng.a8803oct13am;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.Manifest;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by guxiaofeng on 10/12/17.
 */

public class tasksetting extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{
    private TextView showDate = null;
    private Button pickDate = null;
    private TextView showTime = null;
    private Button pickTime = null;
    private EditText taskname = null;
    private Button confirm_btn;
    private Button add_to_calendar;


    private EditText tasknote = null;
    private DatabaseReference myDatabase;
    public String User_id;
    private User_id my_usr_id;
    private Button back_btn;

    private static final int SHOW_DATAPICK = 0;
    private static final int DATE_DIALOG_ID = 1;
    private static final int SHOW_TIMEPICK = 2;
    private static final int TIME_DIALOG_ID = 3;

    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    private static String calendarURL = "content://com.android.calendar/calendars";
    private static String calendarEventURL = "content://com.android.calendar/events";
    private static String calendarRemiderURL = "content://com.android.calendar/reminders";

    private static String CALENDARS_NAME = "test";
    private static String CALENDARS_ACCOUNT_NAME = "test@gmail.com";
    private static String CALENDARS_ACCOUNT_TYPE = "com.android.exchange";
    private static String CALENDARS_DISPLAY_NAME = "Ants";

    private static long ONE_HOUR = 3600;



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
        //Log.v("year","kk" + mYear);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.READ_CALENDAR)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
//                    Manifest.permission.READ_CONTACTS)) {
//
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//
//            } else {
//
//                // No explanation needed, we can request the permission.
//
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.READ_CONTACTS},
//                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        }

        setDateTime();
        back_to_Main();
        setTimeOfDay();
        confirm_task();
        add_event_to_calendar(this,"deadline","try best",11);
    }
    public void back_to_Main(){
        back_btn = (Button)findViewById(R.id.back_button_task);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tasksetting.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void add_event_to_calendar(final Context context, final String title, final String description, final long beginTime){
        final int cal_Id = checkAndAddCalendarAccount(context);

        if(cal_Id < 0){
            Log.v("faill to add evet","calendar");
            return;
        }
        Log.v("faill to add evet2","2calendar");

        add_to_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String calId = "";
                Cursor userCursor = getContentResolver().query(Uri.parse(calendarURL), null, null, null, null);
                if (userCursor.getCount() > 0) {
                    userCursor.moveToLast();  //your account
                    calId = userCursor.getString(userCursor.getColumnIndex("_id"));
                    Log.v("calid",calId);
                }
                else {
                    Toast.makeText(tasksetting.this, "Please add a new account", Toast.LENGTH_LONG).show();
                    return;
                }

                ContentValues event = new ContentValues();
                event.put("title", taskname.getText().toString());
                event.put("description", tasknote.getText().toString());
                // insert a account
                event.put("calendar_id", calId);
                System.out.println("calId: " + calId);
                event.put("eventLocation", "Earth");

                String month = showDate.getText().toString().substring(5,7);
                int month_num = Integer.parseInt(month);
                String year = showDate.getText().toString().substring(0,4);
                int year_num = Integer.parseInt(year);
                //Log.v("year",year);
                String date = showDate.getText().toString().substring(8,10);
                int date_num = Integer.parseInt(date);
                //Log.v("date",date);
                String hour = showTime.getText().toString().substring(0,2);
                int hour_num = Integer.parseInt(hour);
                Log.v("hour",hour);
                String minute = showTime.getText().toString().substring(3,5);
                int minute_num = Integer.parseInt(minute);
                Log.v("minute",minute);

                Calendar mCalendar = Calendar.getInstance();
                mCalendar.set(Calendar.MONTH,month_num - 1);
                mCalendar.set(Calendar.YEAR,year_num);
                mCalendar.set(Calendar.DATE,date_num);
                mCalendar.set(Calendar.HOUR_OF_DAY, hour_num);
                mCalendar.set(Calendar.MINUTE, minute_num);
                long start = mCalendar.getTime().getTime();
                mCalendar.set(Calendar.HOUR_OF_DAY, hour_num + 1);
                long end = mCalendar.getTime().getTime();

                event.put("dtstart", start);
                event.put("dtend", end);
                event.put("hasAlarm", 1);

                event.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Shanghai");  //time zone，
                //add event
                Uri newEvent = getContentResolver().insert(Uri.parse(calendarEventURL), event);
                //reminder setting
                long id = Long.parseLong(newEvent.getLastPathSegment());
                ContentValues values = new ContentValues();
                values.put("event_id", id);
                // reminde 10 minutes earlier
                values.put("minutes", 10);
                getContentResolver().insert(Uri.parse(calendarRemiderURL), values);

                Toast.makeText(tasksetting.this, "Insert a new event!!!", Toast.LENGTH_LONG).show();



//                ContentValues event = new ContentValues();
//                event.put("title",title);
//                event.put("description",description);
//                event.put("calendar_id",calId);
//                Calendar mCalendar = Calendar.getInstance();
//                mCalendar.setTimeInMillis(beginTime);
//                long start = mCalendar.getTime().getTime();
//                mCalendar.setTimeInMillis(start + ONE_HOUR);
//                long end = mCalendar.getTime().getTime();
//
//                event.put(CalendarContract.Events.DTSTART,start);
//                event.put(CalendarContract.Events.DTEND, end);
//                event.put(CalendarContract.Events.HAS_ALARM, 1);
//                event.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Shanghai");
//
//                Uri newEvent = context.getContentResolver().insert(Uri.parse(calendarEventURL), event);
//                if (newEvent == null) {
//                    return;
//                }
//                ContentValues values = new ContentValues();
//                values.put(CalendarContract.Reminders.EVENT_ID, ContentUris.parseId(newEvent));
//
//                values.put(CalendarContract.Reminders.MINUTES, 10);
//                values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
//                Uri uri = context.getContentResolver().insert(Uri.parse(calendarRemiderURL), values);
//                if(uri == null) {
//
//                    return;
//                }


            }
        });
    }

    public static int chechCalendarAccount(Context context){
        Cursor userCursor = context.getContentResolver().query(Uri.parse(calendarURL),null,null,null,null);
        try{
            if(userCursor == null){
                return -1;
            }
            int count = userCursor.getCount();
            if(count >0){
                userCursor.moveToFirst();
                return userCursor.getInt(userCursor.getColumnIndex(CalendarContract.Calendars._ID));

            }
            else {
                return -1;
            }
        }
        finally {
            if(userCursor != null){
                userCursor.close();
            }
        }
    }
    public static long addCalendarAccount(Context context){
        TimeZone timeZone = TimeZone.getDefault();
        ContentValues value = new ContentValues();
        value.put(CalendarContract.Calendars.NAME,CALENDARS_NAME);
        value.put(CalendarContract.Calendars.ACCOUNT_NAME,CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.ACCOUNT_TYPE,CALENDARS_ACCOUNT_TYPE);
        value.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,CALENDARS_DISPLAY_NAME);

        value.put(CalendarContract.Calendars.VISIBLE,1);
        value.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.BLUE);
        value.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
        value.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
        value.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, timeZone.getID());
        value.put(CalendarContract.Calendars.OWNER_ACCOUNT, CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 0);

        Uri calendarUri = Uri.parse(calendarURL);
        calendarUri = calendarUri.buildUpon().appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME)
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE)
                .build();
        Uri res = context.getContentResolver().insert(calendarUri,value);
        long id = res == null ?-1: ContentUris.parseId(res);
        return id;
    }

    public static int checkAndAddCalendarAccount(Context context){
        int oldId = chechCalendarAccount(context);
        if(oldId >= 0){
            return oldId;
        }
        else {
            long addId = addCalendarAccount(context);
            if(addId >= 0){
                return chechCalendarAccount(context);
            }
            else {
                return -1;
            }
        }
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

                if (!task_name.isEmpty()) {
                    write_task_data(user_id, task_name, due_date, remind_time, task_note);
                    Intent intent = new Intent(tasksetting.this,MainActivity.class);
                    //Bundle bundle = new Bundle();

//                bundle.putString("due_date",due_date);
//                bundle.putString("task_name",task_name);
//                    bundle.putString("usr_id", user_id);
//                    intent.putExtras(bundle);

                    startActivity(intent);
                    finish();
                }
                else{

                }

                Log.v("key",due_date);


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
        add_to_calendar = (Button)findViewById(R.id.add_to_calendar);

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
        showTime.setText(new StringBuilder().append((mHour < 10)? "0"+mHour : mHour).append(":")
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
        myDatabase.child(user_id).child("Task").child(task_name).child("top").setValue("false");

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
