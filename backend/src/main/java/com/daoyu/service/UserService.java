package com.daoyu.service;

import com.daoyu.dao.UserDao;
import com.daoyu.dao.impl.UserDaoImpl;
import com.daoyu.entity.User;

import java.util.ArrayList;

public class UserService {
    static UserDao userDao = new UserDaoImpl();

    //登录
    static public void logIn(String userID, String nickName, String avatarImg) {
        User user = userDao.findUserByID(userID);
        if (user == null) {
            //若不存在，则新建一条记录
            user = new User();
            user.setUserID(userID);
            user.setNickName(nickName);
            user.setAvatarImg(avatarImg);
            userDao.addUser(user);
        } else {
            //若存在，则判断nickName和avatarImg是否需要更新
            user.setNickName(nickName);
            user.setAvatarImg(avatarImg);
            userDao.updateUser(userID, user);
        }
    }

    //根据userID获取用户所有信息
    static public User getUserInfo(String userID) {
        return userDao.findUserByID(userID);
    }

    //修改用户信息
    static public void modifyUserInfo(String userID, int gender, String birthday, String phoneNum, String address, String introduction) {
        User user = userDao.findUserByID(userID);
        if (user != null) {
            user.setGender(gender);
            user.setBirthday(birthday);
            user.setPhoneNum(phoneNum);
            user.setAddress(address);
            user.setIntroduction(introduction);
            userDao.updateUser(userID, user);
        }
    }

    //给某个user的 posts字段 增加一条
    static public void appendUserPost(String userID, int postID) {
        User user = userDao.findUserByID(userID);
        if (user != null) {
            ArrayList<Integer> userPosts = user.getPosts();
            if (userPosts == null) {
                userPosts = new ArrayList<Integer>();
            }
            userPosts.add(postID);
            user.setPosts(userPosts);
            userDao.updateUser(userID, user);
        }
    }

    //给某个user的 followPosts字段 增加一条
    static public void appendUserFollowPost(String userID, int postID) {
        User user = userDao.findUserByID(userID);
        if (user != null) {
            ArrayList<Integer> userFollowPosts = user.getFollowPosts();
            if (userFollowPosts == null) {
                userFollowPosts = new ArrayList<Integer>();
            }
            userFollowPosts.add(postID);
            user.setFollowPosts(userFollowPosts);
            userDao.updateUser(userID, user);
        }
    }
}
