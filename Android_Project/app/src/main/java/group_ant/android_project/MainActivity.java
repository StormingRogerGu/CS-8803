package group_ant.android_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button friend;
    private Button timer;
    private Button home;
    private Button tasklist;
    private Button profile;
    private Button addtask;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpView();

        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(MainActivity.this,tasksetting.class);
                startActivity(addnewtask);
            }
        });
    }

    private void setUpView(){
        setContentView(R.layout.task_layout);
        friend = (Button)findViewById(R.id.friend);
        timer = (Button)findViewById(R.id.timer);
        home = (Button)findViewById(R.id.home);
        tasklist = (Button)findViewById(R.id.puzzle);
        addtask = (Button)findViewById(R.id.add_new_task);
    }

    

    
}
