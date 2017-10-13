package group_ant.android_project;

import java.sql.Time;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

/**
 * Created by guxiaofeng on 10/12/17.
 */

public class tasksetting extends Activity implements View.OnTouchListener{

    private EditText Duedate;
    private EditText remind_time;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_setting);

        Duedate = (EditText) this.findViewById(R.id.Due_date);
        remind_time = (EditText) this.findViewById(R.id.Remind_time);

        Duedate.setOnTouchListener(this);
        remind_time.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = View.inflate(this, R.layout.task_setting,null);
            final DatePicker datePicker = (DatePicker) view.findViewById(R.id.Due_date);
            final TimePicker timePicker = (android.widget.TimePicker) view.findViewById(R.id.Remind_time);
            builder.setView(view);

            Calendar cal = 
        }
        return false;
    }
}
