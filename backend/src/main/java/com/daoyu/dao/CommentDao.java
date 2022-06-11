package com.daoyu.dao;

import com.daoyu.entity.Comment;

import java.util.ArrayList;

public interface CommentDao {
    //增加Comment对象
    int addComment(Comment comment);

    //根据commentID  删除Comment对象
    void delComment(int commentID);

    //根据commentID  更新Comment对象
    void updateComment(int commentID, Comment comment);

    //根据commentID  查找comment对象
    Comment findCommentByID(int commentID);

    //查找所有comment对象
    ArrayList<Comment> findAllComments();
}
