package com.daoyu.dao;

import com.daoyu.entity.User;

import java.util.ArrayList;

public interface UserDao {
    //增加User对象
    void addUser(User user);

    //根据userID  删除User对象
    void delUser(String userID);

    //根据userID  更新User对象
    void updateUser(String userID, User user);

    //根据userID 查找User对象
    User findUserByID(String userID);

    //根据nickName 查找User对象
    ArrayList<User> findUserByName(String nickName);

    //查找所有User对象
    ArrayList<User> findAllUsers();
}
