package com.example.zhangyongzheng.a8803oct13am;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.google.firebase.database.*;
/**
 * Created by guxiaofeng on 11/11/17.
 */

public class signup_profile extends Activity{
    private EditText company;
    private EditText age;
    private Button complete_btn;
    private User_id my_usr_id;
    private String usr_id;
    private RadioGroup radgroup;
    private DatabaseReference myRef;

    @Override
    public void onCreate(Bundle savedInstanseState){
        super.onCreate(savedInstanseState);
        setContentView(R.layout.signup_profile);

        setMy_usr_id();
        setupView();

        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("usr_age").setValue(age.getText().toString());
                myRef.child("usr_Institution").setValue(company.getText().toString());
                for (int i = 0; i < radgroup.getChildCount(); i++){
                    RadioButton rb = (RadioButton) radgroup.getChildAt(i);
                    if (rb.isChecked()){
                        myRef.child("usr_Institution").setValue(rb.getText().toString());
                        break;
                    }
                }
                Intent intent = new Intent(signup_profile.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void setupView(){
        company = (EditText) findViewById(R.id.sign_up_companyschool);
        age = (EditText) findViewById(R.id.sign_up_age);
        complete_btn = (Button)findViewById(R.id.sign_up_complete_btn);
        radgroup = (RadioGroup)findViewById(R.id.radio_group);

    }
    public void setMy_usr_id(){
        my_usr_id = (User_id)getApplication();
        usr_id = my_usr_id.getUserid();
        myRef = FirebaseDatabase.getInstance().getReference("User_profile").child(usr_id);
    }

}
