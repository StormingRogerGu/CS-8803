package com.example.zhangyongzheng.a8803oct13am;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.*;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by zhangyongzheng on 10/13/17.
 */


public class puzzle extends Activity {

    private ImageButton friend;
    private ImageButton timer;
    private ImageButton home;
    private ImageButton puzzle;
    private ImageButton profile;

    private String usr_id;
    private User_id my_usr_id;

    private DatabaseReference myRef;
    private DatabaseReference myRef2;
    private DatabaseReference myRef3;

    private int ongoing_id_now;

    private ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;
    private Button go_to_complete_puzzle_btn;
    private TextView tx1;

    private StorageReference storageref = FirebaseStorage.getInstance().getReference().child("pic1").child("puzzle_pic0_1.png");
    private String puzzle_file = "pic";
    private String puzzle_pic = "puzzle_pic0_";
    private int ongoing_id_now_cur;
    int counter=9;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle_gallery);
        setUpView();
        setUsr_id();

        go_to_complete_puzzle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(puzzle.this, Complete_puzzle_show.class);
                startActivity(intent);
            }
        });
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ongoing_id_now = dataSnapshot.child("puzzle_ongoing_id").child("puzzle_piece_ongoing").getValue(int.class);

                ongoing_id_now_cur = dataSnapshot.child("puzzle_ongoing_id").child("puzzle_piece_ongoing").getValue(int.class);
                ongoing_id_now_cur = ongoing_id_now_cur % 9;

