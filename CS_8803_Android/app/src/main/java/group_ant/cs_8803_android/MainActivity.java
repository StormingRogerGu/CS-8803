package group_ant.cs_8803_android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

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
        Home = (Button)findViewById(R.id.home);
        TimeMode = (Button)findViewById(R.id.time);
        Setting = (Button)findViewById(R.id.setting);
        addtask = (Button)findViewById(R.id.add);

        addtask.setOnClickListener(listener);

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, task_setting.class);
            startActivity(intent);
        }
    };


}
