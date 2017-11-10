package com.example.zhangyongzheng.a8803oct13am;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhangyongzheng on 11/8/17.
 */

public class friend_adapter extends BaseAdapter{
    private LayoutInflater mInflater;
    ArrayList<HashMap<String, Object>> listItem;

    static class frienddViewHolder{
        public TextView all_username;
        public TextView num_of_finish_puzzle;
        public TextView num_of_on_going_puzzle;
        public ImageView image;

    }

    public friend_adapter(Context context, ArrayList<HashMap<String, Object>> listItem){
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
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        frienddViewHolder holder;
        if(convertview == null){
            holder = new frienddViewHolder();
            convertview = mInflater.inflate(R.layout.friend_rank_item,null);
            holder.all_username = convertview.findViewById(R.id.friend_rank_item_username);
            holder.num_of_finish_puzzle = convertview.findViewById(R.id.friend_rank_item_finish);
            holder.num_of_on_going_puzzle = convertview.findViewById(R.id.friend_rank_item_ongoing);
            holder.image = convertview.findViewById(R.id.friend_rank_item_img);
            convertview.setTag(holder);
        }
        else{
            holder = (frienddViewHolder)convertview.getTag();
        }
        holder.all_username.setText((String) listItem.get(position).get("all_username"));
        holder.num_of_finish_puzzle.setText((String) ""+ listItem.get(position).get("num_of_finish_puzzle"));
        holder.num_of_on_going_puzzle.setText((String) ""+listItem.get(position).get("num_of_on_going_puzzle"));
        holder.image.setImageResource((Integer) listItem.get(position).get("ItemImage"));

        return convertview;
    }

    public void updateData(ArrayList<HashMap<String, Object>> listItem){
        this.listItem = listItem;
        notifyDataSetChanged();
    }
}
