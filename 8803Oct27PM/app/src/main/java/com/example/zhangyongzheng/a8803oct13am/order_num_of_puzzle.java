package com.example.zhangyongzheng.a8803oct13am;

import java.util.Comparator;

/**
 * Created by zhangyongzheng on 11/8/17.
 */

public class order_num_of_puzzle implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        friend_detail f1 = (friend_detail)o1;
        friend_detail f2 = (friend_detail)o2;

        if(f1.num_of_on_going_puzzle > f2.num_of_on_going_puzzle){
            return -1;
        }
        else if(f1.num_of_on_going_puzzle == f2.num_of_on_going_puzzle){
            return 0;
        }
        else {
            return 1;
        }

    }
}
