package com.example.zhangyongzheng.a8803oct13am;

import java.util.*;

import android.app.Activity;
import android.os.Bundle;
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


    }

    public void setupView(){
        confirm = (Button)findViewById(R.id.sign_up_comfirm_btn);
        cancel = (Button)findViewById(R.id.sign_up_cancel_btn);
        user_id = (EditText)findViewById(R.id.sign_up_id);
        password = (EditText)findViewById(R.id.sign_up_pwd);
        confirm_password = (EditText)findViewById(R.id.sign_up_confirm_pwd);

    }

}
