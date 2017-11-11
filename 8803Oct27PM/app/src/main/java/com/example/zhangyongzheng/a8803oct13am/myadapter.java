package com.example.zhangyongzheng.a8803oct13am;

import android.content.Context;
import android.content.DialogInterface;

import android.graphics.Bitmap;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhangyongzheng on 10/13/17.
 */

public class myadapter extends BaseAdapter{
    private LayoutInflater mInflater;
    ArrayList<HashMap<String, Object>> listItem;
    private MyClickListener mListener;
    private MyFlagClickListener mFListener;
    static class ViewHolder{
        public ImageButton img;
        public TextView title;
        public TextView text;
        public Button btn;


    }


    public myadapter(Context context, ArrayList<HashMap<String, Object>> listItem, MyClickListener listener, MyFlagClickListener flaglistenter){
        this.mInflater = LayoutInflater.from(context);
        this.listItem = listItem;
        this.mListener = listener;
        this.mFListener = flaglistenter;

    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null)
        {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.task_item, null);
            holder.img = (ImageButton) convertView.findViewById(R.id.task_image);
            holder.title = (TextView)convertView.findViewById(R.id.task_deadline);
            holder.text = (TextView)convertView.findViewById(R.id.task_name);
            holder.btn = (Button) convertView.findViewById(R.id.finish_task);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();

        }
        holder.img.setImageResource((Integer) listItem.get(position).get("ItemImage"));
        holder.img.setOnClickListener(mFListener);
        holder.img.setTag(position);
        holder.title.setText((String) listItem.get(position).get("ItemTitle"));
        holder.text.setText((String) listItem.get(position).get("ItemText"));
        holder.btn.setOnClickListener(mListener);
        holder.btn.setTag(position);

        return convertView;


    }
    public void updateData(ArrayList<HashMap<String, Object>> listItem){
        this.listItem = listItem;
        notifyDataSetChanged();
    }

//    private class myListenDelete implements View.OnClickListener {
//
//        int position;
//
//        public myListenDelete(int position){
//            this.position = position;
//        }
//
//
//        @Override
//        public void onClick(View view) {
//            Log.v("where","here");
//
//        }
//
//
//    }
    public static abstract class MyClickListener implements View.OnClickListener {
    
    @Override
    public void onClick(View v){
        myOnClick((Integer) v.getTag(), v);

    }

    public abstract void myOnClick(int position, View v);
    }

    public static abstract class MyFlagClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            myflagOnClick((Integer) view.getTag(), view);


        }

        public abstract void myflagOnClick(int position, View v);
    }
}
