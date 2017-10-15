package com.example.zhangyongzheng.a8803oct13am;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private ImageButton friend;
    private ImageButton timer;
    private ImageButton home;
    private ImageButton puzzle;
    private ImageButton profile;


    private Button addtask;
    private TextView due_date;

    private static ListView lv;
    private static ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_layout);
        setUpView();

        


        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(MainActivity.this,tasksetting.class);
                startActivity(addnewtask);
            }
        });

        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(MainActivity.this,friend.class);
                startActivity(addnewtask);
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(MainActivity.this,timesetting.class);
                Drawable image = getResources().getDrawable(R.drawable.clock_icon_highlight);
                addnewtask.putExtra("new_image",drawable2Bytes(image));
                startActivity(addnewtask);

            }
        });
        puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(MainActivity.this,puzzle.class);
                Drawable image = getResources().getDrawable(R.drawable.puzzle_icon_highlight);
                addnewtask.putExtra("new_image",drawable2Bytes(image));
                startActivity(addnewtask);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(MainActivity.this,profile.class);
                startActivity(addnewtask);
            }
        });
        //due_date = (TextView)findViewById(R.id.date);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();



        myadapter myownadapter = new myadapter(this, listItem);

        if(bundle != null){
            Log.v("test",bundle.getString("due_date"));
            Log.v("test2",bundle.getString("task_name"));
            //due_date.setText(bundle.getString("due_date"));
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemText",bundle.getString("task_name"));
            map.put("ItemTitle",bundle.getString("due_date"));
            map.put("ItemImage",R.drawable.up_button_default);
            listItem.add(map);

        }
        //initiData();


        myownadapter.updateData(listItem);
        lv.setAdapter(myownadapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("test","click click");

            }
        });


    }

    private void setUpView(){

        friend = (ImageButton)findViewById(R.id.friend_bar);
        timer = (ImageButton)findViewById(R.id.timer);
        home = (ImageButton)findViewById(R.id.home);
        puzzle = (ImageButton)findViewById(R.id.puzzle);
        profile = (ImageButton)findViewById(R.id.profile);
        addtask = (Button)findViewById(R.id.addnewtask);
        lv = (ListView)findViewById(R.id.listview1);
    }

    public static byte[] drawable2Bytes(Drawable drawable) {


        if (drawable == null) {
            return null;
        }
        Bitmap bitmap = drawableToBitmap(drawable);


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
    public static Bitmap drawableToBitmap(Drawable drawable) {


        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private void initiData(){
        listItem = new ArrayList<HashMap<String, Object>>();

//        for(int i = 0; i<100;i++){
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("ItemImage",R.mipmap.ic_launcher);
//            map.put("ItemTitle",i + "th lane");
//            map.put("ItemText", "this is" + i + "th lane");
//            listItem.add(map);
//        }
    }




}

