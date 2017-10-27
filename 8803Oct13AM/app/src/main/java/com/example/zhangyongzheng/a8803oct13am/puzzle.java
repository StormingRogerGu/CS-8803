package com.example.zhangyongzheng.a8803oct13am;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

/**
 * Created by zhangyongzheng on 10/13/17.
 */


public class puzzle extends Activity {

    private ImageButton friend;
    private ImageButton timer;
    private ImageButton home;
    private ImageButton puzzle;
    private ImageButton profile;
    private ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;
    private TextView rect1, rect2, rect3, rect4, rect5, rect6, rect7, rect8, rect9, rect10;
    private Button bt1;
    private TextView tx1;
    int counter=9;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle_gallery);
        setUpView();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter==9) {
                    img1.setImageResource(R.drawable.pic1);
                    rect1.setBackgroundColor(Color.GREEN);
                    rect10.setText("11%");
                    counter--;
                }
                else if(counter==8) {
                    img2.setImageResource(R.drawable.pic2);
                    rect2.setBackgroundColor(Color.GREEN);
                    rect10.setText("22%");
                    counter--;
                }
                else if(counter==7) {
                    img3.setImageResource(R.drawable.pic3);
                    rect3.setBackgroundColor(Color.GREEN);
                    rect10.setText("33%");
                    counter--;
                }
                else if(counter==6) {
                    img4.setImageResource(R.drawable.pic4);
                    rect4.setBackgroundColor(Color.GREEN);
                    rect10.setText("44%");
                    counter--;
                }
                else if(counter==5) {
                    img5.setImageResource(R.drawable.pic5);
                    rect5.setBackgroundColor(Color.GREEN);
                    rect10.setText("55%");
                    counter--;
                }
                else if(counter==4) {
                    img6.setImageResource(R.drawable.pic6);
                    rect6.setBackgroundColor(Color.GREEN);
                    rect10.setText("66%");
                    counter--;
                }
                else if(counter==3) {
                    img7.setImageResource(R.drawable.pic7);
                    rect7.setBackgroundColor(Color.GREEN);
                    rect10.setText("77%");
                    counter--;
                }
                else if(counter==2) {
                    img8.setImageResource(R.drawable.pic8);
                    rect8.setBackgroundColor(Color.GREEN);
                    rect10.setText("88%");
                    counter--;
                }
                else if(counter==1) {
                    img9.setImageResource(R.drawable.pic9);
                    tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    rect9.setBackgroundColor(Color.GREEN);
                    rect10.setText("100%");
                }
            }
        });


        //change icon
        Intent intent = getIntent();
        byte[] new_icon = intent.getByteArrayExtra("new_image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(new_icon,0,new_icon.length);
        puzzle.setImageBitmap(bitmap);



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
        rect1=(TextView)findViewById(R.id.rect1);
        rect2=(TextView)findViewById(R.id.rect2);
        rect3=(TextView)findViewById(R.id.rect3);
        rect4=(TextView)findViewById(R.id.rect4);
        rect5=(TextView)findViewById(R.id.rect5);
        rect6=(TextView)findViewById(R.id.rect6);
        rect7=(TextView)findViewById(R.id.rect7);
        rect8=(TextView)findViewById(R.id.rect8);
        rect9=(TextView)findViewById(R.id.rect9);
        rect10=(TextView)findViewById(R.id.rect10);

        tx1=(TextView)findViewById(R.id.textView);
        tx1.setVisibility(View.GONE);
        bt1=(Button)findViewById(R.id.button_id);
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
