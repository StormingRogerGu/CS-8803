package group_ant.cs_8803_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button Friend;
    private Button Home;
    private Button TimeMode;
    private Button Setting;
    private Button addtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_layout);

        Friend = (Button)findViewById(R.id.friend);
        Home = (Button)findViewById(R.id.task);
        TimeMode = (Button)findViewById(R.id.time);
        Setting = (Button)findViewById(R.id.setting);
        addtask = (Button)findViewById(R.id.add)

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.friend:
                break;
            case R.id.task:
                break;
            case R.id.add:
                final Intent addtask = new Intent()

        }

    }
}
