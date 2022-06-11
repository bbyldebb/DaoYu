package com.daoyu.utils;

import java.util.ArrayList;

public class testParse {
    public static void main(String[] args) {
        ArrayList<Integer> lst1=new ArrayList<Integer>();
        lst1.add(111);
        lst1.add(222);
        String str1=Parse.listToString(lst1);
        System.out.println(str1);
        System.out.println(Parse.stringToList(str1));
    }
}
