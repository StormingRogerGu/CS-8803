package com.example.zhangyongzheng.a8803oct13am;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by zhangyongzheng on 10/27/17.
 */

public class Puzzle_detail {
    public String puzzle_title;
    public Drawable drawable;

    public Puzzle_detail(String title, Drawable drawable){
        this.puzzle_title = title;
        this.drawable = drawable;
    }
    public String get_title(){
        return this.puzzle_title;
    }
}
