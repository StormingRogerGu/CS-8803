package group_ant.cs_8803_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by guxiaofeng on 10/10/17.
 */

public class task_setting_activity extends Activity{

    private Button confirm;
    private TextView Duedate;
    private TextView ReminderTime;
    private TextView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_setting);
        confirm = (Button)findViewById(R.id.confirm_btn);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.confirm_btn:

        }

    }

}
