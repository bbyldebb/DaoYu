package com.daoyu.utils;

import java.util.ArrayList;

public class Parse {
    public static ArrayList<Integer> stringToList(String str) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (str != null && !str.equals("")) {
            for (String s : str.split("#")) {
                res.add(Integer.parseInt(s));
            }
        }
        return res;
    }

    public static String listToString(ArrayList<Integer> lst) {
        String res = "";
        if (lst != null) {
            for (int i = 0; i < lst.size(); i++) {
                if (i != 0) res += '#';
                res += Integer.toString(lst.get(i));
            }
        }
        return res;
    }
}
