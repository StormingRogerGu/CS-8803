package group_ant.android_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button friend;
    private Button timer;
    private Button home;
    private Button tasklist;
    private Button profile;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_layout);
        friend = (Button)findViewById(R.id.friend);
        timer = (Button)findViewById(R.id.timer);
        home = (Button)findViewById(R.id.home);
        tasklist = (Button)findViewById(R.id.puzzle);
    }
}
