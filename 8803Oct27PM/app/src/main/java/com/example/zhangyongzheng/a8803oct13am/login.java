package com.example.zhangyongzheng.a8803oct13am;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.*;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.util.Arrays;

public class login extends AppCompatActivity  {
    Button b1,b2;
    EditText ed1,ed2;

    TextView tx1;
    int counter = 3;

    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User_profile");

    private DatabaseReference mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        b1 = (Button)findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);

        b2 = (Button)findViewById(R.id.button2);
    //    tx1 = (TextView)findViewById(R.id.textView3);
    //    tx1.setVisibility(View.GONE);

        //final boolean[] sameID = {false};

        //about facebook login
        callbackManager = CallbackManager.Factory.create();
        final String user_id = new String();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                nextActivity(newProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();

        LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            boolean samepwd = false;

            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
//                GraphRequest request = GraphRequest.newMeRequest(
//                        loginResult.getAccessToken(),
//                        new GraphRequest.GraphJSONObjectCallback() {
//                            @Override
//                            public void onCompleted(JSONObject object, GraphResponse response) {
//                                Log.v("LoginActivity", response.toString());
//                                // Application code
//                                try {
//                                    String user_email = object.getString("email");
//                                    Log.v("facebook111", user_email);
//                                } catch (JSONException e) {
//                                }
//                            }
//                        });
                nextActivity(profile);
                //Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
            }
        };
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
        loginButton.registerCallback(callbackManager, callback);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //myRef:User_Profile
                final String usr_id = ed1.getText().toString();
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    boolean finduser = false;
                    boolean matchpwd = false;

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
                                    matchpwd = true;
                                    break;
                                }

                                //Password is wrong
        //                        else {
        //                            Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();

        //                            tx1.setVisibility(View.VISIBLE);
        //                            tx1.setBackgroundColor(Color.RED);
        //                            counter--;
        //                            tx1.setText(Integer.toString(counter));

        //                        if (counter == 0) {
        //                                b1.setEnabled(false);
        //                            }
        //                        }
                            }



                        }
                        if (finduser == true && matchpwd == true){
                            Intent intent = new Intent(login.this, MainActivity.class);

//                            Bundle bundle = new Bundle();
//                            bundle.putString("usr_id", usr_id);
//
//                            intent.putExtras(bundle);
                            User_id userId = ((User_id)getApplicationContext());
                            userId.setUserid(usr_id);

                            startActivity(intent);
                            finish();
                        }
                        else{
                            AlertDialog dialog = new AlertDialog.Builder(login.this).setTitle("Username or Password Not match")
                                    .setPositiveButton("OK",new DialogInterface.OnClickListener(){
                                        @Override
                                        public void onClick(DialogInterface dialog, int which){

                                        }
                                    }).setMessage("Please check your username and password!").create();

                            dialog.show();
//                            Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
//
//                            tx1.setVisibility(View.VISIBLE);
//                            tx1.setBackgroundColor(Color.RED);
//                            counter--;
//                            tx1.setText(Integer.toString(counter));
//
//                            if (counter == 0) {
//                                b1.setEnabled(false);
//                            }
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

    @Override
    protected void onResume() {
        super.onResume();
        //Facebook login
        Profile profile = Profile.getCurrentProfile();
        nextActivity(profile);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
        //Facebook login
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        //Facebook login
        callbackManager.onActivityResult(requestCode, responseCode, intent);

    }

    private void nextActivity(Profile pro){
        if(pro != null){

            String first_name = pro.getFirstName().toString();
            String last_name = pro.getLastName().toString();

            final String usr_name = first_name + last_name;
//            mydatabase.child(usr_name).child("usr_pwd").setValue("");
            final boolean[] sameID = {false};
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot post : dataSnapshot.getChildren()){
                        String key = post.getKey();
                        Log.v("11111",usr_name);
                        if (usr_name.equals(key)){
                            Log.v("Same Id", "Error with same ID");
                            sameID[0] = true;
                            break;
                        }
                    }
                    if (sameID[0] == false){
                        myRef.child(usr_name).child("Time_mode").child("puzzle_ongoing_id").child("puzzle_piece_id").setValue(1);
                        myRef.child(usr_name).child("Time_mode").child("puzzle_ongoing_id").child("puzzle_piece_ongoing").setValue(0);
                        User_id userId = ((User_id)getApplicationContext());
                        userId.setUserid(usr_name);
                        Intent main = new Intent(login.this, MainActivity.class);
                        startActivity(main);
                    }
                    else if (sameID[0] == true){
                        User_id userId = ((User_id)getApplicationContext());
                        userId.setUserid(usr_name);
                        Intent main = new Intent(login.this, MainActivity.class);
                        startActivity(main);
                    }

                }



                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            Log.v("facebook", "ffffffffff");

        }
    }
}