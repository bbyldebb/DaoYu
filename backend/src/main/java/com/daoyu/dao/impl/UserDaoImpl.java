package com.daoyu.dao.impl;

import com.daoyu.dao.UserDao;
import com.daoyu.entity.User;
import com.daoyu.utils.Parse;
import com.daoyu.utils.connectUtil;

import java.sql.*;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    static Connection conn;

    static {
        conn = connectUtil.getConnection();
    }

    @Override
    public void addUser(User user) {
        try {
            String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            //只有user的userID要自己指定
            stmt.setString(1, user.getUserID());
            stmt.setString(2, user.getNickName());
            stmt.setString(3, user.getAvatarImg());
            stmt.setString(4, user.getPhoneNum());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getBirthday());
            stmt.setString(7, user.getIntroduction());
            stmt.setInt(8, user.getGender());
            stmt.setString(9, Parse.listToString(user.getPosts()));
            stmt.setString(10, Parse.listToString(user.getFollowPosts()));
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("addUser失败");
            e.printStackTrace();
        }
    }

    @Override
    public void delUser(String userID) {
        try {
            String sql = "delete from user where userID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userID);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("delUser失败");
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(String userID, User user) {
        try {
            String sql = "update user " +
                    "set nickName = ? ,avatarImg = ? ,phoneNum = ? ,address = ? ,birthday = ? ," +
                    "introduction = ? ,gender = ? ,posts = ? ,followPosts = ? " +
                    "WHERE userID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getNickName());
            stmt.setString(2, user.getAvatarImg());
            stmt.setString(3, user.getPhoneNum());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getBirthday());
            stmt.setString(6, user.getIntroduction());
            stmt.setInt(7, user.getGender());
            stmt.setString(8, Parse.listToString(user.getPosts()));
            stmt.setString(9, Parse.listToString(user.getFollowPosts()));
            stmt.setString(10, userID);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("updateUser失败");
            e.printStackTrace();
        }
    }

    @Override
    public User findUserByID(String userID) {
        User user = null;
        try {
            String sql = "select * from user where userID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userID);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                user = new User();
                user.setUserID(res.getString("userID"));
                user.setNickName(res.getString("nickName"));
                user.setAvatarImg(res.getString("avatarImg"));
                user.setPhoneNum(res.getString("phoneNum"));
                user.setAddress(res.getString("address"));
                user.setBirthday(res.getString("birthday"));
                user.setIntroduction(res.getString("introduction"));
                user.setGender(res.getInt("gender"));
                user.setPosts(Parse.stringToList(res.getString("posts")));
                user.setFollowPosts(Parse.stringToList(res.getString("followPosts")));
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("findUserByID失败");
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public ArrayList<User> findUserByName(String nickName) {
        ArrayList<User> users = new ArrayList<User>();
        try {
            String sql = "select * from user where nickName = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nickName);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                User user = new User();
                user.setUserID(res.getString("userID"));
                user.setNickName(res.getString("nickName"));
                user.setAvatarImg(res.getString("avatarImg"));
                user.setPhoneNum(res.getString("phoneNum"));
                user.setAddress(res.getString("address"));
                user.setBirthday(res.getString("birthday"));
                user.setIntroduction(res.getString("introduction"));
                user.setGender(res.getInt("gender"));
                user.setPosts(Parse.stringToList(res.getString("posts")));
                user.setFollowPosts(Parse.stringToList(res.getString("followPosts")));
                users.add(user);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("findUserByName失败");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public ArrayList<User> findAllUsers() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            String sql = "select * from user";
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                User user = new User();
                user.setUserID(res.getString("userID"));
                user.setNickName(res.getString("nickName"));
                user.setAvatarImg(res.getString("avatarImg"));
                user.setPhoneNum(res.getString("phoneNum"));
                user.setAddress(res.getString("address"));
                user.setBirthday(res.getString("birthday"));
                user.setIntroduction(res.getString("introduction"));
                user.setGender(res.getInt("gender"));
                user.setPosts(Parse.stringToList(res.getString("posts")));
                user.setFollowPosts(Parse.stringToList(res.getString("followPosts")));
                users.add(user);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("findAllUsers失败");
            e.printStackTrace();
        }
        return users;
    }
}
