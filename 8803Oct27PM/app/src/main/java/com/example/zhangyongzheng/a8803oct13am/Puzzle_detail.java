package com.example.zhangyongzheng.a8803oct13am;

import android.graphics.Bitmap;

/**
 * Created by zhangyongzheng on 10/27/17.
 */

public class Puzzle_detail {
    public String puzzle_title;
    public Bitmap bitmap;

    public Puzzle_detail(String title, Bitmap bitmap){
        this.puzzle_title = title;
        this.bitmap = bitmap;
    }
    public String get_title(){
        return this.puzzle_title;
    }
}
