package com.daoyu.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class HotNumCalculator {
    public static int calHotNum(int followNum, int commentNum, int status, Timestamp time) {
        LocalDateTime tm = time.toLocalDateTime();
        int date = tm.getYear() * 365 + tm.getDayOfYear();
        if (status == 0) {
            return date;
        }
        return followNum + commentNum + 30 * status + date;
    }
}
