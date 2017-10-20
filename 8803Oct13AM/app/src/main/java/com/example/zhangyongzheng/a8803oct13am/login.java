package com.example.zhangyongzheng.a8803oct13am;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class login extends Activity  {
    Button b1,b2;
    EditText ed1,ed2;

    TextView tx1;
    int counter = 3;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User_profile");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        b1 = (Button)findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);

        b2 = (Button)findViewById(R.id.button2);
        tx1 = (TextView)findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //myRef:User_Profile
                final String usr_id = ed1.getText().toString();
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    boolean finduser = false;

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot post : dataSnapshot.getChildren()) {

                            String key = post.getKey();

                            //Find users
                            if (ed1.getText().toString().equals(key)) {
                                finduser = true;
                                //Log.v("user_id", key);
                                DataSnapshot s = post.child("user_pwd");
                                String passWord = s.getValue(String.class);
                                //Log.v("password", passWord);
                                //Password is corrent
                                if (ed2.getText().toString().equals(passWord)) {
                                    break;
                                }

                                //Password is wrong
                                else {
                                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();

                                    tx1.setVisibility(View.VISIBLE);
                                    tx1.setBackgroundColor(Color.RED);
                                    counter--;
                                    tx1.setText(Integer.toString(counter));

                                    if (counter == 0) {
                                        b1.setEnabled(false);
                                    }
                                }
                            }



                        }
                        if (finduser == true){
                            Intent intent = new Intent(login.this, MainActivity.class);

                            Bundle bundle = new Bundle();
                            bundle.putString("usr_id", usr_id);

                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();

                            tx1.setVisibility(View.VISIBLE);
                            tx1.setBackgroundColor(Color.RED);
                            counter--;
                            tx1.setText(Integer.toString(counter));

                            if (counter == 0) {
                                b1.setEnabled(false);
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

//
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
                finish();
            }
        });
    }
}