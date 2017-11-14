package com.example.zhangyongzheng.a8803oct13am;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhangyongzheng on 10/13/17.
 */

public class friend extends Activity {



    private ImageButton friend;
    private ImageButton timer;
    private ImageButton home;
    private ImageButton puzzle;
    private ImageButton profile;

    private ListView lv;
    private TextView friend_title;
    private TextView current_username;
    private TextView current_user_rank;
    private ImageView current_img;
    private TextView current_rank_level;
    private TextView current_user_finish_puzzle;
    private TextView current_user_ongoing_pieces;

    private ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
    private List<String> listkey = new ArrayList<String>();
    private List<friend_detail> listvalue = new ArrayList<friend_detail>();

    private DatabaseReference myRef;
    private DatabaseReference myRef2;
    private DatabaseReference mylargeRef;
    private String usr_id;
    private User_id my_usr_id;

    public order_num_of_puzzle my_order_of_puzzle = new order_num_of_puzzle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend);

        setUpView();

        setUsr_id();
        current_username.setText(usr_id);


        final friend_adapter my_friend_adapter = new friend_adapter(this,listItem);

        mylargeRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot post : dataSnapshot.getChildren()){
                    String key = post.getKey();
                    Log.v(key,"hahhaha");
                    listkey.add(key);
                    int num_of_finish = post.child("Time_mode").child("puzzle_ongoing_id").child("puzzle_piece_id").getValue(int.class);
                    int num_of_ongoing = post.child("Time_mode").child("puzzle_ongoing_id").child("puzzle_piece_ongoing").getValue(int.class);
                    friend_detail temp_friend_detail = new friend_detail(key,num_of_finish,num_of_ongoing);
                    listvalue.add(temp_friend_detail);
                }
                Log.v("what is worng","hehe");

                initialdata();
                listvalue.clear();
                listkey.clear();

                my_friend_adapter.updateData(listItem);
                lv.setAdapter(my_friend_adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(friend.this,friend.class);
                startActivity(addnewtask);
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(friend.this,timesetting.class);
//                Drawable image = getResources().getDrawable(R.drawable.clock_icon_highlight);
//                addnewtask.putExtra("new_image",drawable2Bytes(image));
                startActivity(addnewtask);
            }
        });
        puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(friend.this,puzzle.class);
//                Drawable image = getResources().getDrawable(R.drawable.puzzle_icon_highlight);
//                addnewtask.putExtra("new_image",drawable2Bytes(image));
                startActivity(addnewtask);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(friend.this,profile.class);
                startActivity(addnewtask);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(friend.this,MainActivity.class);
                startActivity(addnewtask);
            }
        });
    }

    private void setUpView(){

        friend = (ImageButton)findViewById(R.id.friend_bar_rank_highlight);
        timer = (ImageButton)findViewById(R.id.timer_rank_highlight);
        home = (ImageButton)findViewById(R.id.home_rank_highlight);
        puzzle = (ImageButton)findViewById(R.id.puzzle_rank_highlight);
        profile = (ImageButton)findViewById(R.id.profile_rank_highlight);

        lv = (ListView)findViewById(R.id.friend_listview_layout_contant);
        friend_title = (TextView)findViewById(R.id.friend_title_layout_textview);
        current_username = (TextView)findViewById(R.id.friend_current_user_name);
        current_user_rank = (TextView)findViewById(R.id.current_rank_index);

        current_img = (ImageView)findViewById(R.id.current_rank_img);
        current_rank_level = (TextView)findViewById(R.id.friend_current_user_level);

        current_user_finish_puzzle = (TextView)findViewById(R.id.current_rank_item_finish);
        current_user_ongoing_pieces = (TextView)findViewById(R.id.current_rank_item_ongoing);

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

    public void setUsr_id(){

        my_usr_id = (User_id) getApplication();
        usr_id = my_usr_id.getUserid();
        mylargeRef = FirebaseDatabase.getInstance().getReference("User_profile");
        myRef = FirebaseDatabase.getInstance().getReference("User_profile").child(usr_id).child("Time_mode");
        myRef2 = FirebaseDatabase.getInstance().getReference("User_profile").child(usr_id).child("Task");
    }

    public void initialdata(){
        Collections.sort(listvalue,my_order_of_puzzle);

        for(int i = 0; i<listvalue.size(); i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("all_username",listvalue.get(i).all_username);
            map.put("num_of_finish_puzzle",listvalue.get(i).num_of_finish_puzzle);
            map.put("num_of_on_going_puzzle",listvalue.get(i).num_of_on_going_puzzle);
            if(listvalue.get(i).num_of_on_going_puzzle >= 0 && listvalue.get(i).num_of_on_going_puzzle <= 20){
                map.put("ItemImage",R.drawable.ant_level_1);
                map.put("level","level 1");
            }
            else if(listvalue.get(i).num_of_on_going_puzzle >= 21 && listvalue.get(i).num_of_on_going_puzzle <= 40){
                map.put("ItemImage",R.drawable.ant_level_2);
                map.put("level","level 2");
            }
            else if(listvalue.get(i).num_of_on_going_puzzle >= 41 && listvalue.get(i).num_of_on_going_puzzle <= 60){
                map.put("ItemImage",R.drawable.ant_level_3);
                map.put("level","level 3");
            }
            else if(listvalue.get(i).num_of_on_going_puzzle >= 61 && listvalue.get(i).num_of_on_going_puzzle <= 80){
                map.put("ItemImage",R.drawable.ant_level_4);
                map.put("level","level 4");
            }
            else if(listvalue.get(i).num_of_on_going_puzzle >= 81){
                map.put("ItemImage",R.drawable.ant_level_5);
                map.put("level","level 5");
            }
            if(listvalue.get(i).all_username.equals(usr_id)){
                int k = i+1;
                current_user_rank.setText("" + k);
                current_user_finish_puzzle.setText(""+listvalue.get(i).num_of_finish_puzzle);
                current_user_ongoing_pieces.setText(""+listvalue.get(i).num_of_on_going_puzzle);
                if(listvalue.get(i).num_of_on_going_puzzle >= 0 && listvalue.get(i).num_of_on_going_puzzle <= 20){
                    current_img.setImageResource(R.drawable.ant_level_1);
                    current_rank_level.setText("Level 1");
                }
                else if(listvalue.get(i).num_of_on_going_puzzle >= 21 && listvalue.get(i).num_of_on_going_puzzle <= 40){
                    current_img.setImageResource(R.drawable.ant_level_2);
                    current_rank_level.setText("Level 2");

                }
                else if(listvalue.get(i).num_of_on_going_puzzle >= 41 && listvalue.get(i).num_of_on_going_puzzle <= 60){
                    current_img.setImageResource(R.drawable.ant_level_3);
                    current_rank_level.setText("Level 3");

                }
                else if(listvalue.get(i).num_of_on_going_puzzle >= 61 && listvalue.get(i).num_of_on_going_puzzle <= 80){
                    current_img.setImageResource(R.drawable.ant_level_4);
                    current_rank_level.setText("Level 4");

                }
                else if(listvalue.get(i).num_of_on_going_puzzle >= 81){
                    current_img.setImageResource(R.drawable.ant_level_5);
                    current_rank_level.setText("Level 5");

                }

            }
            map.put("index","" + (i+1));

            listItem.add(map);
        }



    }

}
