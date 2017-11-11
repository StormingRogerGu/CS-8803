package com.example.zhangyongzheng.a8803oct13am;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by zhangyongzheng on 10/27/17.
 */

public class Complete_puzzle_show extends Activity {

    private ListView lv;
    private TextView activity_title;
    private Button back_btn;
    private ImageView img;

    private User_id my_usr_id;
    private String usr_id;
    private DatabaseReference myRef;
    private DatabaseReference myRef2;

    private ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
    private List<String> puzzle_name_list = new ArrayList<String>();
    private List<Bitmap> puzzle_storage_list = new ArrayList<Bitmap>();

    private HashMap<String, Bitmap> raw_hashmap = new HashMap<String, Bitmap>();


    private StorageReference storageref = FirebaseStorage.getInstance().getReference().child("all_complete_puzzle");
    private int complete_puzzle_id;

    private HashMap<String, Drawable> original = new HashMap<String, Drawable>();
    private long ONE_MEGABYTE = 1024 * 1024;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_puzzle);
        setUpView();
        setUsr_id();
        //img.setDrawingCacheEnabled(true);

        final mypuzzleadapter myownpuzzleadapter = new mypuzzleadapter(this, listItem);

//        setcontent();
//        Log.v("how many complete,","it is "+complete_puzzle_id);

        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                complete_puzzle_id = dataSnapshot.getValue(int.class);
                for(int i = 0; i< complete_puzzle_id; i++){
                    String picture = i + ".jpg";
                    String name = "The" + i + "th puzzle";
                    puzzle_name_list.add(name);
                    //ImageView temp_image = (ImageView)findViewById(R.id.complete_item_puzzle);
                    StorageReference temp = storageref.child(picture);
                    Log.v("pic name", picture);
                    //Glide.with(Complete_puzzle_show.this).using(new FirebaseImageLoader()).load(temp).into(img);
                    final long MAX_BYTE = 1024*1024;
                    temp.getBytes(MAX_BYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                            puzzle_storage_list.add(bitmap);
                            initialdata();
                            myownpuzzleadapter.updateData(listItem);
                            lv.setAdapter(myownpuzzleadapter);

                        }
                    });



                }

                Log.v("storage_size","the size is " + puzzle_storage_list.size());


                myownpuzzleadapter.updateData(listItem);
                lv.setAdapter(myownpuzzleadapter);
                Log.v("how many complete,","it is "+complete_puzzle_id +"in datasnap shot");


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Complete_puzzle_show.this, puzzle.class);
                startActivity(intent);
            }
        });


    }

    public void setUpView(){
        this.activity_title = (TextView)findViewById(R.id.completed_puzzle_title_text);
        this.back_btn = (Button)findViewById(R.id.completed_puzzle_back_btn);
        this.lv = (ListView)findViewById(R.id.completed_puzzle_show_listview);
        this.img = (ImageView)findViewById(R.id.completed_puzzle_temp_imageview_show);
    }

    public void setUsr_id(){

        my_usr_id = (User_id) getApplication();
        usr_id = my_usr_id.getUserid();
        myRef = FirebaseDatabase.getInstance().getReference("User_profile").child(usr_id).child("Time_mode");
        myRef2 = FirebaseDatabase.getInstance().getReference("User_profile").child(usr_id).child("Time_mode").child("puzzle_comp_id").child("Completed_id");
    }

    public void initialdata(){
        for(int i = 0; i<puzzle_name_list.size();i++){
            String key = puzzle_name_list.get(i);
            Bitmap btm = puzzle_storage_list.get(i);
            raw_hashmap.put(key, btm);

        }
        List<Map.Entry<String, Bitmap>> transfer = new ArrayList<Map.Entry<String, Bitmap>>(raw_hashmap.entrySet());

        for(int i = 0; i<puzzle_name_list.size(); i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle",transfer.get(i).getKey());
            //Log.v("mapkey",transfer.get(i).getKey());
            map.put("ItemImage",transfer.get(i).getValue());

            listItem.add(map);
        }


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

    public void setcontent(){
        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                complete_puzzle_id = dataSnapshot.getValue(int.class);

                Log.v("how many complete,","it is "+complete_puzzle_id +"in datasnap shot");


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.v("how many complete,","it is "+complete_puzzle_id + "in function");

    }

    public void shuchu(){
        Log.v("how many complete","shuchu" + complete_puzzle_id);
    }


}
