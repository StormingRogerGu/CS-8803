package com.example.zhangyongzheng.a8803oct13am;

import android.content.Context;
import android.graphics.Bitmap;
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
 * Created by zhangyongzheng on 10/27/17.
 */

public class mypuzzleadapter extends BaseAdapter {
    private LayoutInflater mInflater;
    ArrayList<HashMap<String, Object>> listItem;

    static class ViewHolder{
        public ImageView Image;
        public TextView title_text;
    }

    public mypuzzleadapter(Context context, ArrayList<HashMap<String, Object>> listItem){
        this.mInflater = LayoutInflater.from(context);
        this.listItem = listItem;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.task_item, null);
            holder.Image = (ImageView) view.findViewById(R.id.complete_item_puzzle);
            holder.title_text = (TextView) view.findViewById(R.id.complete_item_text);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder)view.getTag();
        }

        holder.Image.setImageBitmap((Bitmap) listItem.get(position).get("ItemImage"));
        holder.title_text.setText((String) listItem.get(position).get("ItemTitle"));


        return view;
    }

    public void updateData(ArrayList<HashMap<String, Object>> listItem){
        this.listItem = listItem;
        notifyDataSetChanged();
    }
}
