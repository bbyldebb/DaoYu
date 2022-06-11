package com.daoyu.utils;

import java.sql.*;

public class connectUtil {
    static String uname = "root";
    static String password = "Fddjhtt+xfl1kgbr";
    static String url = "jdbc:mysql://sh-cynosdbmysql-grp-9s96k98c.sql.tencentcdb.com:20596/daoyu";

    static {
        //注册驱动程序，一次即可
        try {
            //使用8.0的路径
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("注册驱动程序失败");
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, uname, password);
        } catch (SQLException e) {
            System.out.println("获取数据库连接失败");
        }
        return conn;
    }
}
