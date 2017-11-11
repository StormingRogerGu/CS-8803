package com.example.zhangyongzheng.a8803oct13am;

import java.util.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.google.firebase.database.*;
/**
 * Created by guxiaofeng on 10/19/17.
 */

public class signup extends Activity{
    private Button confirm;
    private Button cancel;
    private EditText user_id;
    private EditText password;
    private EditText confirm_password;
    private DatabaseReference mydatabase;
    private EditText email;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        setupView();


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userid = user_id.getText().toString();
                final String pwd = password.getText().toString();
                final String email_info = email.getText().toString();
                final String confirm_pwd = confirm_password.getText().toString();
                final boolean[] sameID = {false};

                mydatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    boolean samepwd = false;
                    boolean validemail = true;
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot post : dataSnapshot.getChildren()){
                            if (userid.isEmpty()){
                                break;
                            }
                            String key = post.getKey();
                            Log.v("11111",userid);
                            if (userid.equals(key)){
                                Log.v("Same Id", "Error with same ID");
                                sameID[0] = true;
                                break;
                            }
                        }

                        if (pwd.equals(confirm_pwd) && !pwd.isEmpty() ){
                            samepwd = true;
                        }
                        if (emailValidation(email_info) && !email_info.isEmpty()){
                            validemail = true;
                            Log.v("email", "is" + validemail);
                        }



                        if (sameID[0] == false && samepwd == true && validemail == true){
                            Log.v("SUCCESS", "11");

                            mydatabase.child(user_id.getText().toString()).child("user_pwd").setValue(password.getText().toString());
                            mydatabase.child(user_id.getText().toString()).child("Time_mode").child("puzzle_ongoing_id").child("puzzle_piece_id").setValue(1);
                            mydatabase.child(user_id.getText().toString()).child("Time_mode").child("puzzle_ongoing_id").child("puzzle_piece_ongoing").setValue(0);
                            mydatabase.child(user_id.getText().toString()).child("usr_email").setValue(email.getText().toString());
                            User_id userId = ((User_id)getApplicationContext());
                            userId.setUserid(userid);
                            //mydatabase.child(user_id.getText().toString()).child("usr_age").setValue(age.getText().toString());
                            //mydatabase.child(user_id.getText().toString()).child("usr_Institution").setValue(Institution.getText().toString());
                            Intent intent = new Intent(signup.this, signup_profile.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            AlertDialog dialog = new AlertDialog.Builder(signup.this).setTitle("Wrong information")
                                    .setPositiveButton("OK",new DialogInterface.OnClickListener(){
                                        @Override
                                        public void onClick(DialogInterface dialog, int which){

                                            Intent intent = new Intent(signup.this, signup.class);
                                            startActivity(intent);

                                        }
                                    }).setMessage("Please input correct information!").create();

                            dialog.show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
                finish();

            }
        });



    }

    public boolean emailValidation(String email) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }

    public void setupView(){
        confirm = (Button)findViewById(R.id.sign_up_comfirm_btn);
        cancel = (Button)findViewById(R.id.sign_up_cancel_btn);
        user_id = (EditText)findViewById(R.id.sign_up_id);
        password = (EditText)findViewById(R.id.sign_up_pwd);
        confirm_password = (EditText)findViewById(R.id.sign_up_confirm_pwd);
        mydatabase = FirebaseDatabase.getInstance().getReference("User_profile");
        email = (EditText)findViewById(R.id.sign_up_email);


    }

}
