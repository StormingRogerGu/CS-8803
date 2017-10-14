package com.example.zhangyongzheng.a8803oct13am;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhangyongzheng on 10/13/17.
 */

public class myadapter extends RecyclerView.Adapter{
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, Object>> listItem;
    private MyItemClickListener myItemClickListener;

    public myadapter(Context context, ArrayList<HashMap<String, Object>> listItem){
        inflater = LayoutInflater.from(context);
        this.listItem = listItem;
    }

    class Viewholder extends RecyclerView.ViewHolder  {
        private TextView Title, Text;
        private ImageView ima;

        public Viewholder(View root) {
            super(root);
            Title = (TextView) root.findViewById(R.id.task_deadline);
            Text = (TextView) root.findViewById(R.id.task_name);
            ima = (ImageView) root.findViewById(R.id.top);
            root.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (myItemClickListener != null)
                                                myItemClickListener .onItemClick(v,getPosition());
                                        }

                                    }
            );

        }

        public TextView getTitle() {
            return Title;
        }

        public TextView getText() {
            return Text;
        }

        public ImageView getIma() {
            return ima;
        }


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(inflater.inflate(R.layout.task_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Viewholder vh = (Viewholder) holder;
        vh.Title.setText((String) listItem.get(position).get("Deadline"));
        vh.Text.setText((String) listItem.get(position).get("TaskName"));
        vh.ima.setImageResource((Integer) listItem.get(position).get("TaskImage"));

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public void setOnItemClickListener(MyItemClickListener listener){
        myItemClickListener = listener;
    }
}
