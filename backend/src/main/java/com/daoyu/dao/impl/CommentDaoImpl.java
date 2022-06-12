package com.daoyu.dao.impl;

import com.daoyu.dao.CommentDao;
import com.daoyu.entity.Comment;
import com.daoyu.utils.connectUtil;

import java.sql.*;
import java.util.ArrayList;

public class CommentDaoImpl implements CommentDao {
    static Connection conn;

    static {
        conn = connectUtil.getConnection();
    }

    @Override
    public int addComment(Comment comment) {
        int id = -1;
        try {
            String sql = "insert into comment values(null,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, comment.getUserID());
            stmt.setInt(2, comment.getPostID());
            stmt.setString(3, comment.getContent());
            stmt.setTimestamp(4, comment.getTime());
            stmt.executeUpdate();
            //获取自增的commentID
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("addComment失败");
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void delComment(int commentID) {
        try {
            String sql = "delete from comment where commentID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, commentID);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("delComment失败");
            e.printStackTrace();
        }
    }

    @Override
    public void updateComment(int commentID, Comment comment) {
        try {
            String sql = "update comment " +
                    "set userID = ? ,postID = ? ,content = ? ,time = ?" +
                    "WHERE commentID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, comment.getUserID());
            stmt.setInt(2, comment.getPostID());
            stmt.setString(3, comment.getContent());
            stmt.setTimestamp(4, comment.getTime());
            stmt.setInt(5, commentID);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("updateComment失败");
            e.printStackTrace();
        }
    }

    @Override
    public Comment findCommentByID(int commentID) {
        Comment comment = null;
        try {
            String sql = "select * from comment where commentID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, commentID);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                comment = new Comment();
                comment.setCommentID(res.getInt("commentID"));
                comment.setUserID(res.getString("userID"));
                comment.setPostID(res.getInt("postID"));
                comment.setContent(res.getString("content"));
                comment.setTime(res.getTimestamp("time"));
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("findCommentByID失败");
            e.printStackTrace();
        }
        return comment;
    }

    @Override
    public ArrayList<Comment> findAllComments() {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        try {
            String sql = "select * from comment";
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                Comment comment = new Comment();
                comment.setCommentID(res.getInt("commentID"));
                comment.setUserID(res.getString("userID"));
                comment.setPostID(res.getInt("postID"));
                comment.setContent(res.getString("content"));
                comment.setTime(res.getTimestamp("time"));
                comments.add(comment);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("findAllComments失败");
            e.printStackTrace();
        }
        return comments;
    }
}
