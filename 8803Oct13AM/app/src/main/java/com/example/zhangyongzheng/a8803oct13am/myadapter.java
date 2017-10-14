package com.example.zhangyongzheng.a8803oct13am;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
    static class ViewHolder{
        public ImageView img;
        public TextView title;
        public TextView text;
        public Button btn;
    }

    public myadapter(Context context, ArrayList<HashMap<String, Object>> listItem){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if(convertView == null)
        {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.task_item, null);
            holder.img = (ImageView)convertView.findViewById(R.id.task_image);
            holder.title = (TextView)convertView.findViewById(R.id.task_deadline);
            holder.text = (TextView)convertView.findViewById(R.id.task_name);
            holder.btn = (Button) convertView.findViewById(R.id.finish_task);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();

        }
        holder.img.setImageResource((Integer) listItem.get(position).get("ItemImage"));
        holder.title.setText((String) listItem.get(position).get("ItemTitle"));
        holder.text.setText((String) listItem.get(position).get("ItemText"));


        return convertView;


    }
}
