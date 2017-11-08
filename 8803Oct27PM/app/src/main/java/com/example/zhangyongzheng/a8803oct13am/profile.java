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
                usrName.setText(usr_name, TextView.BufferType.EDITABLE);

                usr_age = dataSnapshot.child("usr_age").getValue(String.class);
                usrAge.setText(usr_age, TextView.BufferType.EDITABLE);

                usr_email = dataSnapshot.child("usr_email").getValue(String.class);
                usrEmail.setText(usr_email, TextView.BufferType.EDITABLE);

                usr_institution = dataSnapshot.child("usr_Institution").getValue(String.class);
                usrInstitution.setText(usr_institution, TextView.BufferType.EDITABLE);


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
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(profile.this,profile.class);
                startActivity(addnewtask);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnewtask = new Intent();
                addnewtask.setClass(profile.this,MainActivity.class);
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
        usrName = (TextView)findViewById(R.id.profile_usr_name);
        usrAge = (EditText)findViewById(R.id.profile_usr_age);
        usrEmail = (EditText)findViewById(R.id.profile_usr_email);
        usrInstitution = (EditText)findViewById(R.id.profile_usr_institution);
        Confirm_change = (Button)findViewById(R.id.profile_confirm);
        chage_pwd = (EditText)findViewById(R.id.profile_usr_new_password);
        cfm_pwd = (EditText)findViewById(R.id.profile_usr_cfm_password);
        sign_out = (Button)findViewById(R.id.sign_out);
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
