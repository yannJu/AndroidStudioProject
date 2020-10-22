package com.example.finalmidtermproject;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<String[]> items;
    private int size = 0;

    public Cart(){
        items = new ArrayList<String[]>();
    }
    public void addCart(int type, String title, String option, int price, int cnt){
        items.add(new String[]{String.valueOf(type), title, option, String.valueOf(price), String.valueOf(cnt)});
        size += 1;
    }

    public int getSize(){
        return size;
    }

    public String[] getItems(int num){
        return items.get(num);
    }
}
