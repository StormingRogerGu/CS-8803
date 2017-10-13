package group_ant.cs_8803_android;

<<<<<<< HEAD
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
=======
>>>>>>> parent of b20e89b... puzzle_gallery
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); HEAD
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


=======
        setContentView(R.layout.activity_main);
    }
>>>>>>> parent of b20e89b... puzzle_gallery
}