//                int file_num = ongoing_id_now / 9 + 1;
//                int comp_id = ongoing_id_now / 9;
//                if(comp_id > 0) {
//                    Log.v("completed puzzle","fdaf"+file_num);
//                    myRef3.child("Completed_id").setValue(file_num);
//                }
                ongoing_id_now = ongoing_id_now % 9;

                Log.v("ongoing num", "total"+ongoing_id_now);
                int file_num = ongoing_id_now / 9 + 1;
                Log.v("ongoingfile", "total"+file_num);
                int comp_id = ongoing_id_now / 9;
                Log.v("ongoingcompid", "total"+comp_id);
                if(file_num > 0) {
                    Log.v("completed puzzle","fdaf"+file_num);
                    myRef3.child("Completed_id").setValue(file_num);
                }

                Log.v("what id","this" + ongoing_id_now + "hh");
                for(int i = 0; i < ongoing_id_now_cur; i++) {
                    Log.v("wrong?","shit!");
                    int k = i + 1;
                    String puzzle_file_id = puzzle_file + file_num;
                    String puzzle_piece_id = puzzle_pic + k +".png";
                    Log.v("piece_id",puzzle_piece_id);
                    StorageReference temp_storageref = FirebaseStorage.getInstance().getReference().child(puzzle_file_id).child(puzzle_piece_id);


                    if (i == 0) {
                        Glide.with(puzzle.this).using(new FirebaseImageLoader()).load(temp_storageref).into(img1);
                        continue;
                    }
                    if (i == 1) {
                        Glide.with(puzzle.this).using(new FirebaseImageLoader()).load(temp_storageref).into(img2);
                        continue;
                    }
                    if (i == 2) {
                        Glide.with(puzzle.this).using(new FirebaseImageLoader()).load(temp_storageref).into(img3);
                        continue;
                    }
                    if (i == 3) {
                        Glide.with(puzzle.this).using(new FirebaseImageLoader()).load(temp_storageref).into(img4);
                        continue;
                    }
                    if (i == 4) {
                        Glide.with(puzzle.this).using(new FirebaseImageLoader()).load(temp_storageref).into(img5);
                        continue;
                    }
                    if (i == 5) {
                        Glide.with(puzzle.this).using(new FirebaseImageLoader()).load(temp_storageref).into(img6);
                        continue;
                    }
                    if (i == 6) {
                        Glide.with(puzzle.this).using(new FirebaseImageLoader()).load(temp_storageref).into(img7);
                        continue;
                    }
                    if (i == 7) {
                        Glide.with(puzzle.this).using(new FirebaseImageLoader()).load(temp_storageref).into(img8);
                        continue;
                    }
                    if (i == 8) {
                        Glide.with(puzzle.this).using(new FirebaseImageLoader()).load(temp_storageref).into(img9);
                        continue;
                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        

//        bt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(counter==9) {
//                    img1.setImageResource(R.drawable.pic1);
//                    counter--;
//                }
//                else if(counter==8) {
//                    img2.setImageResource(R.drawable.pic2);
//                    counter--;
//                }
//                else if(counter==7) {
//                    img3.setImageResource(R.drawable.pic3);
//                    counter--;
//                }
//                else if(counter==6) {
//                    img4.setImageResource(R.drawable.pic4);
//                    counter--;
//                }
//                else if(counter==5) {
//                    img5.setImageResource(R.drawable.pic5);
//                    counter--;
//                }
//                else if(counter==4) {
//                    img6.setImageResource(R.drawable.pic6);
//                    counter--;
//                }
//                else if(counter==3) {
//                    img7.setImageResource(R.drawable.pic7);
//                    counter--;
//                }
//                else if(counter==2) {
//                    img8.setImageResource(R.drawable.pic8);
//                    counter--;
//                }
//                else if(counter==1) {
//                    img9.setImageResource(R.drawable.pic9);
//                    tx1.setVisibility(View.VISIBLE);
//                    tx1.setBackgroundColor(Color.RED);
//                }
//            }
//        });


        //change icon
//        Intent intent = getIntent();
//        byte[] new_icon = intent.getByteArrayExtra("new_image");
//        Bitmap bitmap = BitmapFactory.decodeByteArray(new_icon,0,new_icon.length);
//        puzzle.setImageBitmap(bitmap);



        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(puzzle.this,friend.class);
                startActivity(addnewtask);
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(puzzle.this,timesetting.class);
                Drawable image = getResources().getDrawable(R.drawable.clock_icon_highlight);
                addnewtask.putExtra("new_image",drawable2Bytes(image));
                startActivity(addnewtask);
            }
        });
        puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(puzzle.this,puzzle.class);
                startActivity(addnewtask);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(puzzle.this,profile.class);
                startActivity(addnewtask);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(puzzle.this,MainActivity.class);
                startActivity(addnewtask);
            }
        });


    }

    private void setUpView(){

        friend = (ImageButton)findViewById(R.id.friend_bar);
        timer = (ImageButton)findViewById(R.id.timer);
        home = (ImageButton)findViewById(R.id.home);
        puzzle = (ImageButton)findViewById(R.id.puzzle);
        profile = (ImageButton)findViewById(R.id.profile);
        img1=(ImageView)findViewById(R.id.puzzle_1);
        img2=(ImageView)findViewById(R.id.puzzle_2);
        img3=(ImageView)findViewById(R.id.puzzle_3);
        img4=(ImageView)findViewById(R.id.puzzle_4);
        img5=(ImageView)findViewById(R.id.puzzle_5);
        img6=(ImageView)findViewById(R.id.puzzle_6);
        img7=(ImageView)findViewById(R.id.puzzle_7);
        img8=(ImageView)findViewById(R.id.puzzle_8);
        img9=(ImageView)findViewById(R.id.puzzle_9);
        tx1=(TextView)findViewById(R.id.textView);
        tx1.setVisibility(View.GONE);
        go_to_complete_puzzle_btn=(Button)findViewById(R.id.puzzle_galery_goto_completed);



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
        myRef = FirebaseDatabase.getInstance().getReference("User_profile").child(usr_id).child("Time_mode");
        myRef2 = FirebaseDatabase.getInstance().getReference("User_profile").child(usr_id).child("Time_mode").child("puzzle_ongonig_id");
        myRef3 = FirebaseDatabase.getInstance().getReference("User_profile").child(usr_id).child("Time_mode").child("puzzle_comp_id");
    }

}
