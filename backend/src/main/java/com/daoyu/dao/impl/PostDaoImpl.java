package com.daoyu.dao.impl;

import com.daoyu.dao.PostDao;
import com.daoyu.entity.Post;
import com.daoyu.entity.User;
import com.daoyu.utils.Parse;
import com.daoyu.utils.connectUtil;

import java.sql.*;
import java.util.ArrayList;

public class PostDaoImpl implements PostDao {
    static Connection conn;

    static {
        conn = connectUtil.getConnection();
    }

    @Override
    public int addPost(Post post) {
        int id = -1;
        try {
            String sql = "insert into post values(null,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, post.getUserID());
            stmt.setString(2, post.getContent());
            stmt.setString(3, post.getImage());
            stmt.setTimestamp(4, post.getTime());
            stmt.setString(5, post.getAddress());
            stmt.setString(6, Parse.listToString(post.getTags()));
            stmt.setInt(7, post.getStatus());
            stmt.setInt(8, post.getFollowNum());
            stmt.setInt(9, post.getCommentNum());
            stmt.setString(10, Parse.listToString(post.getComments()));
            stmt.executeUpdate();
            //获取自增的postID
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("addPost失败");
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void delPost(int postID) {
        try {
            String sql = "delete from post where postID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, postID);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("delPost失败");
            e.printStackTrace();
        }
    }

    @Override
    public void updatePost(int postID, Post post) {
        try {
            String sql = "update post " +
                    "set userID = ? ,content = ? ,image = ? ,time = ? ,address = ? ," +
                    "tags = ? ,status = ? ,followNum = ? ,commentNum = ? ,comments=?" +
                    "WHERE postID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, post.getUserID());
            stmt.setString(2, post.getContent());
            stmt.setString(3, post.getImage());
            stmt.setTimestamp(4, post.getTime());
            stmt.setString(5, post.getAddress());
            stmt.setString(6, Parse.listToString(post.getTags()));
            stmt.setInt(7, post.getStatus());
            stmt.setInt(8, post.getFollowNum());
            stmt.setInt(9, post.getCommentNum());
            stmt.setString(10, Parse.listToString(post.getComments()));
            stmt.setInt(11, postID);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("updatePost失败");
            e.printStackTrace();
        }
    }

    @Override
    public Post findPostByID(int postID) {
        Post post = null;
        try {
            String sql = "select * from post where postID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, postID);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                post = new Post();
                post.setPostID(res.getInt("postID"));
                post.setUserID(res.getString("userID"));
                post.setContent(res.getString("content"));
                post.setImage(res.getString("image"));
                post.setTime(res.getTimestamp("time"));
                post.setAddress(res.getString("address"));
                post.setTags(Parse.stringToList(res.getString("tags")));
                post.setStatus(res.getInt("status"));
                post.setFollowNum(res.getInt("followNum"));
                post.setCommentNum(res.getInt("commentNum"));
                post.setComments(Parse.stringToList(res.getString("comments")));
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("findPostByID失败");
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public ArrayList<Post> findPostByKeyword(String keyword) {
        ArrayList<Post> posts = new ArrayList<Post>();
        try {
            String sql = "select * from post where content like ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + keyword + "%");
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Post post = new Post();
                post.setPostID(res.getInt("postID"));
                post.setUserID(res.getString("userID"));
                post.setContent(res.getString("content"));
                post.setImage(res.getString("image"));
                post.setTime(res.getTimestamp("time"));
                post.setAddress(res.getString("address"));
                post.setTags(Parse.stringToList(res.getString("tags")));
                post.setStatus(res.getInt("status"));
                post.setFollowNum(res.getInt("followNum"));
                post.setCommentNum(res.getInt("commentNum"));
                post.setComments(Parse.stringToList(res.getString("comments")));
                posts.add(post);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("findPostByKeyword失败");
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public ArrayList<Post> findAllPosts() {
        ArrayList<Post> posts = new ArrayList<Post>();
        try {
            String sql = "select * from post";
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                Post post = new Post();
                post.setPostID(res.getInt("postID"));
                post.setUserID(res.getString("userID"));
                post.setContent(res.getString("content"));
                post.setImage(res.getString("image"));
                post.setTime(res.getTimestamp("time"));
                post.setAddress(res.getString("address"));
                post.setTags(Parse.stringToList(res.getString("tags")));
                post.setStatus(res.getInt("status"));
                post.setFollowNum(res.getInt("followNum"));
                post.setCommentNum(res.getInt("commentNum"));
                post.setComments(Parse.stringToList(res.getString("comments")));
                posts.add(post);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("findAllPosts失败");
            e.printStackTrace();
        }
        return posts;
    }
}
