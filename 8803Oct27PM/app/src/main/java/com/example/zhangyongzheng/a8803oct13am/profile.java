package com.example.zhangyongzheng.a8803oct13am;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;


import java.util.*;

/**
 * Created by zhangyongzheng on 10/13/17.
 */

public class profile extends Activity {

    private ImageButton friend;
    private ImageButton timer;
    private ImageButton home;
    private ImageButton puzzle;
    private ImageButton profile;

    private User_id my_usr_id;
    private String usr_id;
    private DatabaseReference myRef;
    private TextView usrName;
    private EditText usrAge;
    private EditText usrEmail;
    private EditText usrInstitution;
    private String usr_name;
    private String usr_age;
    private String usr_email;
    private String usr_institution;
    private Button Confirm_change;
    private EditText chage_pwd;
    private EditText cfm_pwd;
    private Button sign_out;

    private ImageView profile_current_user_rank_img;
    private TextView profile_current_user_name;
    private TextView profile_current_user_level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        setUpView();
        setMy_usr_id();
        setConfirm_change();
        setSign_out();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usr_name = dataSnapshot.getKey();
                //usrName.setText(usr_name, TextView.BufferType.EDITABLE);
                profile_current_user_name.setText(usr_name);

                usr_age = dataSnapshot.child("usr_age").getValue(String.class);
                usrAge.setText(usr_age, TextView.BufferType.EDITABLE);

                usr_email = dataSnapshot.child("usr_email").getValue(String.class);
                usrEmail.setText(usr_email, TextView.BufferType.EDITABLE);

                usr_institution = dataSnapshot.child("usr_Institution").getValue(String.class);
                usrInstitution.setText(usr_institution, TextView.BufferType.EDITABLE);

                int num_pic = dataSnapshot.child("Time_mode").child("puzzle_ongoing_id").child("puzzle_piece_ongoing").getValue(Integer.class);
                if(num_pic >= 0 && num_pic <=20){
                    profile_current_user_rank_img.setImageResource(R.drawable.ant_level_1);
                    profile_current_user_level.setText("Level 1");

                }
                else if(num_pic >= 21 && num_pic <=40){
                    profile_current_user_rank_img.setImageResource(R.drawable.ant_level_2);
                    profile_current_user_level.setText("Level 2");
                }
                else if(num_pic >= 41 && num_pic <60){
                    profile_current_user_rank_img.setImageResource(R.drawable.ant_level_3);
                    profile_current_user_level.setText("Level 3");
                }
                else if(num_pic >= 61 && num_pic <= 80){
                    profile_current_user_rank_img.setImageResource(R.drawable.ant_level_4);
                    profile_current_user_level.setText("Level 4");
                }
                else {
                    profile_current_user_rank_img.setImageResource(R.drawable.ant_level_5);
                    profile_current_user_level.setText("Level 5");
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(profile.this,friend.class);
                startActivity(addnewtask);
                finish();
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(profile.this,timesetting.class);
//                Drawable image = getResources().getDrawable(R.drawable.clock_icon_highlight);
//                addnewtask.putExtra("new_image",drawable2Bytes(image));
                startActivity(addnewtask);
                finish();
            }
        });
        puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(profile.this,puzzle.class);
//                Drawable image = getResources().getDrawable(R.drawable.puzzle_icon_highlight);
//                addnewtask.putExtra("new_image",drawable2Bytes(image));
                startActivity(addnewtask);
                finish();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(profile.this,profile.class);
                startActivity(addnewtask);
                finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(profile.this,MainActivity.class);
                startActivity(addnewtask);
                finish();
            }
        });
    }

    private void setUpView(){

        friend = (ImageButton)findViewById(R.id.friend_bar_profile_highlight);
        timer = (ImageButton)findViewById(R.id.timer_profile_highlight);
        home = (ImageButton)findViewById(R.id.home_profile_highlight);
        puzzle = (ImageButton)findViewById(R.id.puzzle_profile_highlight);
        profile = (ImageButton)findViewById(R.id.profile_profile_highlight);

        //usrName = (TextView)findViewById(R.id.profile_usr_name);
        usrAge = (EditText)findViewById(R.id.profile_usr_age);
        usrEmail = (EditText)findViewById(R.id.profile_usr_email);
        usrInstitution = (EditText)findViewById(R.id.profile_usr_institution);
        Confirm_change = (Button)findViewById(R.id.profile_confirm);
        chage_pwd = (EditText)findViewById(R.id.profile_usr_new_password);
        cfm_pwd = (EditText)findViewById(R.id.profile_usr_cfm_password);
        sign_out = (Button)findViewById(R.id.sign_out);

        profile_current_user_rank_img = (ImageView)findViewById(R.id.profile_current_rank_img);
        profile_current_user_name = (TextView)findViewById(R.id.profile_current_user_name);
        profile_current_user_level = (TextView)findViewById(R.id.profile_current_user_level);
    }

    private void setConfirm_change(){
        Confirm_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!chage_pwd.getText().toString().equals(cfm_pwd.getText().toString())){
                    AlertDialog dialog = new AlertDialog.Builder(profile.this).setTitle("Password Not match")
                            .setPositiveButton("OK",new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which){

                                    Intent intent = new Intent(profile.this, profile.class);
                                    startActivity(intent);
                                    //TODO share to facebook
                                }
                            }).setMessage("Please input same password!").create();

                    dialog.show();
                }
                else{
                    String usr_age1 = usrAge.getText().toString();
                    if (usr_age1.length() != 0){
                        myRef.child("usr_age").setValue(usr_age1);
                    }
                    String usr_email1 = usrEmail.getText().toString();
                    if (usr_email1.length() != 0){
                        myRef.child("usr_email").setValue(usr_email1);
                    }
                    String usr_institution1 = usrInstitution.getText().toString();
                    if (usr_institution1.length() != 0){
                        myRef.child("usr_Institution").setValue(usr_institution1);
                    }

                    String new_pwd = chage_pwd.getText().toString();
                    if (new_pwd.length() != 0){
                        myRef.child("user_pwd").setValue(new_pwd);
                    }

                    Intent intent = new Intent(profile.this, profile.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void setSign_out(){
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(profile.this, login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setMy_usr_id(){
        my_usr_id = (User_id) getApplication();
        usr_id = my_usr_id.getUserid();
        myRef = FirebaseDatabase.getInstance().getReference("User_profile").child(usr_id);
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


}
