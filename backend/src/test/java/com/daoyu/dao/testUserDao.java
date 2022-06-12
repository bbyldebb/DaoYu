package com.daoyu.dao;

import com.alibaba.fastjson.JSON;
import com.daoyu.dao.impl.UserDaoImpl;
import com.daoyu.entity.User;

import java.util.ArrayList;

public class testUserDao {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();

        //添加一条记录
        User user = new User();
        user.setUserID("111");
        user.setNickName("guobaorou");
        ArrayList<Integer> lst = new ArrayList<Integer>();
        lst.add(1);
        lst.add(2);
        user.setPosts(lst);
        userDao.addUser(user);
        //查看初始状态
        System.out.println(userDao.findAllUsers());
        System.out.println("*****************************");
        //查找这条记录
        User res = userDao.findUserByID("111");
        System.out.println(res);
        //更新这条记录
        res.setNickName("gbr");
        userDao.updateUser(res.getUserID(), res);
        //再次查看状态
        System.out.println(userDao.findAllUsers());
        System.out.println("*****************************");
        //删除这条记录
        userDao.delUser(res.getUserID());
        //再次查看状态
        System.out.println(JSON.toJSONString(userDao.findAllUsers()));
    }
}
