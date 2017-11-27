package com.example.zhangyongzheng.a8803oct13am;

import android.content.Entity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    private ImageButton friend;
    private ImageButton timer;
    private ImageButton home;
    private ImageButton puzzle;
    private ImageButton profile;

    private Button addtask;
    private Button refreshbtn;
    private TextView due_date;
    private String usr_id;
    private DatabaseReference myRef;
    private DatabaseReference myRef2;
    private User_id my_usr_id;

    final String TAG = "FireDatabase";

    //private Object retirve_Data = retirvedata.newInstance();

    private ListView lv;
    private ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

    //private HashMap<String, Task_detail> mylist = new HashMap<String, Task_detail>();

//    private FirebaseDatabase database = FirebaseDatabase.getInstance();
//    private DatabaseReference myRef1 = database.getReference("User_profile");
//    private DatabaseReference myRef2 = myRef1.child(usr_id);
//    private DatabaseReference myRef = myRef2.child("Task");
    public List<String> listkey = new ArrayList<String>();
    public List<Task_detail> listvalue = new ArrayList<Task_detail>();

    public orderduedate fororder = new orderduedate();
    private HashMap<String, Task_detail> original = new HashMap<String, Task_detail>();
    private List<Map.Entry<String, Task_detail>> copytodelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //去除状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.task_layout);

        setUpView();

        setUsr_id();
        final myadapter myownadapter = new myadapter(this, listItem, mListener,myFlagClickListener);

        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("usr_id", usr_id);
                addnewtask.putExtras(bundle);
                addnewtask.setClass(MainActivity.this,tasksetting.class);
                startActivity(addnewtask);
                finish();
            }
        });

        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(MainActivity.this,friend.class);
                startActivity(addnewtask);
                finish();
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(MainActivity.this,timesetting.class);
                startActivity(addnewtask);
                finish();

            }
        });
        puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(MainActivity.this,puzzle.class);
                startActivity(addnewtask);
                finish();

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(MainActivity.this,profile.class);
                startActivity(addnewtask);
                finish();
            }
        });

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot post: dataSnapshot.getChildren()){
                    //Log.v("retdata","zzzzzzz");
                    String key = post.getKey();
                    List<String> temp = new ArrayList<String>();
                    for (DataSnapshot postson : post.getChildren()){
                        String s = postson.getValue(String.class);
                        temp.add(s);

                    }

                    String s1 = temp.get(0);
                    //Log.v("first data",s1);
                    String s2 = temp.get(1);
                    //Log.v("2nd data",s2);
                    String s3 = temp.get(2);
                    //Log.v("3rd data",s3);
                    String s4 = temp.get(3);
                    Task_detail dt =new Task_detail(s1,s2,s3,s4);
                    listkey.add(key);
                    //Task_detail dt = new Task_detail(post.child("due_date").getValue().toString(),post.child("note").getValue().toString(),post.child("remind_time").getValue().toString());

                    //Log.v("retdata",key);
                    listvalue.add(dt);

                }

                initdata();
                listvalue.clear();
                listkey.clear();

                Log.v("afterinit","go");



                myownadapter.updateData(listItem);
                lv.setAdapter(myownadapter);

                Log.v("afterlistview","go");
                for(int i = 0; i<listvalue.size();i++){
                    Log.v("lllllllll",listvalue.get(i).due_date);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("test","click click");

            }
        });



    }

    private void setUpView(){

        friend = (ImageButton)findViewById(R.id.friend_bar_tasklayout_highlight);
        timer = (ImageButton)findViewById(R.id.timer_tasklayout_highlight);
        home = (ImageButton)findViewById(R.id.home_tasklayout_highlight);
        puzzle = (ImageButton)findViewById(R.id.puzzle_tasklayout_highlight);
        profile = (ImageButton)findViewById(R.id.profile_tasklayout_highlight);
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

    public void initdata(){

        //HashMap<String, Task_detail> original = new HashMap<String, Task_detail>();
        for(int i = 0; i<listkey.size();i++){
            String key = listkey.get(i);
            Task_detail td = listvalue.get(i);
            original.put(key,td);
        }
        List<Map.Entry<String, Task_detail>> orderdate = new ArrayList<Map.Entry<String, Task_detail>>(original.entrySet());
        Collections.sort(orderdate,fororder);

        for (int i = 0; i < listkey.size(); i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemText", orderdate.get(i).getKey());
            map.put("ItemTitle", (String)orderdate.get(i).getValue().due_date);
            if(orderdate.get(i).getValue().task_top.equals("false")){
                map.put("ItemImage", R.drawable.task_top_gray);
            }
            else{
                map.put("ItemImage", R.drawable.task_top_red);
            }

            listItem.add(map);
        }
        copytodelete = orderdate;

    }


    public void setUsr_id(){
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        if (bundle != null){
//            usr_id = bundle.getString("usr_id");
//            Log.v("id_to_main", usr_id);
//        }

        my_usr_id = (User_id) getApplication();
        usr_id = my_usr_id.getUserid();
        myRef = FirebaseDatabase.getInstance().getReference("User_profile").child(usr_id).child("Task");
        myRef2 = FirebaseDatabase.getInstance().getReference("User_profile").child(usr_id).child("Task");
    }

    private myadapter.MyClickListener mListener = new myadapter.MyClickListener() {
        @Override
        public void myOnClick(int position, View v) {
            myRef.child(copytodelete.get(position).getKey()).removeValue();
            Intent jump = new Intent(MainActivity.this, MainActivity.class);
            startActivity(jump);

        }
    };

    final String[] state_top = new String[1];

    private myadapter.MyFlagClickListener myFlagClickListener = new myadapter.MyFlagClickListener() {


        @Override
        public void myflagOnClick(int position, View v) {
            final DatabaseReference temp = myRef.child(copytodelete.get(position).getKey()).child("top");
            temp.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    state_top[0] = dataSnapshot.getValue(String.class);
                    //state = state_top[0]
                    Log.v("Top_state",state_top[0]);
                    if (state_top[0].equals("false")){
                        temp.setValue("true");
                        Intent jump = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(jump);
                    }
                    else if (state_top[0].equals("true")){
                        temp.setValue("false");
                        Intent jump = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(jump);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
    };

    public void refresh(){


    }

}

