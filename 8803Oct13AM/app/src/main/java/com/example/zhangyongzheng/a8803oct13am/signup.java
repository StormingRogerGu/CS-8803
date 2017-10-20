package com.example.zhangyongzheng.a8803oct13am;

import java.util.*;

import android.app.Activity;
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
                final String confirm_pwd = confirm_password.getText().toString();
                final boolean[] sameID = {false};

                mydatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    boolean samepwd = false;

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot post : dataSnapshot.getChildren()){
                            String key = post.getKey();
                            Log.v("11111",userid);
                            if (userid.equals(key)){
                                Log.v("Same Id", "Error with same ID");
                                sameID[0] = true;
                                break;
                            }
                        }

                        if (pwd.equals(confirm_pwd)){
                            samepwd = true;
                        }
                        if (sameID[0] == false && samepwd == true){
                            Log.v("SUCCESS", "11");
                            mydatabase.child(user_id.getText().toString()).child("user_pwd").setValue(password.getText().toString());
                            Intent intent = new Intent(signup.this, login.class);
                            startActivity(intent);
                            finish();
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



    public void setupView(){
        confirm = (Button)findViewById(R.id.sign_up_comfirm_btn);
        cancel = (Button)findViewById(R.id.sign_up_cancel_btn);
        user_id = (EditText)findViewById(R.id.sign_up_id);
        password = (EditText)findViewById(R.id.sign_up_pwd);
        confirm_password = (EditText)findViewById(R.id.sign_up_confirm_pwd);
        mydatabase = FirebaseDatabase.getInstance().getReference("User_profile");

    }

}
