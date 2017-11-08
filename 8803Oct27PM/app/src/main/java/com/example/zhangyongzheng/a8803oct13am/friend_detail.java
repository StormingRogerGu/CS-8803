package com.example.zhangyongzheng.a8803oct13am;

/**
 * Created by zhangyongzheng on 11/8/17.
 */

public class friend_detail {
    public String all_username;
    public int num_of_finish_puzzle;
    public int num_of_on_going_puzzle;

    public friend_detail(){

    }

    public friend_detail(String all_username, int num_of_finish_puzzle, int num_of_on_going_puzzle){
        this.all_username = all_username;
        this.num_of_finish_puzzle = num_of_finish_puzzle;
        this.num_of_on_going_puzzle = num_of_on_going_puzzle;
    }

    public void setAll_username(String all_username){
        this.all_username = all_username;
    }

    public void setNum_of_finish_puzzle(int num_of_finish_puzzle){
        this.num_of_finish_puzzle = num_of_finish_puzzle;
    }

    public void setNum_of_on_going_puzzle(int num_of_on_going_puzzle){
        this.num_of_on_going_puzzle = num_of_on_going_puzzle;
    }

    public String getAll_username(){
        return this.all_username;
    }

    public int getNum_of_finish_puzzle(){
        return this.num_of_finish_puzzle;
    }

    public int getNum_of_on_going_puzzle(){
        return this.num_of_on_going_puzzle;
    }
}
