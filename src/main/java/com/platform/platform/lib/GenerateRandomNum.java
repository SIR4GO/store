package com.platform.platform.lib;

import java.util.ArrayList;
import java.util.Random;

public class GenerateRandomNum {


    public  Integer GenerateNumber()
    {
        int size = 100;
        ArrayList<Integer> list = new ArrayList<Integer>(size);
        for(int i = 1; i <= size; i++) {
            list.add(i);
        }

        Random rand = new Random();
        int index = 0 ;
        if(list.size() > 0) {
            index = rand.nextInt(list.size());
            list.remove(index);
        }

        return index;
    }
}
