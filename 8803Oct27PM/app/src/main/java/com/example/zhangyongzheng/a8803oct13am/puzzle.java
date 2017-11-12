package com.example.zhangyongzheng.a8803oct13am;

import android.app.Activity;
import android.app.Fragment;
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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

    private RelativeLayout puzzle_piece;
    private RelativeLayout puzzle_total_piece_show;
    private ImageView turn_image;

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

    //private StorageReference storageref = FirebaseStorage.getInstance().getReference().child("pic1").child("puzzle_pic0_1.png");
    private String puzzle_file = "pic";
    private String puzzle_pic = "puzzle_pic";
    private int ongoing_id_now_cur;
    int counter=9;

    private int index = 1;
    private FrameLayout containerView;
    private StorageReference storageref = FirebaseStorage.getInstance().getReference().child("all_complete_puzzle");



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle_gallery);
        setUpView();
        setUsr_id();

        go_to_complete_puzzle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(puzzle.this, Complete_puzzle_show.class);
//                startActivity(intent);
                if(index == 0){
                    applyRotation(0,0,90);
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            ongoing_id_now = dataSnapshot.child("puzzle_ongoing_id").child("puzzle_piece_ongoing").getValue(int.class);
                            int file_num = ongoing_id_now / 9 + 1;

                            ongoing_id_now_cur = dataSnapshot.child("puzzle_ongoing_id").child("puzzle_piece_ongoing").getValue(int.class);
                            myRef.child("puzzle_ongoing_id").child("puzzle_piece_id").setValue(ongoing_id_now_cur/9 + 1);
                            int file = dataSnapshot.child("puzzle_ongoing_id").child("puzzle_piece_ongoing").getValue(int.class);
                            file = file /9;

                            ongoing_id_now_cur = ongoing_id_now_cur % 9;

                            ongoing_id_now = ongoing_id_now % 9;



                            int comp_id = ongoing_id_now / 9;


                            Log.v("what id","this" + ongoing_id_now + "hh");
                            for(int i = 0; i < ongoing_id_now_cur; i++) {
                                Log.v("wrong?","shit!");
                                int k = i + 1;
                                String puzzle_file_id = puzzle_file + file_num;
                                String puzzle_piece_id = puzzle_pic+file +"_"+ k +".png";
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
                    index = 1;

                }
                else{
                    applyRotation(1,0,-90);
                    myRef.addListenerForSingleValueEvent(new ValueEventListener(){
                                                             @Override
                                                             public void onDataChange(DataSnapshot dataSnapshot) {
                                                                 int file = dataSnapshot.child("puzzle_ongoing_id").child("puzzle_piece_id").getValue(int.class);
                                                                 file = file - 1;
                                                                 String picture = file + ".png";
                                                                 StorageReference temp = storageref.child(picture);
                                                                 Glide.with(puzzle.this).using(new FirebaseImageLoader()).load(temp).into(turn_image);
                                                             }

                                                             @Override
                                                             public void onCancelled(DatabaseError databaseError) {

                                                             }
                                                         });
//                    String picture = 0 + ".png";
//                    StorageReference temp = storageref.child(picture);
//                    Glide.with(puzzle.this).using(new FirebaseImageLoader()).load(temp).into(turn_image);

                    index = 0;
                }

            }
        });
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ongoing_id_now = dataSnapshot.child("puzzle_ongoing_id").child("puzzle_piece_ongoing").getValue(int.class);
                int file_num = ongoing_id_now / 9 + 1;

                ongoing_id_now_cur = dataSnapshot.child("puzzle_ongoing_id").child("puzzle_piece_ongoing").getValue(int.class);

                int file = dataSnapshot.child("puzzle_ongoing_id").child("puzzle_piece_ongoing").getValue(int.class);
                file = file /9;

                myRef.child("puzzle_ongoing_id").child("puzzle_piece_id").setValue(ongoing_id_now_cur/9 + 1);

                ongoing_id_now_cur = ongoing_id_now_cur % 9;

                ongoing_id_now = ongoing_id_now % 9;



                int comp_id = ongoing_id_now / 9;

                for(int i = 0; i < ongoing_id_now_cur; i++) {

                    int k = i + 1;
                    String puzzle_file_id = puzzle_file + file_num;
                    Log.v("whatfile",puzzle_file_id);
                    String puzzle_piece_id = puzzle_pic +file +"_"+ k +".png";
                    Log.v("what",puzzle_piece_id);

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

        friend = (ImageButton)findViewById(R.id.friend_bar_puzzle_highlight);
        timer = (ImageButton)findViewById(R.id.timer_puzzle_highlight);
        home = (ImageButton)findViewById(R.id.home_puzzle_highlight);
        puzzle = (ImageButton)findViewById(R.id.puzzle_highlight);
        profile = (ImageButton)findViewById(R.id.profile_puzzle_highlight);

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
        containerView = (FrameLayout)findViewById(R.id.puzzle_continer);
        puzzle_piece = (RelativeLayout)findViewById(R.id.puzzle_pieces_relative_layout);
        puzzle_total_piece_show = (RelativeLayout)findViewById(R.id.puzzle_total_pieces);
        go_to_complete_puzzle_btn=(Button)findViewById(R.id.puzzle_galery_goto_completed);
        turn_image = (ImageView)findViewById(R.id.puzzle_total_show);



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

    public void applyRotation(int tag, float start, float end){
        final float centerX = containerView.getWidth() / 2.0f;
        final float centerY = containerView.getHeight() / 2.0f;

        final Rotate3D rotation = new Rotate3D(start,end, centerX, centerY,310.0f,true);
        rotation.setDuration(300);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplyNextView(tag));
        containerView.startAnimation(rotation);
    }

    private final class DisplyNextView implements Animation.AnimationListener{
        private final int tag;
        private DisplyNextView(int tag){
            this.tag = tag;
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            containerView.post(new SwapViews(tag));

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    private final class SwapViews implements Runnable {
        private final int tag;

        public SwapViews(int position) {
            tag = position;
        }

        public void run() {
            if (tag == 0) {
                tx1.setText("In Progress");

                showView(tag,puzzle_piece, puzzle_total_piece_show, 90, 0);
            } else if (tag == 1) {
                tx1.setText("In Progress");

                showView(tag,puzzle_total_piece_show, puzzle_piece, -90, 0);
            }
        }
    }

    private void showView(int tag, RelativeLayout showView, RelativeLayout hiddenView, int start_jd, int end_jd) {

        float centerX = showView.getWidth() / 2.0f;
        float centerY = showView.getHeight() / 2.0f;
        if (centerX == 0 || centerY == 0) {
            showView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

            centerX = showView.getMeasuredWidth() / 2.0f;
            centerY = showView.getMeasuredHeight() / 2.0f;
        }
        hiddenView.setVisibility(View.GONE);
        showView.setVisibility(View.VISIBLE);
        Rotate3D rotation = new Rotate3D(start_jd, end_jd, centerX, centerY, 310.0f, false);
        rotation.setDuration(300);
        rotation.setInterpolator(new DecelerateInterpolator());
        containerView.startAnimation(rotation);
    }

}
